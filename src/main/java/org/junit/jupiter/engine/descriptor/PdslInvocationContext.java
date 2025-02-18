package org.junit.jupiter.engine.descriptor;

import com.pdsl.testcases.TestCase;
import org.junit.jupiter.api.extension.*;

import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.extension.ExtensionContext.Namespace;

/**
 * An invocation context used by JUnit5 that has an embedded PDSL test.
 */
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
                String uniqueId = extensionContext.getUniqueId();
                Namespace namespace = Namespace.create(uniqueId);
                extensionContext.getStore(namespace).put(namespace, executable);

                return executable;
            }
        });
    }
}
