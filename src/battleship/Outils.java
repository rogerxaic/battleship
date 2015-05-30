/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package battleship;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author Roger
 */
public class Outils extends Color {

//    private Plateau p1;
//    private Plateau p2;
    public static final String ABC = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final String abc = "abcdefghijklmnopqrstuvwxyz";

    protected static String[] banner = new String[23];

    public Outils() {
//        this.p1 = p1;
//        this.p2 = p2;

        banner[0] = "   888888b.            888             d8b 888 888          ";
        banner[1] = "   888  \"88b           888             Y8P 888 888          ";
        banner[2] = "   888  .88P           888                 888 888          ";
        banner[3] = "   8888888K.   8888b.  888888  8888b.  888 888 888  .d88b.  ";
        banner[4] = "   888  \"Y88b     \"88b 888        \"88b 888 888 888 d8P  Y8b ";
        banner[5] = "   888    888 .d888888 888    .d888888 888 888 888 88888888 ";
        banner[6] = "   888   d88P 888  888 Y88b.  888  888 888 888 888 Y8b.     ";
        banner[7] = "   8888888P\"  \"Y888888  \"Y888 \"Y888888 888 888 888  \"Y8888  ";
        banner[8] = "                                                         ";
        banner[9] = "                                                         ";
        banner[10] = "                                                         ";
        banner[11] = "                                       888                  ";
        banner[12] = "                                       888                  ";
        banner[13] = "                                       888                  ";
        banner[14] = "   88888b.   8888b.  888  888  8888b.  888  .d88b.          ";
        banner[15] = "   888 \"88b     \"88b 888  888     \"88b 888 d8P  Y8b         ";
        banner[16] = "   888  888 .d888888 Y88  88P .d888888 888 88888888         ";
        banner[17] = "   888  888 888  888  Y8bd8P  888  888 888 Y8b.             ";
        banner[18] = "   888  888 \"Y888888   Y88P   \"Y888888 888  \"Y8888          ";
        banner[19] = "                                                         ";
        banner[20] = "                                                         ";
        banner[21] = "                                                         ";
        banner[22] = "              [Press ENTER to continue]                   ";

    }

    public static void affiche(int[][] plat1, int[][] plat2) {
        System.out.println(getAffiche(plat1, plat2));
    }

    public static void affiche(int[][] plat1, int[][] plat2, String info) {
        System.out.println(getAffiche(plat1, plat2) + info);
    }

    public static void Banner() throws IOException {
        System.out.println(getBanner());

        try {
            System.in.read();
        } catch (Exception e) {
        }

        exe("clear");

    }

    public static void exe(String command) throws IOException {
        String OS = System.getProperty("os.name").toLowerCase();
        boolean isWindows = OS.contains("win");

        String toPass = "";

        if (!isWindows) {

            switch (command) {
                case "clear":
                    toPass = (isWindows) ? "" : "clear";
                    break;
                case "start":
                    toPass = (isWindows) ? "" : "tput smcup";
                    break;
                case "finish":
                    toPass = (isWindows) ? "" : "tput rmcup";
                    break;
                default:
                    toPass = "";
                    break;
            }

            //        J'ai créé le fichier clear.exe (trouvable dans 
            //                                        ./external/clear/bin/Debug)
            //        J'ai placé ce fichier dans System32 pour que Windows ne 
            //        donne pas d'erreurs. La commande clear est cls, mais NetBeans ne veut
            //        pas la prendre car c'est cmd qui interprète cls, donc n'est pas un 
            //        executable Windows que NetBeans puisse executer.
            Process p = Runtime.getRuntime().exec(toPass);

            try (BufferedReader br = new BufferedReader(
                    new InputStreamReader(p.getInputStream()))) {

                String line;
                while ((line = br.readLine()) != null) {
                    System.out.println(line);
                }
            }
        }

    }

    public static String getAffiche(int[][] plat1, int[][] plat2) {

        String base = "                     BATAILLE NAVALE\n";

        base = base + "      Mon plateau                                 Son plateau\n";

        String headerP1 = " ";
        String headerP2 = " ";

        for (int i = 0; i < plat1[0].length; i++) {
            headerP1 += (i < 10) ? " " + i + " " : " " + i;
        }
        for (int i = 0; i < plat2[0].length; i++) {
            headerP2 += (i < 10) ? " " + i + " " : " " + i;
        }
        String header = headerP1 + "            " + headerP2 + "\n";

        base += header;

        for (int i = 0; i < plat1.length; i++) {
            base += getLletra(i);
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

    public static String getBanner() {
        String resultat = "";
        for (int i = 0; i < banner.length; i++) {
            resultat += banner[i] + "\n";
        }
        return resultat;
    }

    public static String getLettre(int x) {
        String letra = "";
        if (x < 26) {
            letra = "" + ABC.charAt(x);
        } else {
            int fois = 0;
            while (x >= 0) {
                letra = "" + ABC.charAt(x % 26);
                x -= 26;
                fois++;
            }
            if (fois < 26) {
                letra = ABC.charAt(fois - 2) + letra;
            }
        }
        return letra;
    }

    public static String getLletra(int x) {
        String resultat;
        for (resultat = ""; x >= 0; x = Integer.parseInt("" + (x / 26)) - 1) {
            resultat = (char) (x % 26 + 0x41) + resultat;
        }
        return resultat;
    }

    public static boolean isNumber(String string) {
        try {
            Long.parseLong(string);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
    
    public static String monPlacement(int[][] plat1) {

        String base = "";

        String headerP1 = " ";

        for (int i = 0; i < plat1[0].length; i++) {
            headerP1 += (i < 10) ? " " + i + " " : " " + i;
        }

        base += headerP1 + "\n";

        for (int i = 0; i < plat1.length; i++) {
            base += getLletra(i);
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
            base += getLletra(i);

            base += "\n";

        }

        return base + headerP1;
    }
}
