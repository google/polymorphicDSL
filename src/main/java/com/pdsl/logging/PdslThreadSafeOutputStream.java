package com.pdsl.logging;

import java.io.IOException;
import java.io.OutputStream;
import java.util.logging.ConsoleHandler;
import java.util.logging.LogRecord;
import java.util.logging.SimpleFormatter;

/**
 * A default, Thread-Safe OutputStream used for TestExecutors to write output to.
 *
 * <p>Using System.out directly is a security vulnerability that has lead to several exploits in Java.
 * This class is backed by a logger to avoid the security issue.
 */
public final class PdslThreadSafeOutputStream extends OutputStream {

    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(PdslThreadSafeOutputStream.class.getName());

    static {
        // Modify the logger so it will only print the message and NOT the timestamp, logging level, etc
        logger.setUseParentHandlers(false);
        ConsoleHandler handler = new ConsoleHandler();

        handler.setFormatter(new SimpleFormatter() {
            @Override
            public synchronized String format(LogRecord lr) {
                return String.format("%s", lr.getMessage());
            }
        });
        logger.addHandler(handler);
    }

    //The internal memory for the written bytes.
    //String builder is unsynchronized and faster than StringBuffer, but is made thread safe by being local to each thread
    private ThreadLocal<StringBuilder> mem = new ThreadLocal<>();

    @Override
    public void write( final int b ) {
        char c = (char) b;
        mem.get().append(c);
        flush();
    }

    @Override
    public void write(byte[] bytes, int start, int stop) {
        String message = new String(bytes);
        // Multibyte characters may require us to have an earlier stop point
        //TODO: This is a bug. Find a way to handle character encoding instead of assuming
        // the caller wants to write the full array logged
        message = new String(bytes).substring(start, stop <= message.length() ? stop : message.length());
        logger.info(message);
    }

    @Override
    public void flush() {
        String message = mem.toString();
        logger.info(message);
        mem.get().setLength(0);
    }

    @Override
    public void close() throws IOException {
        super.close();
        mem.remove();
        mem = null;
    }
}
