package com.pdsl.uat;

import com.pdsl.logging.AnsiTerminalColorHelper;
import junit.framework.Test;
import junit.framework.TestSuite;
import junit.textui.TestRunner;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import java.util.stream.Collectors;

public class AllTests {

    public static void main(String[] args) {

            JUnitCore runner = new JUnitCore();
            Result result = runner.run(PdslConfigurableExecutorTest.class);
            if (!result.wasSuccessful()) {
                throw new PdslUserAcceptanceTestFailure(createErrorString(result));
            }
            System.out.println(AnsiTerminalColorHelper.BRIGHT_GREEN + "User acceptance tests passed!" + AnsiTerminalColorHelper.RESET);
    }



        private static final class PdslUserAcceptanceTestFailure extends RuntimeException {
            PdslUserAcceptanceTestFailure(String err) {
                super(err);
            }
        }

        private static final String createErrorString(Result result) {
            return String.format("There was an error with one of the User Acceptance Tests!%n"
                    + "%s",
                    result.getFailures().stream()
                            .map(Failure::toString)
                            .collect(Collectors.joining(String.format("%n\t"))).toString());
        };


}
