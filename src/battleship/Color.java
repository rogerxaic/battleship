package battleship;

/**
 *
 * @author Roger && Marta
 */
public class Color {

    /**
     * Terminal escape sequence to reset the parameters.
     */
    public static final String ANSI_RESET = "\u001B[0m";

    /**
     * Terminal escape sequence to change to bold.
     */
    public static final String ANSI_BOLD = "\u001B[1m";

    /**
     * Terminal escape sequence to decrease the bright.
     */
    public static final String ANSI_HALF_BRIGHT = "\u001B[2m";

    /**
     * Terminal escape sequence to ??? *idk, haven't tested*
     */
    public static final String ANSI_UNDERSCORE = "\u001B[4m";

    /**
     * Terminal escape sequence to ¿blink? *idk, not tested*
     */
    public static final String ANSI_BLINK = "\u001B[7m";

    /**
     * Terminal escape sequence to unset underline.
     */
    public static final String ANSI_UNDERLINE_OFF = "\u001B[24m";

    /**
     * Terminal escape sequence to unset blink.
     */
    public static final String ANSI_BLINK_OFF = "\u001B[25m";

    /**
     * Terminal escape sequence to change the text color to black.
     */
    public static final String ANSI_BLACK = "\u001B[30m";

    /**
     * Terminal escape sequence to change the text color to red.
     */
    public static final String ANSI_RED = "\u001B[31m";

    /**
     * Terminal escape sequence to change the text color to  green.
     */
    public static final String ANSI_GREEN = "\u001B[32m";

    /**
     * Terminal escape sequence to change the text color to yellow.
     */
    public static final String ANSI_YELLOW = "\u001B[33m";

    /**
     * Terminal escape sequence to change the text color to blue.
     */
    public static final String ANSI_BLUE = "\u001B[34m";

    /**
     * Terminal escape sequence to change the text color to purple.
     */
    public static final String ANSI_PURPLE = "\u001B[35m";

    /**
     * Terminal escape sequence to change the text color to cyan.
     */
    public static final String ANSI_CYAN = "\u001B[36m";

    /**
     * Terminal escape sequence to change the text color to white.
     */
    public static final String ANSI_WHITE = "\u001B[37m";

    /**
     * Terminal escape sequence to ???
     */
    public static final String ANSI_UNDERSCORE_ON = "\u001B[38m";

    /**
     * Terminal escape sequence to ???
     */
    public static final String ANSI_UNDERSCORE_OFF = "\u001B[39m";

    /**
     * Terminal escape sequence to change the background color to black.
     */
    public static final String ANSI_BG_BLACK = "\u001B[40m";

    /**
     * Terminal escape sequence to change the background color to red.
     */
    public static final String ANSI_BG_RED = "\u001B[41m";

    /**
     * Terminal escape sequence to change the background color to green.
     */
    public static final String ANSI_BG_GREEN = "\u001B[42m";

    /**
     * Terminal escape sequence to change the background color to yellow
     */
    public static final String ANSI_BG_YELLOW = "\u001B[43m";

    /**
     * Terminal escape sequence to change the background color to blue.
     */
    public static final String ANSI_BG_BLUE = "\u001B[44m";

    /**
     * Terminal escape sequence to change the background color to purple.
     */
    public static final String ANSI_BG_PURPLE = "\u001B[45m";

    /**
     * Terminal escape sequence to change the background color to cyan.
     */
    public static final String ANSI_BG_CYAN = "\u001B[46m";

    /**
     * Terminal escape sequence to change the background color to white.
     */
    public static final String ANSI_BG_WHITE = "\u001B[47m";

    /**
     * Terminal escape sequence to reset the background color to default
     */
    public static final String ANSI_BG_DEFAULT = "\u001B[49m";

    /**
     * Terminal escape sequence to clean the screen. 
     */
    public static final String ANSI_REMOVE = "\u001B[2J";

    /**
     * Terminal escape sequence to move the cursor to the left top corner.
     */
    public static final String ANSI_MOVE = "\u001B[H";

    /**
     * Terminal escape sequence of the terminal bell.
     */
    public static final String ANSI_BELL = "\u0007";

    /**
     * Constructeur.
     */
    public Color() {
    }

    /**
     * Méthode qui ajoute les 'terminal scape sequences' pour que le text change
     * son couleur à bleu.
     * @param text
     * @return
     */
    public static String blue(String text) {
        return ANSI_BLUE + text + ANSI_RESET;
    }

