package com.pdsl.reports;

import com.google.common.base.Preconditions;
import com.pdsl.reports.proto.*;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.*;

public class PdslReportData {

    private final Map<String, Map<List<String>, Map<String, Collection<TestResult>>>> fullReportData = new HashMap<>();

    public void addData(String application, List<String> unfilteredPhraseBody, String context, TestResult testResult) {
        fullReportData.computeIfAbsent(application, k -> new HashMap<>());
        fullReportData.get(application).computeIfAbsent(unfilteredPhraseBody, k -> new HashMap<>());
        fullReportData.get(application).get(unfilteredPhraseBody).computeIfAbsent(context, k -> new ArrayList<>());
        fullReportData.get(application).get(unfilteredPhraseBody).get(context).add(testResult);
    }

    public StrategicReportData getReportData() {
        StrategicReportData.Builder strategic = StrategicReportData.newBuilder();
        for (Map.Entry<String, Map<List<String>, Map<String, Collection<TestResult>>>> applicationToPhraseBody : fullReportData.entrySet()) {
            OperationalReportData.Builder operational = OperationalReportData.newBuilder();
            operational.setApplication(applicationToPhraseBody.getKey());
            for (Map.Entry<List<String>, Map<String, Collection<TestResult>>> phraseBody : applicationToPhraseBody.getValue().entrySet()) {
                TacticalReportData.Builder tactical = TacticalReportData.newBuilder();
                tactical.addAllUnfilteredPhraseBody(phraseBody.getKey());
                for (Map.Entry<String, Collection<TestResult>> contextToTestResults : phraseBody.getValue().entrySet()) {
                    TestCaseGroup.Builder testCaseGroup = TestCaseGroup.newBuilder();
                    for (TestResult testResult : contextToTestResults.getValue()) {
                        TechnicalReportData.Builder technical = TechnicalReportData.newBuilder();
                        technical.addAllFilteredPhraseBody(testResult.getTestCase().getContextFilteredPhraseBody());
                        technical.setTestCaseTitle(testResult.getTestCaseTitle());
                        if (testResult.getStatus().equals(TechnicalReportData.Status.FAILED)) {
                            Preconditions.checkArgument(testResult.getFailingPhrase().isPresent(),
                                    "A failing test must have a failing phrase!");
                            Preconditions.checkArgument(testResult.getFailureReason().isPresent(),
                                    "A failing test must have an associated exception!");
                            // Get full stack trace
                            final StringWriter sw = new StringWriter();
                            final PrintWriter pw = new PrintWriter(sw, true);
                            Throwable t = testResult.getFailureReason().get();
                            t.printStackTrace(pw);
                            technical.setFailureReason(sw.getBuffer().toString());
                            // Set all other failure fields
                            technical.setFailingPhrase(testResult.getFailingPhrase().get().getParseTree().getText());
                            technical.setFailingPhraseIndex(testResult.getFailingPhrase().get().getPrefilteredIndex());
                        }
                        technical.setStatus(testResult.getStatus());
                        testCaseGroup.addTechnicalReportData(technical.build());
                    }
                    tactical.putContextToTestCases(contextToTestResults.getKey(), testCaseGroup.build());
                }
                operational.addTacticalReportData(tactical.build());
            }
            strategic.addOperationalReportData(operational.build());
        }
        return strategic.build();
    }
}
