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

//    private Plateau p1;
//    private Plateau p2;
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
    public static final String abc = "abcdefghijklmnopqrstuvwxyz";

    public Printer() {
//        this.p1 = p1;
//        this.p2 = p2;
    }

    public void affiche(int[][] plat1, int[][] plat2) {
        System.out.println(getAffiche(plat1, plat2));
    }

    public void affiche(int[][] plat1, int[][] plat2, String info) {
        System.out.println(getAffiche(plat1, plat2) + info);
    }

    public String getAffiche(int[][] plat1, int[][] plat2) {

        //p1 bigger?
        boolean p1Bigger = (plat1.length >= plat2.length);

        String base = "                     BATAILLE NAVALE\n";

        base = (p1Bigger)
                ? base + "      Mon plateau                                 Son plateau\n"
                : base + "      Son plateau                                 Mon plateau\n";

        String headerP1 = " ";
        String headerP2 = " ";

        for (int i = 0; i < plat1[0].length; i++) {
            headerP1 += (i < 10) ? " " + i + " " : " " + i;
        }
        for (int i = 0; i < plat2[0].length; i++) {
            headerP2 += (i < 10) ? " " + i + " " : " " + i;
        }
        String header = (p1Bigger)
                ? headerP1 + "            " + headerP2 + "\n"
                : headerP2 + "            " + headerP1 + "\n";

        base += header;

        for (int i = 0; i < plat1.length; i++) {
            base += ABC.charAt(i);
            for (int j = 0; j < plat1[i].length; j++) {
                switch (plat1[i][j]) {
                    case 1:
                        base += ANSI_BLUE + " ▓" + ANSI_RESET + " "; //WATER
                        break;
                    case 2:
                        base += ANSI_GREEN + " o" + ANSI_RESET + " "; //FAIL SHOT
                        break;
                    case 3:
                        base += ANSI_RED + " h" + ANSI_RESET + " "; //GOOD SHOT
                        break;
                    case 4:
                        base += ANSI_YELLOW + " X" + ANSI_RESET + " "; //SUNK 
                        break;
                    case 5:
                        base += ANSI_PURPLE + " H" + ANSI_RESET + " "; //SUNK 
                        break;
                    default:
                        base += " ▓ ";
                        break;
                }
            }
            base += ABC.charAt(i);

            if (i < plat2.length) {
                //Print line's columns
                base += "           " + ABC.charAt(i);
                for (int j = 0; j < plat2[i].length; j++) {
                    switch (plat2[i][j]) {
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
                            base += ANSI_BLUE + " ▓ " + ANSI_RESET;
                            break;
                    }
                }
                base += ABC.charAt(i);
            }
            base += "\n";

        }

        return base + header;
    }

    public String monPlacement(int[][] plat1) {

        String base = "";

        String headerP1 = " ";

        for (int i = 0; i < plat1[0].length; i++) {
            headerP1 += (i < 10) ? " " + i + " " : " " + i;
        }

        base += headerP1;

        for (int i = 0; i < plat1.length; i++) {
            base += ABC.charAt(i);
            for (int j = 0; j < plat1[i].length; j++) {
                switch (plat1[i][j]) {
                    case 1:
                        base += ANSI_BLUE + " ▓" + ANSI_RESET + " "; //WATER
                        break;

                    case 5:
                        base += ANSI_PURPLE + " H" + ANSI_RESET + " "; //SHIP 
                        break;
                    default:
                        base += " ▓ ";
                        break;
                }
            }
            base += ABC.charAt(i);

            base += "\n";

        }

        return base + headerP1;
    }
}
