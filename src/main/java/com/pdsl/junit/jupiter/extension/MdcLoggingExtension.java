package com.pdsl.junit.jupiter.extension;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.slf4j.MDC;

/**
 * A JUnit 5 extension that adds a unique identifier to the logging context (MDC) for each test.
 *
 * <p>This extension puts a unique test ID into the SLF4J Mapped Diagnostic Context (MDC) before each
 * test is run, and removes it after the test completes. This allows for structured logging and
 * makes it easier to trace logs for a specific test execution, especially in parallel test runs.
 *
 * <p>The test ID is constructed using the test class name, test method name, and the display name of
 * the test.
 */
public class MdcLoggingExtension implements BeforeEachCallback, AfterEachCallback {

    /**
     * Called before each test method execution.
     *
     * <p>This method constructs a unique test identifier and puts it into the MDC.
     *
     * @param context the extension context for the current test
     */
    @Override
    public void beforeEach(ExtensionContext context) {
        String testId = buildUniqTestIdValue(context);
        testId = truncateTestId(testId);

        // Put the unique identifier into MDC
        MDC.put(LoggingConstants.TEST_ID_KEY, testId);
    }

    /**
     * Called after each test method execution.
     *
     * <p>This method removes the test identifier from the MDC to prevent context leakage.
     *
     * @param context the extension context for the current test
     * @throws Exception if an error occurs
     */
    @Override
    public void afterEach(ExtensionContext context) throws Exception {
        // Remove the key to prevent context leakage between threads or test executions
        MDC.remove(LoggingConstants.TEST_ID_KEY);
    }

    /**
     * Constructs a unique, sanitized test identifier string from the extension context.
     *
     * <p>This method combines the test class simple name, the test method name, and a
     * sanitized version of the display name (removing any characters not safe for filenames).
     *
     * @param context the extension context of the current test
     * @return the constructed unique test ID string
     */
    private static @NonNull String buildUniqTestIdValue(ExtensionContext context) {
        String testClassName = context.getRequiredTestClass().getSimpleName();
        String testMethodName = context.getRequiredTestMethod().getName();
        // For @TestTemplate or parameterized tests, getDisplayName() returns the unique invocation name
        String displayName = context.getDisplayName();

        String sanitizedDisplayName = displayName.replaceAll("[^a-zA-Z0-9_\\-]", LoggingConstants.TEST_ID_DELIMITER)
                .replaceAll(LoggingConstants.TEST_ID_DELIMITER + "+", LoggingConstants.TEST_ID_DELIMITER)
                .trim();
        return String.join(LoggingConstants.TEST_ID_DELIMITER, testClassName, testMethodName, sanitizedDisplayName);
    }

    /**
     * Limits the length of the constructed test ID to prevent filesystem issues.
     *
     * <p>If the test ID is longer than the limit defined in {@link LoggingConstants#MAX_TEST_ID_LENGTH}, 
     * it is truncated. A hexadecimal hash suffix generated from the original test ID is appended 
     * to guarantee uniqueness and prevent collisions.
     *
     * @param testId the full constructed test ID to truncate
     * @return the truncated test ID (guaranteed to be under {@link LoggingConstants#MAX_TEST_ID_LENGTH} characters)
     */
    private static @NonNull String truncateTestId(String testId) {
        if (testId.length() > LoggingConstants.MAX_TEST_ID_LENGTH) {
            String hash = Integer.toHexString(testId.hashCode());
            int truncateIndex = LoggingConstants.MAX_TEST_ID_LENGTH - hash.length() - LoggingConstants.TEST_ID_DELIMITER.length();
            return testId.substring(0, truncateIndex) + LoggingConstants.TEST_ID_DELIMITER + hash;
        }
        return testId;
    }
}