    /**
     * Méthode qui ajoute les 'terminal scape sequences' pour que le text change
     * son couleur à noir.
     * @param text
     * @return
     */
    public static String black(String text) {
        return ANSI_BLACK + text + ANSI_RESET;
    }

    /**
     * Méthode qui ajoute les 'terminal scape sequences' pour que le text change
     * son couleur à rouge.
     * @param text
     * @return
     */
    public static String red(String text) {
        return ANSI_RED + text + ANSI_RESET;
    }

    /**
     * Méthode qui ajoute les 'terminal scape sequences' pour que le text change
     * son couleur à vert.
     * @param text
     * @return
     */
    public static String green(String text ){
        return ANSI_GREEN + text + ANSI_RESET;
    }

    /**
     * Méthode qui ajoute les 'terminal scape sequences' pour que le text change
     * son couleur à jeune.
     * @param text
     * @return
     */
    public static String yellow(String text) {
        return ANSI_YELLOW + text + ANSI_RESET;
    }

    /**
     * Méthode qui ajoute les 'terminal scape sequences' pour que le text change
     * son couleur à violet.
     * @param text
     * @return
     */
    public static String purple(String text) {
        return ANSI_PURPLE + text + ANSI_RESET;
    }

    /**
     * Méthode qui ajoute les 'terminal scape sequences' pour que le text change
     * son couleur à cyan.
     * @param text
     * @return
     */
    public static String cyan(String text) {
        return ANSI_CYAN + text + ANSI_RESET;
    }

    /**
     * Méthode qui ajoute les 'terminal scape sequences' pour que le text change
     * son couleur à blanc.
     * @param text
     * @return
     */
    public static String white(String text) {
        return ANSI_WHITE + text + ANSI_RESET;
    }

    /**
     * Méthode qui ajoute les 'terminal scape sequences' pour que le fond change
     * son couleur à bleu.
     * @param text
     * @return
     */
    public static String bblue(String text) {
        return ANSI_BG_BLUE + text + ANSI_RESET;
    }

    /**
     * Méthode qui ajoute les 'terminal scape sequences' pour que le fond change
     * son couleur à noir.
     * @param text
     * @return
     */
    public static String bblack(String text) {
        return ANSI_BG_BLACK + text + ANSI_RESET;
    }

    /**
     * Méthode qui ajoute les 'terminal scape sequences' pour que le fond change
     * son couleur à rouge.
     * @param text
     * @return
     */
    public static String bred(String text) {
        return ANSI_BG_RED + text + ANSI_RESET;
    }

    /**
     * Méthode qui ajoute les 'terminal scape sequences' pour que le fond change
     * son couleur à vert.
     * @param text
     * @return
     */
    public static String bgreen(String text) {
        return ANSI_BG_GREEN + text + ANSI_RESET;
    }

    /**
     * Méthode qui ajoute les 'terminal scape sequences' pour que le fond change
     * son couleur à jeune.
     * @param text
     * @return
     */
    public static String byellow(String text) {
        return ANSI_BG_YELLOW + text + ANSI_RESET;
    }

    /**
     * Méthode qui ajoute les 'terminal scape sequences' pour que le fond change
     * son couleur à violet.
     * @param text
     * @return
     */
    public static String bpurple(String text) {
        return ANSI_BG_PURPLE + text + ANSI_RESET;
    }

    /**
     * Méthode qui ajoute les 'terminal scape sequences' pour que le fond change
     * son couleur à cyan.
     * @param text
     * @return
     */
    public static String bcyan(String text) {
        return ANSI_BG_CYAN + text + ANSI_RESET;
    }

    /**
     * Méthode qui ajoute les 'terminal scape sequences' pour que le fond change
     * son couleur à blanc.
     * @param text
     * @return
     */
    public static String bwhite(String text) {
        return ANSI_BG_WHITE + text + ANSI_RESET;
    }

    /**
     *
     * @return The terminal scape sequences to cleen the screen.
     */
    public static String clearScreen() {
        return ANSI_REMOVE + ANSI_MOVE;
    }

    /**
     * Méthode qui nettoie l'écran.
     */
    public static void clear() {
        System.out.print(clearScreen());
    }

    /**
     * Méthode qui active la cloche du terminal.
     */
    public static void bell() {
        System.out.print(ANSI_BELL);
    }
}
