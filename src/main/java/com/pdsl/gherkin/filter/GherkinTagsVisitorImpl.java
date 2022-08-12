package com.pdsl.gherkin.filter;

import com.google.common.base.Preconditions;
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
import java.util.List;
import java.util.Set;

public class GherkinTagsVisitorImpl implements GherkinTagsVisitor<Boolean>, GherkinTagFilterer {

    private final ThreadLocal<Set<String>> tags = new ThreadLocal<>();
    private final Logger logger = LoggerFactory.getLogger(GherkinTagsVisitorImpl.class);
    @Override
    public Boolean visitNot(GherkinTagsParser.NotContext ctx) {
        return (ctx.NOT() != null) != visitExpr(ctx.expr());
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
            if (ctx.TAG() == null) {
                String message = String.format("Expected to have a single gherkin tag, but found %s",
                        ctx.TAG().getText());
                throw new IllegalStateException(message);
            }
            return tags.get().contains(ctx.getText());
        }
    }

    @Override
    public Boolean visit(ParseTree tree) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Boolean visitChildren(RuleNode node) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Boolean visitTerminal(TerminalNode node) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Boolean visitErrorNode(ErrorNode node) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean tagExpressionMatchesPickle(Set<String> pickleTags, String tagExpression) {
        Preconditions.checkNotNull(pickleTags);
        Preconditions.checkNotNull(tagExpression);
        if (pickleTags.isEmpty() && !tagExpression.isEmpty()) { return false; }
        Preconditions.checkArgument(!pickleTags.isEmpty());
        tags.set(pickleTags);
        if (tagExpression.isEmpty()) { return true; }
        GherkinTagsParser parser = getParser(tagExpression);
        parser.setErrorHandler(new BailErrorStrategy());
        try {
            boolean match = visitOr(parser.or());
            tags.remove();
            return match;
        } catch (ParseCancellationException e) {
            tags.remove();
						String message = String.format("The gherkin tag expression is invalid!%n\tTag Expression: %s", tagExpression);
            throw new IllegalArgumentException(
                    message, e);
        }
    }

    private GherkinTagsParser getParser(String tagExpression) {

        try {
            CharStream charStream = CharStreams.fromStream(new ByteArrayInputStream(tagExpression.getBytes()));
            GherkinTagsLexer lexer = new GherkinTagsLexer(charStream);
            lexer.addErrorListener(new TagExpressionErrorListener(tagExpression));
            List<? extends Token> allTokens = lexer.getAllTokens();
						String message = String.format(String.format("Parsing tag expression%n\tExpression: %s%n\tResults: %s", tagExpression, allTokens));
            logger.debug(message);
            if (allTokens.isEmpty()) {
                throw new IllegalArgumentException(String.format("Could not parse tag expression: %s", tagExpression));
            }
            lexer.reset();
            return new GherkinTagsParser(new CommonTokenStream(lexer));
        } catch (IOException e) {
            throw new IllegalArgumentException(
                    String.format("Could not perform test arrangement with tag expression:%n%s", tagExpression), e);
        }
    }
}
