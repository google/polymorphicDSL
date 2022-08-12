package com.pdsl.unit;

import com.pdsl.gherkin.filter.*;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.misc.ParseCancellationException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Set;

import static com.google.common.truth.Truth.assertThat;

@RunWith(JUnit4.class)
public class GherkinTagFiltererTest {

    private static final GherkinTagFilterer filter = new GherkinTagsVisitorImpl();
    private static final Set<String> tags = Set.of("@tagA", "@tagB", "@tagC", "@tagD", "@!","@#$");

    @Test
    public void emptyString_keepsPickle() {
        assertThat(filter.tagExpressionMatchesPickle(tags, "")).isTrue();
    }

    @Test
    public void filterSingleTagPositive_keepPickle() {
        assertThat(filter.tagExpressionMatchesPickle(tags, "@tagA")).isTrue();
    }

    @Test
    public void filterTagWithSpecialCharacterPositive_keepPickle() {
        assertThat(filter.tagExpressionMatchesPickle(tags, "@!@#$")).isTrue();
    }

    @Test
    public void filterTagNotExpression_keepPickle() {
        assertThat(filter.tagExpressionMatchesPickle(tags, "not @tagMissing")).isTrue();
    }

    @Test
    public void filterTagNotExpression_removePickle() {
        boolean filterOut = filter.tagExpressionMatchesPickle(tags, "not @tagA");
        assertThat(filterOut).isFalse();
    }

    @Test
    public void singleNestedTag_removePickle() {
        assertThat(filter.tagExpressionMatchesPickle(tags, "(@tagA)")).isTrue();
    }

    @Test
    public void expressionStartingWithParen_removePickle() {
        assertThat(filter.tagExpressionMatchesPickle(tags, "(@tagA and @tagB) and not @tagMissing")).isTrue();
    }

    @Test
    public void nestedParen_keepPickle() {
        assertThat(filter.tagExpressionMatchesPickle(tags, "((((((((@tagA))))))))")).isTrue();
    }

    @Test
    public void nestedParen_removePickle() {
        assertThat(filter.tagExpressionMatchesPickle(tags, "((((((((not @tagA))))))))")).isFalse();
    }

    @Test
    public void positiveOrExpression_keepPickle() {
        assertThat(filter.tagExpressionMatchesPickle(tags, "@tagMissing or @tagB")).isTrue();
    }

    @Test
    public void positiveAndExpression_keepPickle() {
        assertThat(filter.tagExpressionMatchesPickle(tags, "@tagA and @tagB")).isTrue();
    }

    @Test
    public void negativeAndExpression_removePickle() {
        assertThat(filter.tagExpressionMatchesPickle(tags, "@tagA and @tagMissing")).isFalse();
    }

    @Test
    public void tautalogy_keepPickle() {
        assertThat(filter.tagExpressionMatchesPickle(tags, "@tagB or not @tagB")).isTrue();
    }

    @Test
    public void complexPositiveTagExpression_keepPickle() {
        assertThat(filter.tagExpressionMatchesPickle(tags,
                "(@tagA and @tagB and (@tagMissing or (@tagD and @tagC))) and not @tagAlsoMissing")).isTrue();
    }

    @Test
    public void positiveOrNot_keepPickle() {
        assertThat(filter.tagExpressionMatchesPickle(tags,
                "@missingTag or not @tagAlsoMissing")).isTrue();
    }

    @Test
    public void deMorgansFirstTheorem_preserved() {
        assertThat(filter.tagExpressionMatchesPickle(tags,
                "not @tagMissing and not @tagAlsoMissing")).isEqualTo(
                filter.tagExpressionMatchesPickle(tags,
                        "not @tagMissing or not @tagAlsoMissing"));
        assertThat(filter.tagExpressionMatchesPickle(tags,
                "not @tagA and not @tagB")).isEqualTo(
                filter.tagExpressionMatchesPickle(tags,
                        "not @tagA or not @tagB"));
    }

    @Test
    public void notWithoutKeyword_throwsException() {
			boolean exceptionFound = false;
			try{ 
        filter.tagExpressionMatchesPickle(tags,
                "@@DoubleAt");
			} catch (Throwable e) {
				exceptionFound =true;
			}
			assertThat(exceptionFound).isTrue();
    }

    @Test
    public void invalidTagExpression_throwsException() {
			boolean exceptionFound = false;
			try{ 
                filter.tagExpressionMatchesPickle(tags, "@NotMissingTag not invalidKeyword");
			} catch (IllegalArgumentException e) {
				exceptionFound =true;
			}
			assertThat(exceptionFound).isTrue();
    }

    @Test
    public void invalidTokenSyntaxInTagExpression_throwsException() {
        boolean exceptionFound = false;
        try{
            filter.tagExpressionMatchesPickle(tags, "not not");
        } catch (IllegalArgumentException e) {
            exceptionFound =true;
        }
        assertThat(exceptionFound).isTrue();

        exceptionFound = false;
        try{
            filter.tagExpressionMatchesPickle(tags, "not @wat or");
        } catch (IllegalArgumentException e) {
            exceptionFound =true;
        }
        assertThat(exceptionFound).isTrue();

        exceptionFound = false;
        try{
            filter.tagExpressionMatchesPickle(tags, "@wat and");
        } catch (IllegalArgumentException e) {
            exceptionFound =true;
        }
        assertThat(exceptionFound).isTrue();

        exceptionFound = false;
        try{
            filter.tagExpressionMatchesPickle(tags, "@foo and (@uncloded and @needsParen");
        } catch (IllegalArgumentException e) {
            exceptionFound =true;
        }
        assertThat(exceptionFound).isTrue();

        exceptionFound = false;
        try{
            filter.tagExpressionMatchesPickle(tags, "tagWithoutAtSymbol");
        } catch (IllegalArgumentException e) {
            exceptionFound =true;
        }
        assertThat(exceptionFound).isTrue();

        exceptionFound = false;
        try{
            filter.tagExpressionMatchesPickle(tags, "@@doubleAts");
        } catch (IllegalArgumentException e) {
            exceptionFound =true;
        }
        assertThat(exceptionFound).isTrue();
    }

    @Test
    public void nullTags_throwsException() {
			boolean exceptionFound = false;
			try{ 
        filter.tagExpressionMatchesPickle(tags,
                null);

			} catch (Throwable e) {
				exceptionFound =true;
			}
			assertThat(exceptionFound).isTrue();
    }

    @Test
    public void tagExpression_positiveTest() throws IOException {
        assertThat(filter.tagExpressionMatchesPickle(
                Set.of("@tag1", "@tag2", "@tag3"), "(not @tag1 and not @tag2) and @tag2")).isFalse();
    }

    @Test
    public void tagExpression_works() throws IOException {
        assertThat(filter.tagExpressionMatchesPickle(
                Set.of("@tag1", "@tag2", "@tag3"), "(not @tag1 and not @tag2) and @tag2")).isFalse();
    }

    @Test
    public void tagExpression_isCaseInsensitive() throws IOException {
        assertThat(filter.tagExpressionMatchesPickle(
                Set.of("@tag1", "@tag2", "@tag3"), "@tag1 AND @tag2")).isTrue();
        assertThat(filter.tagExpressionMatchesPickle(
                Set.of("@tag1", "@tag2", "@tag3"), "@tag1 OR @tag9")).isTrue();
        assertThat(filter.tagExpressionMatchesPickle(
                Set.of("@tag1", "@tag2", "@tag3"), "@tag1 AnD NOT @tag9")).isTrue();
    }
}
