package com.pdsl.gherkin.parser;

import com.pdsl.logging.AnsiTerminalColorHelper;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Vocabulary;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A utility class that provides common functionality for extracting specific tokens or parameters
 * from a specified Gherkin vocabulary.
 */
public final class GherkinCommonContextHelper {

    private final Pattern FIRST_INTEGER = Pattern.compile("[^0-9]*(\\d+).*", Pattern.DOTALL);
    private final Pattern FIRST_TEXT_IN_QUOTES = Pattern.compile("[^\"]*\"([^\"]*)\".*", Pattern.DOTALL);
    private final Pattern DOCSTRING = Pattern.compile("[^\"]*\"\"\"([^\"]*)\"\"\".*", Pattern.DOTALL);
    private final Vocabulary vocabulary;

    private final Map<String, Integer> tokenToType = new HashMap<>();

    public GherkinCommonContextHelper(Vocabulary vocabulary) {
        this.vocabulary = vocabulary;
        for (int i=0; i < vocabulary.getMaxTokenType(); i++) {
            tokenToType.put(vocabulary.getSymbolicName(i), i);
        }
    }

    /** Returns the integer value in the grammars symbol table that corresponds of the specified tokens abstract name. */
    public Integer getTokenType(String symbolicName) {
        return tokenToType.get(symbolicName);
    }

    /** Gets the abstract name from the underlying vocabulary that matches the integer value in the grammar symbol table. */
    public String getTokenSymbolicName(int type) {
        return vocabulary.getSymbolicName(type);
    }

    /**
     * Returns inner text of a string that is enquoted by a leading and trailing character ignoring trailing and leading
     * whitespace.
     *
     * @return text inside arbitrary delimiting characters used for quotes
     */
    public static String extractTextInQuotes(String textInQuotes) {
        return textInQuotes.strip().substring(1, textInQuotes.length() - 2);
    }

    /**
     * Returns inner text of a string that is enquoted by 3 leading and trailing characters.
     *
     * It ignores all trailing and leading whitespace in the provided string.
     *
     * @param docstring Text inside 3 arbitrary quoting characters
     * @return String inside arbitrary delimiting characters used for triple quotes
     */
    public static String extractDocstring(String docstring) {
        return docstring.strip().substring(3, docstring.length() - 4);
    }

    /**
     * Searches the context text for the first instance of text inside double quotes and returns the inner text.
     *
     * e.g., the following text:
     * This is some rule with "some text"
     *
     * would return:
     * some text
     *
     * @param ctx a parser context that contains a string in
     * @return String of the inner text inside the first parameter with
     */
    public String extractTextInQuotes(ParserRuleContext ctx) {
        try {
           return GherkinCommonContextHelper.extractTextInQuotes(ctx.getToken(getTokenType("DOUBLE_QUOTES"), 0).getText());
        } catch (NullPointerException e) {
            Matcher matcher = FIRST_TEXT_IN_QUOTES.matcher(ctx.getText());
            if (matcher.matches()) {
                return matcher.group(1);
            } else {
                throw new IllegalArgumentException(
                        String.format("%sCould not find the text in double quotes in the following context:%n%s%s",
                                AnsiTerminalColorHelper.RED, ctx.getText(), AnsiTerminalColorHelper.RESET));
            }
        }
    }

    /**
     * Searches the context text for the first instance of an integer and returns it.
     *
     * e.g., the following text:
     * I ate 3 meals today
     *
     * would return:
     * 3
     *
     * @param ctx a parser context that contains an integer in the text
     * @return int The first int found in the text
     */
    public int extractInt(ParserRuleContext ctx) {
        try {
            return Integer.parseInt(GherkinCommonContextHelper.extractTextInQuotes(
                    ctx.getToken(getTokenType("INT"), 0).getText()));
        } catch (NullPointerException e) {
            Matcher matcher = FIRST_INTEGER.matcher(ctx.getText());
            if (matcher.matches()) {
                return Integer.parseInt(matcher.group(1));
            } else {
                throw new IllegalArgumentException(
                        String.format("%sCould not find the int in the following context:%n%s%s",
                                AnsiTerminalColorHelper.RED, ctx.getText(), AnsiTerminalColorHelper.RESET));
            }
        }
    }

    /**
     * Searches the context text for the first instance of text inside a docstring and returns the inner text.
     *
     * e.g., the following text:
     * I have a docstring ->
     * """
     * This is a docstring
     * """
     *
     * would return:
     * This is a docstring
     *
     * @param ctx a parser context that contains a docstring
     * @return String of the inner text inside the first parameter with
     */
    public String extractDocstring(ParserRuleContext ctx) {
        try {
            return extractDocstring(ctx.getToken(getTokenType("DOCSTRING"), 0).getText());
        } catch(NullPointerException e) {
            Matcher matcher = DOCSTRING.matcher(ctx.getText());
            if (matcher.matches()) {
                return matcher.group(1);
            } else {
                throw new IllegalArgumentException(
                        String.format("%sCould not find the docstring in the following context:%n%s%s",
                                AnsiTerminalColorHelper.RED, ctx.getText(), AnsiTerminalColorHelper.RESET));
            }
        }
    }
}
