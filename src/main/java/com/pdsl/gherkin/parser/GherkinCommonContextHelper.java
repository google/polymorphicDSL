package com.pdsl.gherkin.parser;

import com.pdsl.logging.AnsiTerminalColorHelper;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Vocabulary;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    public Integer getTokenType(String symbolicName) {
        return tokenToType.get(symbolicName);
    }

    public String getTokenSymbolicName(int type) {
        return vocabulary.getSymbolicName(type);
    }

    public static String extractTextInQuotes(String textInQuotes) {
        return textInQuotes.strip().substring(1, textInQuotes.length() - 2);
    }

    public static String extractDocstring(String docstring) {
        return docstring.strip().substring(3, docstring.length() - 4);
    }

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
