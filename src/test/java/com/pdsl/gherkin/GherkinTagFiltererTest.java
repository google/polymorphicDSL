package com.pdsl.gherkin;

import static com.google.common.truth.Truth.assertThat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.nio.charset.StandardCharsets;
import java.util.Set;

@RunWith(JUnit4.class)
public class GherkinTagFiltererTest {

    private static final GherkinTagFilterer filter = new GherkinTagFiltererImpl();
    private static final Set<String> tags = Set.of("@tagA", "@tagB", "@tagC", "@tagD", "@!@#$");

    @Test
    public void emptyString_keepsPickle() {
        assertThat(filter.tagExpressionMatchesPickle(tags, "", StandardCharsets.UTF_8)  ).isTrue();
    }

    @Test
    public void filterSingleTagPositive_keepPickle() {
        assertThat(filter.tagExpressionMatchesPickle(tags, "@tagA", StandardCharsets.UTF_8)  ).isTrue();
    }

    @Test
    public void filterTagWithSpecialCharacterPositive_keepPickle() {
        assertThat(filter.tagExpressionMatchesPickle(tags, "@!@#$", StandardCharsets.UTF_8)).isTrue();
    }

    @Test
    public void filterTagNotExpression_keepPickle() {
        assertThat(filter.tagExpressionMatchesPickle(tags, "not @tagMissing", StandardCharsets.UTF_8)).isTrue();
    }

    @Test
    public void filterTagNotExpression_removePickle() {
         boolean filterOut = filter.tagExpressionMatchesPickle(tags, "not @tagA", StandardCharsets.UTF_8);
        assertThat(filterOut).isFalse();
    }

    @Test
    public void singleNestedTag_removePickle() {
        assertThat(filter.tagExpressionMatchesPickle(tags, "(@tagA)", StandardCharsets.UTF_8)).isTrue();
    }

    @Test
    public void expressionStartingWithParen_removePickle() {
        assertThat(filter.tagExpressionMatchesPickle(tags, "(@tagA and @tagB) and not @tagMissing", StandardCharsets.UTF_8)).isTrue();
    }

    @Test
    public void nestedParen_keepPickle() {
        assertThat(filter.tagExpressionMatchesPickle(tags, "((((((((@tagA))))))))", StandardCharsets.UTF_8)).isTrue();
    }

    @Test
    public void nestedParen_removePickle() {
        assertThat(filter.tagExpressionMatchesPickle(tags, "((((((((not @tagA))))))))", StandardCharsets.UTF_8)).isFalse();
    }

    @Test
    public void positiveOrExpression_keepPickle() {
            assertThat(filter.tagExpressionMatchesPickle(tags, "@tagMissing or @tagB", StandardCharsets.UTF_8)).isTrue();
    }

    @Test
    public void positiveAndExpression_keepPickle() {
        assertThat(filter.tagExpressionMatchesPickle(tags, "@tagA and @tagB", StandardCharsets.UTF_8)).isTrue();
    }

    @Test
    public void negativeAndExpression_removePickle() {
        assertThat(filter.tagExpressionMatchesPickle(tags, "@tagA and @tagMissing", StandardCharsets.UTF_8)).isFalse();
    }

    @Test
    public void tautalogy_keepPickle() {
        assertThat(filter.tagExpressionMatchesPickle(tags, "@tagB or not @tagB", StandardCharsets.UTF_8)).isTrue();
    }

    @Test
    public void complexPositiveTagExpression_keepPickle() {
        assertThat(filter.tagExpressionMatchesPickle(tags,
                "(@tagA and @tagB and (@tagMissing or (@tagD and @tagC))) and not @tagAlsoMissing",
                StandardCharsets.UTF_8)).isTrue();
    }

    @Test
    public void positiveOrNot_keepPickle() {
        assertThat(filter.tagExpressionMatchesPickle(tags,
                "@missingTag or not @tagAlsoMissing",
                StandardCharsets.UTF_8)).isTrue();
    }

    @Test
    public void deMorgansFirstTheorem_preserved() {
        assertThat(filter.tagExpressionMatchesPickle(tags,
                "not @tagMissing and not @tagAlsoMissing",
                StandardCharsets.UTF_8)).isEqualTo(
                filter.tagExpressionMatchesPickle(tags,
                        "not @tagMissing or not @tagAlsoMissing",
                        StandardCharsets.UTF_8));
        assertThat(filter.tagExpressionMatchesPickle(tags,
                "not @tagA and not @tagB",
                StandardCharsets.UTF_8)).isEqualTo(
                filter.tagExpressionMatchesPickle(tags,
                        "not @tagA or not @tagB",
                        StandardCharsets.UTF_8));
    }
}
