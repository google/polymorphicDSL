package com.pdsl.custom;

import com.pdsl.runners.PdslGherkinApplication;
import com.pdsl.runners.PdslTest;
import com.pdsl.runners.PdslConfiguration;
import com.pdsl.runners.RecognizedBy;
import com.pdsl.runners.junit.PdslGherkinJUnit4Runner;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.junit.AfterClass;
import org.junit.runner.RunWith;
import com.example.DotNavigationLexer;
import com.example.DotNavigationParser;
import com.example.DotNavigationParserBaseListener;
import javax.inject.Provider;
import com.pdsl.specifications.FileDelimitedTestSpecificationFactory;
import com.pdsl.testcases.SingleTestOutputPreorderTestCaseFactory;
import com.pdsl.runners.junit.PdslJUnit4ConfigurableRunner;

import java.util.*;

/**
 * A listener that fires off Java code every time we encounter a phrase we care about in the DOT file we are parsing.
 * In this case all we really care about is figuring out what pages link to other pages, so we simply pay attention
 * to those phrases and ignore all the others.
 */
 public class CustomDotNavigationListener extends DotNavigationParserBaseListener {

    public Map<String, List<String>> getNavigationPath() {
        return navigationPath;
    }

    public Map<String, List<String>> getAdminOnly() {
        return adminOnly;
    }

    private final Map<String, List<String>> navigationPath = new HashMap<>();
    private final Map<String, List<String>> adminOnly = new HashMap<>();



        /**
         * Determines what pages another page can navigate to.
         *
         * In a real test we would probably use something like webdriver to actually attempt to navigate to these
         * pages, or after we define the graph internally (which we do with the variables navigationPath and adminOnly)
         * use an algorithm to traverse all possible paths.
         *
         * However since this is just a proof of concept we'll just find out what the navigation paths are and spot
         * check whether it identified them.
         * @param ctx the parse tree
         */
        @Override
        public void enterLinkedPages(DotNavigationParser.LinkedPagesContext ctx) {
            if (ctx.page().admin() != null || ctx.link().get(0).page().admin() != null) {
                List<String> paths = adminOnly.computeIfAbsent(ctx.page().PAGE().getText().trim(), (p -> new ArrayList<>()));
                String pageToNavigateToText = ctx.link().get(0).page().PAGE().getText().trim();
                paths.add(pageToNavigateToText);
            } else {
                List<String> paths = navigationPath.computeIfAbsent(ctx.page().PAGE().getText().trim(), (p -> new ArrayList<>()));
                paths.add(ctx.link().get(0).page().PAGE().getText().trim());
            }
            for (int i=1; i < ctx.link().size(); i++) {
                DotNavigationParser.PageContext page = ctx.link().get(i - 1).page();
                DotNavigationParser.PageContext navigateTo = ctx.link().get(i).page();
                String pageText = page.getText().trim();
                List<String> paths = page.admin() != null || navigateTo.admin() != null
                        ? adminOnly.computeIfAbsent(pageText, (p -> new ArrayList<>()))
                        : navigationPath.computeIfAbsent(pageText, (p -> new ArrayList<>()));
                paths.add(navigateTo.getText().trim());
            }
        }
 }

