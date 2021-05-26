package com.google.pdsl.reports;

import java.util.Optional;

public interface TestResultReportProvider {

    Optional<TestRunResults> getResults();
}
