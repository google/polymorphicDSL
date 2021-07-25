package com.pdsl.gherkin.filter;

import com.google.common.base.Preconditions;
import com.pdsl.transformers.DefaultPolymorphicDslPhraseFilter;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.ParseCancellationException;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.RuleNode;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.*;

public class GherkinTagsVisitorImpl implements GherkinTagsVisitor<Boolean>, GherkinTagFilterer {

    private ThreadLocal<Set<String>> tags = new ThreadLocal<>();
    private final Logger logger = LoggerFactory.getLogger(GherkinTagsVisitorImpl.class);
    @Override
    public Boolean visitNot(GherkinTagsParser.NotContext ctx) {
        return ctx.NOT() != null ? !visitExpr(ctx.expr()) : visitExpr(ctx.expr());
    }

    @Override
    public Boolean visitAnd(GherkinTagsParser.AndContext ctx) {
        boolean match = true;
        for (GherkinTagsParser.NotContext notContext : ctx.not()) {
            match &= visitNot(notContext);
        }
         return match;
    }

    @Override
    public Boolean visitOr(GherkinTagsParser.OrContext ctx) {
        boolean match = false;
        for (GherkinTagsParser.AndContext andContext : ctx.and()) {
            match |= visitAnd(andContext);
        }
        return match;
    }

    @Override
    public Boolean visitExpr(GherkinTagsParser.ExprContext ctx) {
        if (ctx.L_PAREN() != null) {
            return visitOr(ctx.or());
        } else {
            if (ctx.TAG() != null) {
                return tags.get().contains(ctx.getText());
            }
            return null;
        }
    }

    @Override
    public Boolean visit(ParseTree tree) {
        return null;
    }

    @Override
    public Boolean visitChildren(RuleNode node) {
        return null;
    }

    @Override
    public Boolean visitTerminal(TerminalNode node) {
        return null;
    }

    @Override
    public Boolean visitErrorNode(ErrorNode node) {
        return null;
    }

    @Override
    public boolean tagExpressionMatchesPickle(Set<String> pickleTags, String tagExpression) {
        Preconditions.checkNotNull(pickleTags);
        Preconditions.checkNotNull(tagExpression);
        Preconditions.checkArgument(!pickleTags.isEmpty());
        tags.set(pickleTags);
        if (tagExpression.isEmpty()) { return true; }
        GherkinTagsParser parser = getParser(tagExpression);
        parser.setErrorHandler(new BailErrorStrategy());
        try {
            return visitOr(parser.or());
        } catch (ParseCancellationException e) {
            throw new IllegalArgumentException(
                    String.format("The gherkin tag expression is invalid!%n\tTag Expression: %s", tagExpression), e);
        }
    }

    private GherkinTagsParser getParser(String tagExpression) {

        try {
            CharStream charStream = CharStreams.fromStream(new ByteArrayInputStream(tagExpression.getBytes()));
            GherkinTagsLexer lexer = new GherkinTagsLexer(charStream);
            lexer.addErrorListener(new TagExpressionErrorListener(tagExpression));
            List<? extends Token> allTokens = lexer.getAllTokens();
            logger.debug(String.format("Parsing tag expression%n\tExpression: %s%n\tResults: %s", tagExpression, allTokens));
            if (allTokens.isEmpty()) {
                throw new IllegalArgumentException(String.format("Could not parse tag expression: %s", tagExpression));
            }
            lexer.reset();
            return new GherkinTagsParser(new CommonTokenStream(lexer));
        } catch (IOException e) {
            throw new IllegalArgumentException(
                    String.format("Could not perform test arrangement with tag expression:\n%s", tagExpression), e);
        }
    }
}
