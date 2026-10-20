package com.pdsl.junit.jupiter.extension;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.mockito.Mockito;
import org.slf4j.MDC;

import java.lang.reflect.Method;

import static com.google.common.truth.Truth.assertThat;
import static org.mockito.Mockito.when;

public class MdcLoggingExtensionTest {

    private MdcLoggingExtension extension;
    private ExtensionContext mockContext;

    // A dummy test class to use for reflection in test cases
    private static class DummyTestClass {
        public void dummyMethod() {
        }
    }

    @BeforeEach
    public void setUp() {
        extension = new MdcLoggingExtension();
        mockContext = Mockito.mock(ExtensionContext.class);
    }

    @AfterEach
    public void tearDown() {
        // Ensure we don't leak MDC context into other tests
        MDC.remove(LoggingConstants.TEST_ID_KEY);
    }

    @Test
    public void beforeEach_withNormalDisplayName_putsFormattedTestIdInMdc() throws NoSuchMethodException {
        // Arrange
        Class<?> testClass = DummyTestClass.class;
        Method testMethod = DummyTestClass.class.getDeclaredMethod("dummyMethod");
        String displayName = "dummyMethod()";

        when(mockContext.getRequiredTestClass()).thenAnswer(invocation -> testClass);
        when(mockContext.getRequiredTestMethod()).thenReturn(testMethod);
        when(mockContext.getDisplayName()).thenReturn(displayName);

        // Act
        extension.beforeEach(mockContext);

        // Assert
        // "dummyMethod()" has '()' replaced with '__', then collapsed to '_', so "dummyMethod_"
        // The expected testId is: "DummyTestClass_dummyMethod_dummyMethod_"
        String expectedTestId = "DummyTestClass_dummyMethod_dummyMethod_";
        assertThat(MDC.get(LoggingConstants.TEST_ID_KEY)).isEqualTo(expectedTestId);
    }

    @Test
    public void beforeEach_withSpecialCharactersDisplayName_sanitizesAndPutsFormattedTestIdInMdc() throws NoSuchMethodException {
        // Arrange
        Class<?> testClass = DummyTestClass.class;
        Method testMethod = DummyTestClass.class.getDeclaredMethod("dummyMethod");
        // Display name contains spaces, special characters, and consecutive underscores to test sanitization
        String displayName = "   My Special-Test #1!!!  ";

        when(mockContext.getRequiredTestClass()).thenAnswer(invocation -> testClass);
        when(mockContext.getRequiredTestMethod()).thenReturn(testMethod);
        when(mockContext.getDisplayName()).thenReturn(displayName);

        // Act
        extension.beforeEach(mockContext);

        // Assert
        // "   My Special-Test #1!!!  "
        // 1. replaceAll("[^a-zA-Z0-9_\\-]", "_") -> "___My_Special-Test__1_____"
        // 2. replaceAll("_+", "_") -> "_My_Special-Test_1_"
        // 3. trim() -> "_My_Special-Test_1_"
        String expectedTestId = "DummyTestClass_dummyMethod__My_Special-Test_1_";
        assertThat(MDC.get(LoggingConstants.TEST_ID_KEY)).isEqualTo(expectedTestId);
    }

    @Test
    public void beforeEach_withTooLongTestId_truncatesAndAppendsHash() throws NoSuchMethodException {
        // Arrange
        Class<?> testClass = DummyTestClass.class;
        Method testMethod = DummyTestClass.class.getDeclaredMethod("dummyMethod");
        // Create an exceptionally long display name (e.g. 200 characters)
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 20; i++) {
            sb.append("veryLongDisplayNameSegment");
        }
        String displayName = sb.toString();

        when(mockContext.getRequiredTestClass()).thenAnswer(invocation -> testClass);
        when(mockContext.getRequiredTestMethod()).thenReturn(testMethod);
        when(mockContext.getDisplayName()).thenReturn(displayName);

        // Act
        extension.beforeEach(mockContext);

        // Assert
        String actualTestId = MDC.get(LoggingConstants.TEST_ID_KEY);
        assertThat(actualTestId).isNotNull();
        assertThat(actualTestId.length()).isEqualTo(LoggingConstants.MAX_TEST_ID_LENGTH);

        // Calculate expected hash for full testId before truncation
        String fullTestId = String.join(LoggingConstants.TEST_ID_DELIMITER, testClass.getSimpleName(), testMethod.getName(), displayName);
        String expectedHash = Integer.toHexString(fullTestId.hashCode());
        assertThat(actualTestId).endsWith(LoggingConstants.TEST_ID_DELIMITER + expectedHash);
    }

    @Test
    public void afterEach_clearsTestIdFromMdc() throws Exception {
        // Arrange
        MDC.put(LoggingConstants.TEST_ID_KEY, "some-pre-existing-id");
        assertThat(MDC.get(LoggingConstants.TEST_ID_KEY)).isEqualTo("some-pre-existing-id");

        // Act
        extension.afterEach(mockContext);

        // Assert
        assertThat(MDC.get(LoggingConstants.TEST_ID_KEY)).isNull();
    }
}
