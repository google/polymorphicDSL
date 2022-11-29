package com.pdsl.logging;

/** Utility class for colorizing terminal output. */
public class AnsiTerminalColorHelper {

		private AnsiTerminalColorHelper() {}
    private static final char ESCAPE_CHAR = 27;
    public static final String RESET = ESCAPE_CHAR + "\u001B[0m";
    public static final String BLACK = ESCAPE_CHAR + "\u001B[30m";
    public static final String RED = ESCAPE_CHAR + "\u001B[31m";
    public static final String GREEN = ESCAPE_CHAR + "\u001B[32m";
    public static final String YELLOW = ESCAPE_CHAR + "\u001B[33m";
    public static final String BLUE = ESCAPE_CHAR + "\u001B[34m";
    public static final String PURPLE = ESCAPE_CHAR + "\u001B[35m";
    public static final String CYAN = ESCAPE_CHAR + "\u001B[36m";
    public static final String WHITE = ESCAPE_CHAR + "\u001B[37m";

    public static final String BRIGHT_BLACK = ESCAPE_CHAR + "\u001B[90m";
    public static final String BRIGHT_RED = ESCAPE_CHAR +"\u001B[91m";
    public static final String BRIGHT_GREEN = ESCAPE_CHAR + "\u001B[92m";
    public static final String BRIGHT_YELLOW = ESCAPE_CHAR + "\u001B[93m";
    public static final String BRIGHT_BLUE = ESCAPE_CHAR + "\u001B[94m";
    public static final String BRIGHT_PURPLE = ESCAPE_CHAR + "\u001B[95m";
    public static final String BRIGHT_CYAN = ESCAPE_CHAR + "\u001B[96m";
    public static final String BRIGHT_WHITE = ESCAPE_CHAR + "\u001B[97m";


    public static final String BG_BLACK = ESCAPE_CHAR + "\u001B[40m";
    public static final String BG_RED = ESCAPE_CHAR + "\u001B[41m";
    public static final String BG_GREEN = ESCAPE_CHAR + "\u001B[42m";
    public static final String BG_YELLOW = ESCAPE_CHAR + "\u001B[43m";
    public static final String BG_BLUE = ESCAPE_CHAR + "\u001B[44m";
    public static final String BG_PURPLE = ESCAPE_CHAR + "\u001B[45m";
    public static final String BG_CYAN = ESCAPE_CHAR + "\u001B[46m";
    public static final String BG_WHITE = ESCAPE_CHAR + "\u001B[47m";

    public static final String BG_BRIGHT_BLACK = ESCAPE_CHAR + "\u001B[100m";
    public static final String BG_BRIGHT_RED = ESCAPE_CHAR + "\u001B[101m";
    public static final String BG_BRIGHT_GREEN = ESCAPE_CHAR + "\u001B[102m";
    public static final String BG_BRIGHT_YELLOW = ESCAPE_CHAR + "\u001B[103m";
    public static final String BG_BRIGHT_BLUE = ESCAPE_CHAR + "\u001B[104m";
    public static final String BG_BRIGHT_PURPLE = ESCAPE_CHAR + "\u001B[105m";
    public static final String BG_BRIGHT_CYAN = ESCAPE_CHAR + "\u001B[106m";
    public static final String BG_BRIGHT_WHITE = ESCAPE_CHAR +"\u001B[107m";

    public static final String BOLD = ESCAPE_CHAR + "\033[1m";
    public static final String FORMAT_STRIKETHROUGH = ESCAPE_CHAR + "\u001b[9m";
}
