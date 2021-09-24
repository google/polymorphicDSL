package com.pdsl.gherkin.models;

import java.util.*;

public class GherkinExamplesTable {
    private final Optional<List<String>> tags;
    private final Optional<String> title;
    private final Optional<String> longDescription;
    private final Optional<Map<String, List<String>>> table;

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
        return table;
    }

    public List<Map<String, String>> getRows() {
        if (table.isEmpty()) {
            return new ArrayList<>();
        }
        Map<String, List<String>> tableData = table.get();
        final int TOTAL_ROWS = tableData.get(new ArrayList<>(tableData.keySet()).get(0)).size();
        List<Map<String, String>> rows = new ArrayList<>(TOTAL_ROWS);
        for (int i = 0; i < TOTAL_ROWS; i++) {
            Map<String, String> rowSubstitutions = new HashMap<>();
            for (Map.Entry<String, List<String>> entry : tableData.entrySet()) {
                rowSubstitutions.put(entry.getKey(), tableData.get(entry.getKey()).get(i));
            }
            rows.add(rowSubstitutions);
        }
        return rows;
    }

    public static class Builder {
        private String title = "";
        private String longDescription = "";
        private List<String> tags = new ArrayList<>();
        private Optional<Map<String, List<String>>> table = Optional.empty();

        public GherkinExamplesTable build() {
            return new GherkinExamplesTable(this);
        }

        public Builder withTable(Map<String, List<String>> table) {
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
}
