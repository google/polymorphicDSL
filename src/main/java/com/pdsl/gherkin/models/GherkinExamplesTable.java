package com.pdsl.gherkin.models;

import java.util.*;
import java.util.stream.Collectors;

/**
 * A DTO representing a table in a gherkin scenario.
 */
public class GherkinExamplesTable {
    private final Optional<List<String>> tags;
    private final Optional<String> title;
    private final Optional<String> longDescription;
    private final Optional<Map<String, List<CellOfExamplesTable>>> table;

    private GherkinExamplesTable(Builder builder) {
        this.title = builder.title.isEmpty() ? Optional.empty() : Optional.of(builder.title);
        this.longDescription = builder.longDescription.isEmpty() ? Optional.empty()
                : Optional.of(builder.longDescription);
        this.tags = builder.tags.isEmpty() ? Optional.empty() : Optional.of(builder.tags);
        this.table = builder.table;
    }

    public Optional<List<String>> getTags() {
        return tags;
    }

    public Optional<String> getTitle() {
        return title;
    }

    public Optional<String> getLongDescription() {
        return longDescription;
    }

    public Optional<Map<String, List<String>>> getTable() {
        return table.map(t -> t.entrySet().stream().collect(Collectors.toMap(
                Map.Entry::getKey, entry -> entry.getValue().stream()
                        .map(CellOfExamplesTable::substitutionValue).toList()
        )));
    }

    public List<Map<String, String>> getRows() {
        List<Map<String, CellOfExamplesTable>> examplesTableMapping = getRowsWithCell();
        List<Map<String, String>> cellOfExamplesTableAsStringValues = new ArrayList<>(examplesTableMapping.size());
        for (Map<String, CellOfExamplesTable> mapping : examplesTableMapping) {
            cellOfExamplesTableAsStringValues.add(
                    mapping.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey,
                    entry -> entry.getValue().substitutionValue)));
        }
       return cellOfExamplesTableAsStringValues;
    }

    public List<Map<String, CellOfExamplesTable>> getRowsWithCell() {
        if (table.isEmpty()) {
            return new ArrayList<>();
        }
        Map<String, List<CellOfExamplesTable>> tableData = table.get();
        final int TOTAL_ROWS = tableData.get(new ArrayList<>(tableData.keySet()).get(0)).size();
        List<Map<String, CellOfExamplesTable>> rows = new ArrayList<>(TOTAL_ROWS);
        for (int i = 0; i < TOTAL_ROWS; i++) {
            Map<String, CellOfExamplesTable> rowSubstitutions = new HashMap<>();
            for (Map.Entry<String, List<CellOfExamplesTable>> entry : tableData.entrySet()) {
                rowSubstitutions.put(entry.getKey(), tableData.get(entry.getKey()).get(i));
            }
            rows.add(rowSubstitutions);
        }
        return rows;
    }

    /**
     * A builder for creating an Examples table DTO.
     */
    public static class Builder {
        private String title = "";
        private String longDescription = "";
        private List<String> tags = new ArrayList<>();
        private Optional<Map<String, List<GherkinExamplesTable.CellOfExamplesTable>>> table = Optional.empty();

        public GherkinExamplesTable build() {
            return new GherkinExamplesTable(this);
        }

        public Builder withTable(Map<String, List<GherkinExamplesTable.CellOfExamplesTable>> table) {
            this.table = table == null ? Optional.empty() : Optional.of(table);
            return this;
        }

        public Builder withTags(List<String> tags) {
            this.tags = tags;
            return this;
        }

        public Builder withTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder withLongDescription(String longDescription) {
            this.longDescription = longDescription;
            return this;
        }
    }

    /**
     * A DTO that represents a cell in an examples row.
     * @param lineNumber the line in the original source file the row was located in
     * @param substitutionValue the text of the cell after un-escaping the text and stripping whitespace
     */
    public record CellOfExamplesTable(int lineNumber, String substitutionValue) {}
}
