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
public class Printer {

    private Plateau p1;
    private Plateau p2;
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";
    public static final String ABC = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public Printer(Plateau p1, Plateau p2) {
        this.p1 = p1;
        this.p2 = p2;
    }

    public void affiche() {
        System.out.println(getAffiche());
    }

    public String getAffiche() {

        //p1 bigger?
        boolean p1Bigger = (p1.plateau.length > p2.plateau.length);

        String base = "                     BATAILLE NAVALE\n";

        base = (p1Bigger)
                ? base + "      Mon plateau			Son plateau\n"
                : base + "      Son plateau			Mon plateau\n";

        String headerP1 = " ";
        String headerP2 = " ";

        for (int i = 0; i < p1.plateau[0].length; i++) {
            headerP1 += " "+i+" ";
        }
        for (int i = 0; i < p2.plateau[0].length; i++) {
            headerP2 += " " +i+" ";
        }
        String header = (p1Bigger)
                ? headerP1 + "            " + headerP2 + "\n"
                : headerP2 + "            " + headerP1 + "\n";

        base += header;

        if (p1Bigger) {
            for (int i = 0; i < p1.plateau.length; i++) {
                base += ABC.charAt(i) ;
                for (int j = 0; j < p1.plateau[i].length; j++) {
                    switch (p1.plateau[i][j]) {
                        case 1:
                            base += ANSI_BLUE + " ▓" + ANSI_RESET + " ";
                            break;
                        case 2:
                            base += ANSI_GREEN + " o" + ANSI_RESET + " ";
                            break;
                        case 3:
                            base += ANSI_RED + " h" + ANSI_RESET + " ";
                            break;
                        case 4:
                            base += ANSI_YELLOW + " x" + ANSI_RESET + " ";
                            break;
                        default:
                            base += " ▓ ";
                            break;
                    }
                }
                base += ABC.charAt(i);

                if (i < p2.plateau.length) {
                    //Print line's columns
                    base += "           " + ABC.charAt(i);
                    for (int j = 0; j < p2.plateau[i].length; j++) {
                        switch (p2.plateau[i][j]) {
                            case 1:
                                base += ANSI_BLUE + " ▓" + ANSI_RESET + " ";
                                break;
                            case 2:
                                base += ANSI_GREEN + " o" + ANSI_RESET + " ";
                                break;
                            case 3:
                                base += ANSI_RED + " h" + ANSI_RESET + " ";
                                break;
                            case 4:
                                base += ANSI_YELLOW + " x" + ANSI_RESET + " ";
                                break;
                            default:
                                base += " ▓ ";
                                break;
                        }
                    }
                    base += ABC.charAt(i);
                }
                base += "\n";

            }

        } else {
            for (int i = 0; i < p2.plateau.length; i++) {
                base += ABC.charAt(i);
                for (int j = 0; j < p2.plateau[i].length; j++) {
                    switch (p2.plateau[i][j]) {
                        case 1:
                            base += ANSI_BLUE + " ▓" + ANSI_RESET + " ";
                            break;
                        case 2:
                            base += ANSI_GREEN + " o" + ANSI_RESET + " ";
                            break;
                        case 3:
                            base += ANSI_RED + " h" + ANSI_RESET + " ";
                            break;
                        case 4:
                            base += ANSI_YELLOW + " x" + ANSI_RESET + " ";
                            break;
                        default:
                            base += " ▓ ";
                            break;
                    }
                }
                base += ABC.charAt(i);

                if (i < p1.plateau.length) {
                    //Print line's columns
                    base += "           " + ABC.charAt(i) + " ";
                    for (int j = 0; j < p1.plateau[i].length; j++) {
                        switch (p1.plateau[i][j]) {
                            case 1:
                                base += ANSI_BLUE + " ▓" + ANSI_RESET + " ";
                                break;
                            case 2:
                                base += ANSI_GREEN + " o" + ANSI_RESET + " ";
                                break;
                            case 3:
                                base += ANSI_RED + " h" + ANSI_RESET + " ";
                                break;
                            case 4:
                                base += ANSI_YELLOW + " x" + ANSI_RESET + " ";
                                break;
                            default:
                                base += " ▓ ";
                                break;
                        }
                    }
                    base += ABC.charAt(i);
                }
                base += "\n";

            }

        }

        return base;
    }
}
