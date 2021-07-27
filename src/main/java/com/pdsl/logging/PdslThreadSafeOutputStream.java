package com.pdsl.logging;

import java.io.*;
import java.nio.charset.Charset;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.SimpleFormatter;

/**
 * A default, Thread-Safe OutputStream used for TestExecutors to write output to.
 *
 * Using System.out directly is a security vulnerability that has lead to several exploits in Java
 * This class is backed by a logger to avoid the security issue
 *
 * Due to the class being thread safe it is not particularly efficient
 */
public class DefaultPdslLogger extends OutputStream {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(DefaultPdslLogger.class.getName());
    private final StringBuilder stringBuilder = new StringBuilder();
    static {
        logger.setUseParentHandlers(false);
        ConsoleHandler handler = new ConsoleHandler();

        handler.setFormatter(new SimpleFormatter() {
            private static final String format = "%s";

            @Override
            public synchronized String format(LogRecord lr) {
                return String.format(format, lr.getMessage());
            }
        });
        logger.addHandler(handler);
    }
    /** The internal memory for the written bytes. */
    private StringBuffer mem = new StringBuffer();

    @Override
    public void write( final int b ) {
        char c = (char) b;
        mem = mem.append(c);
        if (c == '\n') {
            flush();
            return;
        }
    }

    @Override
    public void flush() {
        logger.info( mem.toString() );
        mem = new StringBuffer();
    }
}
