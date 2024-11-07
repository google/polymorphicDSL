package com.pdsl.logging;

/** Utility class for colorizing terminal output. */
public class AnsiTerminalColorHelper {

		private AnsiTerminalColorHelper() {}
    private static final char ESCAPE_CHAR = 27;
    /** Clear all formatting introduced and revert to default. */
    public static final String RESET = ESCAPE_CHAR + "\u001B[0m";
    /** Color the terminal black. */
    public static final String BLACK = ESCAPE_CHAR + "\u001B[30m";
    /** Color the terminal red. */
    public static final String RED = ESCAPE_CHAR + "\u001B[31m";
    /** Color the terminal green. */
    public static final String GREEN = ESCAPE_CHAR + "\u001B[32m";
    /** Color the terminal yellow. */
    public static final String YELLOW = ESCAPE_CHAR + "\u001B[33m";
    /** Color the terminal blue. */
    public static final String BLUE = ESCAPE_CHAR + "\u001B[34m";
    /** Color the terminal purple. */
    public static final String PURPLE = ESCAPE_CHAR + "\u001B[35m";
    /** Color the terminal cyan. */
    public static final String CYAN = ESCAPE_CHAR + "\u001B[36m";
    /** Color the terminal white. */
    public static final String WHITE = ESCAPE_CHAR + "\u001B[37m";
	/** Color the terminal grey. */
    public static final String GREY = ESCAPE_CHAR + "\u001B[90m";

    /** Color the terminal bright black. */
    public static final String BRIGHT_BLACK = ESCAPE_CHAR + "\u001B[90m";
    /** Color the terminal bright red. */
    public static final String BRIGHT_RED = ESCAPE_CHAR +"\u001B[91m";
    /** Color the terminal bright green. */
    public static final String BRIGHT_GREEN = ESCAPE_CHAR + "\u001B[92m";
    /** Color the terminal bright yellow. */
    public static final String BRIGHT_YELLOW = ESCAPE_CHAR + "\u001B[93m";
    /** Color the terminal bright blue. */
    public static final String BRIGHT_BLUE = ESCAPE_CHAR + "\u001B[94m";
    /** Color the terminal bright purple. */
    public static final String BRIGHT_PURPLE = ESCAPE_CHAR + "\u001B[95m";
    /** Color the terminal bright cyan. */
    public static final String BRIGHT_CYAN = ESCAPE_CHAR + "\u001B[96m";
    /** Color the terminal bright white. */
    public static final String BRIGHT_WHITE = ESCAPE_CHAR + "\u001B[97m";

    /** Color the terminal background black. */
    public static final String BG_BLACK = ESCAPE_CHAR + "\u001B[40m";
    /** Color the terminal background red. */
    public static final String BG_RED = ESCAPE_CHAR + "\u001B[41m";
    /** Color the terminal background green. */
    public static final String BG_GREEN = ESCAPE_CHAR + "\u001B[42m";
    /** Color the terminal background yellow. */
    public static final String BG_YELLOW = ESCAPE_CHAR + "\u001B[43m";
    /** Color the terminal background blue. */
    public static final String BG_BLUE = ESCAPE_CHAR + "\u001B[44m";
    /** Color the terminal background purple. */
    public static final String BG_PURPLE = ESCAPE_CHAR + "\u001B[45m";
    /** Color the terminal background cyan. */
    public static final String BG_CYAN = ESCAPE_CHAR + "\u001B[46m";
    /** Color the terminal background white. */
    public static final String BG_WHITE = ESCAPE_CHAR + "\u001B[47m";

    /** Color the terminal background bright black. */
    public static final String BG_BRIGHT_BLACK = ESCAPE_CHAR + "\u001B[100m";
    /** Color the terminal background bright red. */
    public static final String BG_BRIGHT_RED = ESCAPE_CHAR + "\u001B[101m";
    /** Color the terminal background bright green. */
    public static final String BG_BRIGHT_GREEN = ESCAPE_CHAR + "\u001B[102m";
    /** Color the terminal background bright yellow. */
    public static final String BG_BRIGHT_YELLOW = ESCAPE_CHAR + "\u001B[103m";
    /** Color the terminal background bright blue. */
    public static final String BG_BRIGHT_BLUE = ESCAPE_CHAR + "\u001B[104m";
    /** Color the terminal background bright purple. */
    public static final String BG_BRIGHT_PURPLE = ESCAPE_CHAR + "\u001B[105m";
    /** Color the terminal background bright cyan. */
    public static final String BG_BRIGHT_CYAN = ESCAPE_CHAR + "\u001B[106m";
    /** Color the terminal background bright white. */
    public static final String BG_BRIGHT_WHITE = ESCAPE_CHAR +"\u001B[107m";

    /** Format the terminal text bold. */
    public static final String BOLD = ESCAPE_CHAR + "\033[1m";
    /** Format the terminal text with strikethrough. */
    public static final String FORMAT_STRIKETHROUGH = ESCAPE_CHAR + "\u001b[9m";
}
