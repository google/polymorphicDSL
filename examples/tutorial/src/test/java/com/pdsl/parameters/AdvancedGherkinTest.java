package com.pdsl.parameters;

import static com.google.common.truth.Truth.assertThat;

import com.example.*;

import org.antlr.v4.runtime.tree.AbstractParseTreeVisitor;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.junit.jupiter.api.TestTemplate;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.api.extension.TestTemplateInvocationContext;
import org.junit.jupiter.engine.descriptor.PdslConfigParameter;
import org.junit.jupiter.engine.descriptor.PdslExecutable;
import org.junit.jupiter.engine.descriptor.PdslGherkinInvocationContextProvider;
import org.junit.jupiter.engine.descriptor.PdslTestParameter;

import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AdvancedGherkinTest {

    @TestTemplate
    @ExtendWith(AdvancedGherkinContext.class)
    public void advancedGherkinSuite(PdslExecutable pdslExecutable) {
        pdslExecutable.execute();
    }

    private static class AdvancedGherkinContext extends PdslGherkinInvocationContextProvider {
        private static final ParseTreeVisitor<?> VISITOR = new AdvancedGherkinVisitor();
        @Override
        public Stream<TestTemplateInvocationContext> provideTestTemplateInvocationContexts(ExtensionContext context) {
            return getInvocationContext(PdslConfigParameter.createGherkinPdslConfig(List.of(
                    new PdslTestParameter.Builder(AdvancedGherkinLexer.class, PdslAdvancedGherkinParser.class, () -> VISITOR)
                            .withIncludedResources(new String[] {"datatables_with_new_lines.feature"})
                            .build()
            ))
                    .withResourceRoot(Paths.get("../../src/test/resources/testdata/good/").toUri()).build()).stream();
        }
    }

    private static class AdvancedGherkinVisitor extends AbstractParseTreeVisitor<Void> implements PdslAdvancedGherkinParserVisitor<Void> {

        @Override
        public Void visitGivenExampleOfNegativeSpace(PdslAdvancedGherkinParser.GivenExampleOfNegativeSpaceContext ctx) {
            List<List<String>> table = tableAsListOfLists(ctx.dataTable());
            boolean anyCellTextStartsOrEndsWithWhitespace = table.stream()
                    .anyMatch(row ->
                        row.stream().anyMatch(cellText -> cellText.startsWith(" ") || cellText.endsWith(" "))
                        );
            assertThat(anyCellTextStartsOrEndsWithWhitespace).isFalse();
            return null;
        }

        @Override
        public Void visitGivenLinesOfPoetry(PdslAdvancedGherkinParser.GivenLinesOfPoetryContext ctx) {
            List<List<String>> table = tableAsListOfLists(ctx.dataTable());
            // Step should only have one cell with one row
            assertThat(table.size()).isEqualTo(1);
            assertThat(table.get(0).size()).isEqualTo(1);

            String[] cellContentSplitByEscapedLineBreak = ctx.dataTable().row().get(0).getText().split("\n");
            // ...on n lines
            assertThat(cellContentSplitByEscapedLineBreak.length).isEqualTo(Long.parseLong(ctx.INT(1).getText()));
            System.out.println("Cells after splitting:");
            Arrays.stream(cellContentSplitByEscapedLineBreak).forEach(System.out::println);
            long linesWithoutBlankText = Arrays.stream(cellContentSplitByEscapedLineBreak)
                    .map(String::strip)
                    .filter(s -> !s.isBlank() && !s.equals("|"))
                    .count();
            // given x lines of poetry
            assertThat(linesWithoutBlankText).isEqualTo(Long.parseLong(ctx.INT().get(0).getText()));
            return null;
        }

        private List<List<String>> tableAsListOfLists( PdslAdvancedGherkinParser.DataTableContext tableCtx) {
            return tableCtx.row().stream()
                    .map(row -> row.cell().stream()
                            .map(PdslAdvancedGherkinParser.CellContext::getText)
                            .collect(Collectors.toUnmodifiableList()))
                    .collect(Collectors.toUnmodifiableList());
        }

        private Map<String, List<String>> tableAsHeaderWithColumnData(PdslAdvancedGherkinParser.DataTableContext tableContext) {
            // Organize the data by rows
            List<List<String>> tableAsListOfLists = tableAsListOfLists(tableContext);
            // The first row contains the headers.
            List<String> headers = tableAsListOfLists.get(0);
            Map<String, List<String>> map2Columns = new HashMap<>();
            for (int i=1; i < tableAsListOfLists.size(); i++) {
                List<String> row = tableAsListOfLists.get(i);
                for (int j=0; j < row.size(); j++) {
                    List<String> column = map2Columns.computeIfAbsent(headers.get(j), (k) -> new ArrayList<String>(tableAsListOfLists.size()));
                    column.add(row.get(j));
                }
            }
            return map2Columns;
        }

        @Override
        public Void visitDataTable(PdslAdvancedGherkinParser.DataTableContext ctx) {
            return visitChildren(ctx);
        }

        @Override
        public Void visitRow(PdslAdvancedGherkinParser.RowContext ctx) {
            return visitChildren(ctx);
        }

        @Override
        public Void visitCell(PdslAdvancedGherkinParser.CellContext ctx) {
            return visitChildren(ctx);
        }

        // Recognizer rules
        @Override
        public Void visitAdvancedGherkinAllRules(PdslAdvancedGherkinParser.AdvancedGherkinAllRulesContext ctx) {
            return visitChildren(ctx);
        }

        @Override
        public Void visitPolymorphicDslSyntaxCheck(PdslAdvancedGherkinParser.PolymorphicDslSyntaxCheckContext ctx) {
            return visitChildren(ctx);
        }

        @Override
        public Void visitPolymorphicDslAllRules(PdslAdvancedGherkinParser.PolymorphicDslAllRulesContext ctx) {
            return visitChildren(ctx);
        }
    }

}
