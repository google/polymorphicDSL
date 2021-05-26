package com.google.pdsl.gherkin.models;

import java.util.*;

public class GherkinStep {

    public enum StepType {
        GIVEN, WHEN, THEN, AND, BUT, WILD;
    }
    private StepType stepType;
    private Optional<GherkinDocString> docString;
    private Optional<List<List<GherkinString>>> dataTable;
    private String stepKeywordText;
    private GherkinString stepContent;

    private GherkinStep(Builder builder) {
        Objects.requireNonNull(builder.stepKeywordText);
        Objects.requireNonNull(builder.stepContent);
        this.docString = builder.docString.isEmpty() ? Optional.empty()
                : Optional.of(new GherkinDocString(builder.docString));
        this.stepType = builder.stepType;
        this.dataTable = builder.dataTable.isEmpty() ? Optional.empty() : Optional.of(builder.dataTable);
        this.stepKeywordText = builder.stepKeywordText;
        this.stepContent = new GherkinString(builder.stepContent);
    }

    public static class Builder {
        private String docString = "";
        private List<List<GherkinString>> dataTable = new LinkedList<>();
        private String stepContent;
        private String stepKeywordText;
        private StepType stepType;

        public GherkinStep build() {
            return new GherkinStep(this);
        }

        public Builder withStepContent(String stepContent) {
            this.stepContent = stepContent;
            return this;
        }

        public Builder withStepKeyword(StepType stepType, String stepKeywordText) {
            this.stepType = stepType;
            this.stepKeywordText = stepKeywordText;
            return this;
        }

        public Builder withDataTable(List<List<GherkinString>> dataTable) {
            this.dataTable = dataTable;
            return this;
        }

        public Builder withDocString(String docString) {
            this.docString = docString;
            return this;
        }
    }

    public StepType getStepType() {
        return stepType;
    }

    public Optional<GherkinDocString> getDocString() {
        return docString;
    }

    public Optional<List<List<GherkinString>>> getDataTable() {
        return dataTable;
    }

    public String getStepKeywordText() {
        return stepKeywordText;
    }

    public GherkinString getStepContent() {
        return stepContent;
    }
}
