package com.pdsl.runners;

import com.pdsl.runners.junit.JUnitConfigurationAccessor;
import com.pdsl.specifications.TestSpecification;
import com.pdsl.specifications.TestSpecificationFactory;

import javax.inject.Provider;
import java.lang.annotation.Annotation;
import java.net.URI;
import java.util.Collection;
import java.util.Optional;
import java.util.Set;

public class PdslConfigurationHelper {

    private static final ExecutorHelper INSTANCE = new ExecutorHelper();

    public static ExecutorHelper getExecutorHelper(JUnitConfigurationAccessor accessor) {
        return INSTANCE;
    }
}
