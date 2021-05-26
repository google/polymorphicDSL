package com.google.pdsl.gherkin;

import com.google.common.base.Ascii;
import com.google.common.base.Preconditions;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class GherkinTagFiltererImpl implements GherkinTagFilterer {

    public boolean tagExpressionMatchesPickle(Set<String> pickleTags, String tagExpression) {
        return tagExpressionMatchesPickle(pickleTags, tagExpression, StandardCharsets.UTF_8);
    }

    @Override
    public boolean tagExpressionMatchesPickle(Set<String> pickleTags, String tagExpression, Charset charset) {
        if (tagExpression == null || tagExpression.isEmpty()) { return true; }
        Stack<String> expressionStack = new Stack<>();
        // First parse tag expression
        byte[] tagBytes = tagExpression.getBytes(charset);
        return new Lexer.Evaluator(Lexer.lex(tagBytes, charset), pickleTags).tagsMatchExpression();
    }

    private static class Token {
        public static final int END_OF_INPUT = -1;
        public static final int TAG = 0;
        public static final int NOT = 1;
        public static final int AND = 2;
        public static final int OR = 3;
        public static final int L_PAREN = 4;
        public static final int R_PAREN = 5;
        int tokenType = -1;
        String value = "";

        public Token(int tokenType) {

            this.tokenType = tokenType;
        }

        public Token(int tokenType, String value) {
            this.tokenType = tokenType;
            this.value = value;
        }
    }

    private static class Lexer {

        public static Queue<Token> lex(byte[] tagBytes, Charset charset) {
            ByteArrayOutputStream elem = new ByteArrayOutputStream();
            Queue<Token> tokens = new LinkedList<>();
            boolean processingTag = false;
            boolean isExpr = true;
            boolean isNot = false;
                for (byte b : tagBytes) {
                    switch (b) {
                        case '(':
                            tokens.add(new Token(Token.L_PAREN, "("));
                            break;
                        case ')':
                            if (!isExpr) {
                                String tag = elem.toString(charset);
                                tokens.add(new Token(Token.TAG, isNot ? "~" + tag : tag));
                                elem.reset();
                                processingTag = false;
                            }
                            isExpr = true;
                            tokens.add(new Token(Token.R_PAREN,")"));
                            break;
                        case '\n':
                        case '\t':
                        case ' ':
                            if (isExpr) {
                                switch (Ascii.toLowerCase(elem.toString(charset))) {
                                    case "not":
                                        isNot = true;
                                        break;
                                    case "and":
                                        tokens.add(new Token(Token.AND, "and"));
                                        break;
                                    case "or":
                                        tokens.add(new Token(Token.OR, "or"));
                                        break;
                                    case "":
                                        continue;
                                    default:
                                        throw new IllegalArgumentException("Do not support operator " + elem.toString());
                                }
                                elem.write(b);
                            } else {
                                tokens.add(new Token(Token.TAG, isNot ? "~" + elem.toString() : elem.toString()));
                            }
                            isExpr = true;
                            elem.reset();
                            break;
                        case '@':
                            if (processingTag) { // @ is not separated by whitespace
                                String tag = elem.toString(charset);
                                if (tag.equals("@")) { // Two @ in a row
                                    throw new IllegalArgumentException("Cannot have a tag start with '@@'");
                                }
                                processingTag = true;
                                tokens.add(new Token(Token.TAG, isNot ? "~" + tag : tag));
                            }
                            isExpr = false;
                            elem.write(b);
                            break;
                        default:
                            elem.write(b);
                            break;
                    }
                }
            String possibleLastToken = elem.toString(charset);
                if (!possibleLastToken.isEmpty()) {
                    assert(!isExpr) : "Last item was not a gherkin tag [@<someText>]";
                    tokens.add(new Token(Token.TAG, isNot ? "~"  + possibleLastToken : possibleLastToken));
                }
            return tokens;
        }

        /*
        expr:   or ;
        or:  and {("or") and} ;
        and:   not {("and") not} ;
        not: "not"? tag | "(" expr ")"  ;
        */
        private static class Evaluator {
            private Queue<Token> tokens; // Input from lexer
            private Set<String> pickleTags;
            private Token curr;
            private Token prev;

            public Evaluator(Queue<Token> tokens, Set<String> pickleTags) {
                Preconditions.checkNotNull(pickleTags);
                Preconditions.checkNotNull(tokens);
                Preconditions.checkArgument(!tokens.isEmpty(), "Tag lexer found no tokens!");
                this.tokens = tokens;
                this.pickleTags = Collections.unmodifiableSet(pickleTags);
            }

            private boolean tagsMatchExpression() {
                boolean result = expr();
                if (!tokens.isEmpty()) {
                    throw new IllegalStateException("Gherkin Tag error: Not all lexer tokens were consumed by the parser!");
                }
                return result;
            }

            private void advance() {
                prev = curr;
                if (!tokens.isEmpty()) {
                    curr = tokens.remove();
                }
            }

            // If the current token is of kind k, advance to the next token and return true;
            // else, return false.
            private boolean match(int k) {
                if (curr.tokenType != k)
                    return false;
                advance();
                return true;
            }

            // If the current token matches k, advance to the next token and return true;
            // else, throw a parse exception.
            private boolean expect(int k) {
                if (curr.tokenType == k) {
                    advance();
                    return true;
                }
                System.out.println("Expected token kind " + k + "; got " + curr.tokenType);
                throw new IllegalStateException("Expected token kind " + k + "; got " + curr.tokenType +"(" + curr.value != null ? curr.value : "" + ")");
            }

            private boolean tagInSet(String possiblyInvertedTag) {
                if (curr.value.startsWith("~")) {
                    return !pickleTags.contains(curr.value.substring(1));
                } else if (curr.value.startsWith("@")) {
                    return pickleTags.contains(curr.value);
                } else {
                    throw new IllegalStateException("Do not know how to handle gherkin tag " + curr.value);
                }
            }

            private boolean parseTag() {
                if (curr.tokenType == Token.TAG) {
                    boolean tagInSet = tagInSet(curr.value);
                    advance();
                    return tagInSet;
                } else if (match(Token.L_PAREN)) {
                    boolean result = or();
                    expect(Token.R_PAREN);
                    return result;
                } else {
                    throw new IllegalStateException("Expected tag or left parenthesis");
                }
            }

            private boolean and() {
                boolean tagMatches = parseTag();
                while (match(Token.AND) || match(Token.NOT)) {
                    boolean nextTagMatches = parseTag();
                    tagMatches = tagMatches && nextTagMatches;
                }
                return tagMatches;
            }

            private boolean or() {
                boolean tagMatches = and();
                while (match(Token.OR)) {
                    boolean nextTerm = and();
                    tagMatches = nextTerm || tagMatches;
                }
                return tagMatches;
            }

            boolean expr() {
                advance(); // Populate the first token
                return or();
            }
        }
    }

}
