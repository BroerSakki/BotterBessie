package com.utils;

public final class Ansi {

    private Ansi() {} // prevents instantiation

    // ================= RESET =================
    public static final String RESET = "\u001B[0m";

    // ================= COLORS =================
    public static final String BLACK  = "\u001B[30m";
    public static final String RED    = "\u001B[31m";
    public static final String GREEN  = "\u001B[32m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE   = "\u001B[34m";
    public static final String PURPLE = "\u001B[35m";
    public static final String CYAN   = "\u001B[36m";
    public static final String WHITE  = "\u001B[37m";

    // Bright
    public static final String BRIGHT_RED    = "\u001B[91m";
    public static final String BRIGHT_GREEN  = "\u001B[92m";
    public static final String BRIGHT_YELLOW = "\u001B[93m";
    public static final String BRIGHT_BLUE   = "\u001B[94m";

    // Backgrounds
    public static final String BG_RED    = "\u001B[41m";
    public static final String BG_GREEN  = "\u001B[42m";
    public static final String BG_YELLOW = "\u001B[43m";
    public static final String BG_BLUE   = "\u001B[44m";

    // Styles
    public static final String BOLD      = "\u001B[1m";
    public static final String UNDERLINE = "\u001B[4m";
    public static final String ITALIC    = "\u001B[3m";

    // =====================================================
    // CORE
    // =====================================================
    public static String format(String style, String text) {
        return style + text + RESET;
    }
    public static String format(String style, char text) {
        return style + text + RESET;
    }

    // =====================================================
    // COLORS
    // =====================================================
    public static String red(String text)    { return format(RED, text); }
    public static String green(String text)  { return format(GREEN, text); }
    public static String yellow(String text) { return format(YELLOW, text); }
    public static String blue(String text)   { return format(BLUE, text); }
    public static String cyan(String text)   { return format(CYAN, text); }
    public static String purple(String text) { return format(PURPLE, text); }

    public static String brightRed(String text)   { return format(BRIGHT_RED, text); }
    public static String brightGreen(String text) { return format(BRIGHT_GREEN, text); }

    // =====================================================
    // STYLES
    // =====================================================
    public static String bold(String text)      { return format(BOLD, text); }
    public static String underline(String text) { return format(UNDERLINE, text); }
    public static String italic(String text)    { return format(ITALIC, text); }

    // =====================================================
    // SEMANTIC HELPERS
    // =====================================================
    public static String error(String text) {
        return format(BRIGHT_RED + BOLD, "ERROR: " + text);
    }

    public static String success(String text) {
        return format(BRIGHT_GREEN + BOLD, "SUCCESS: " + text);
    }

    public static String warning(String text) {
        return format(YELLOW + BOLD, "WARNING: " + text);
    }

    public static String info(String text) {
        return format(CYAN, text);
    }
}
