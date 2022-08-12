package com.pdsl.uat.java.sut;

import com.pdsl.runners.PdslGherkinApplication;
import com.pdsl.runners.PdslTest;
import com.pdsl.runners.junit.PdslGherkinJUnit4Runner;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.junit.runner.RunWith;
import com.pdsl.grammars.AllGrammarsParserBaseListener;
import com.pdsl.grammars.AllGrammarsParser;
import com.pdsl.grammars.AllGrammarsLexer;
import javax.inject.Provider;

@RunWith(PdslGherkinJUnit4Runner.class)
@PdslGherkinApplication(
        applicationName = "Polymorphic DSL Test Framework",
        context = "User Acceptance Testing",
        resourceRoot = "src/test/resources/framework_specifications/features/java"
)
public class TaggedScenarioRunner {
    @PdslTest(
            parser = AllGrammarsParser.class,
            lexer = AllGrammarsLexer.class,
            tags = "@TaggedScenario",
            listener = TaggedScenarioRunner.Listener.class
    )
    public void tagFiltering_runsOnlySubsetOfScenarios(){}


    public static final class Listener implements Provider<ParseTreeListener> {

        @Override
        public ParseTreeListener get() {
            return new AllGrammarsParserBaseListener();
        }
    }

}
