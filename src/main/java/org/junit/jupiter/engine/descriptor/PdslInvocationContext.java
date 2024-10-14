package org.junit.jupiter.engine.descriptor;

import com.pdsl.testcases.TestCase;
import org.junit.jupiter.api.extension.*;

import java.util.Collections;
import java.util.List;

final class PdslInvocationContext implements TestTemplateInvocationContext {

    private final PdslExecutable executable;

    PdslInvocationContext(PdslExecutable executable) {
        this.executable = executable;
    }

    @Override
    public String getDisplayName(int invocationIndex) {
        return executable.getTestTitle();
    }

    @Override
    public List<Extension> getAdditionalExtensions() {
        return Collections.singletonList(new ParameterResolver() {
            @Override
            public boolean supportsParameter(ParameterContext parameterContext,
                                             ExtensionContext extensionContext) {
                return parameterContext.getParameter().getType().equals(PdslExecutable.class);
            }

            @Override
            public Object resolveParameter(ParameterContext parameterContext,
                                           ExtensionContext extensionContext) {
                return executable;
            }
        });
    }
}
