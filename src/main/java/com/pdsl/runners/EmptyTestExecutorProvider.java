package com.pdsl.runners;

import com.pdsl.executors.TraceableTestRunExecutor;

import javax.inject.Provider;

class EmptyTestExecutorProvider implements Provider<TraceableTestRunExecutor> {
    public EmptyTestExecutorProvider(){}
    @Override
    public TraceableTestRunExecutor get() {
        return null;
    }
}
