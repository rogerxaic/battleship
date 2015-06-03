/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package battleship;

/**
 *
 * @author Roger
 */
public class Color extends Thread {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BOLD = "\u001B[1m";
    public static final String ANSI_HALF_BRIGHT = "\u001B[2m";
    public static final String ANSI_UNDERSCORE = "\u001B[4m";
    public static final String ANSI_BLINK = "\u001B[7m";
    public static final String ANSI_UNDERLINE_OFF = "\u001B[24m";
    public static final String ANSI_BLINK_OFF = "\u001B[25m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";
    public static final String ANSI_UNDERSCORE_ON = "\u001B[38m";
    public static final String ANSI_UNDERSCORE_OFF = "\u001B[39m";
    public static final String ANSI_BG_BLACK = "\u001B[40m";
    public static final String ANSI_BG_RED = "\u001B[41m";
    public static final String ANSI_BG_GREEN = "\u001B[42m";
    public static final String ANSI_BG_YELLOW = "\u001B[43m";
    public static final String ANSI_BG_BLUE = "\u001B[44m";
    public static final String ANSI_BG_PURPLE = "\u001B[45m";
    public static final String ANSI_BG_CYAN = "\u001B[46m";
    public static final String ANSI_BG_WHITE = "\u001B[47m";
    public static final String ANSI_BG_DEFAULT = "\u001B[49m";
    public static final String ANSI_REMOVE = "\u001B[2J";
    public static final String ANSI_MOVE = "\u001B[H";
    public static final String ANSI_BELL = "\u0007";

    public Color() {
    }

    public static String blue(String noir) {
        return ANSI_BLUE + noir + ANSI_RESET;
    }

    public static String black(String noir) {
        return ANSI_BLACK + noir + ANSI_RESET;
    }

    public static String red(String noir) {
        return ANSI_RED + noir + ANSI_RESET;
    }

    public static String green(String noir) {
        return ANSI_GREEN + noir + ANSI_RESET;
    }

    public static String yellow(String noir) {
        return ANSI_YELLOW + noir + ANSI_RESET;
    }

    public static String purple(String noir) {
        return ANSI_PURPLE + noir + ANSI_RESET;
    }

    public static String cyan(String noir) {
        return ANSI_CYAN + noir + ANSI_RESET;
    }

    public static String white(String noir) {
        return ANSI_WHITE + noir + ANSI_RESET;
    }

    public static String bblue(String noir) {
        return ANSI_BG_BLUE + noir + ANSI_RESET;
    }

    public static String bblack(String noir) {
        return ANSI_BG_BLACK + noir + ANSI_RESET;
    }

    public static String bred(String noir) {
        return ANSI_BG_RED + noir + ANSI_RESET;
    }

    public static String bgreen(String noir) {
        return ANSI_BG_GREEN + noir + ANSI_RESET;
    }

    public static String byellow(String noir) {
        return ANSI_BG_YELLOW + noir + ANSI_RESET;
    }

    public static String bpurple(String noir) {
        return ANSI_BG_PURPLE + noir + ANSI_RESET;
    }

    public static String bcyan(String noir) {
        return ANSI_BG_CYAN + noir + ANSI_RESET;
    }

    public static String bwhite(String noir) {
        return ANSI_BG_WHITE + noir + ANSI_RESET;
    }

    public static String clearScreen() {
        return ANSI_REMOVE + ANSI_MOVE;
    }

    public static void clear() {
        System.out.print(clearScreen());
    }

    public static void bell() {
        System.out.print(getBell());
    }

    public static String getBell() {
        return ANSI_BELL;
    }
}
