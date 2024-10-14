package org.junit.jupiter.engine.descriptor;

import com.pdsl.executors.InterpreterObj;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.Parser;

public record Interpreter(Class<? extends Lexer> lexer, Class<? extends Parser> parser,
                          InterpreterObj interpreterObj) {
}
