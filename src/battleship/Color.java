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
public class Color {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public Color() {
    }

    public String blue(String noir) {
        return ANSI_BLUE + noir + ANSI_RESET;
    }

    public String black(String noir) {
        return ANSI_BLACK + noir + ANSI_RESET;
    }

    public String red(String noir) {
        return ANSI_RED + noir + ANSI_RESET;
    }

    public String green(String noir) {
        return ANSI_GREEN + noir + ANSI_RESET;
    }

    public String yellow(String noir) {
        return ANSI_YELLOW + noir + ANSI_RESET;
    }

    public String purple(String noir) {
        return ANSI_PURPLE + noir + ANSI_RESET;
    }

    public String cyan(String noir) {
        return ANSI_CYAN + noir + ANSI_RESET;
    }
    
    public String white(String noir) {
        return ANSI_WHITE + noir + ANSI_RESET;
    }

}
