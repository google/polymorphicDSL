package com.pdsl.runners;

import com.pdsl.runners.junit.JUnitConfigurationAccessor;
import org.junit.jupiter.engine.descriptor.JupiterDescriptorKey;

/**
 * A helper class used to limit the instantiation and use of the @link{com.pdls.runners.ExecutorHelper} class.
 *
 * This is purely for use of the PDSL framework and has no direct value to users of the framework.
 */
public class PdslConfigurationHelper {

    private static final ExecutorHelper INSTANCE = new ExecutorHelper();

    public static ExecutorHelper getExecutorHelper(JUnitConfigurationAccessor accessor) {
        return INSTANCE;
    }
    public static ExecutorHelper getExecutorHelper(JupiterDescriptorKey accessor) { return INSTANCE; }
}
