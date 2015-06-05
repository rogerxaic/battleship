package battleship;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;
import java.util.Scanner;

/**
 * Differents outils qu'on utilise lors qu'on execute le programme. Des outils
 * pour afficher les tableaux, vérifier si un String est bien un numéro, faire
 * un "clean" de l'écran, etc.
 * @author Roger && Marta
 */
public class Outils extends Color {

    /**
     * String qui contient l'alphabet.
     */
    public static final String ABC = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    /**
     * Objet Random d'où on obtient des entiers et booleans random.
     */
    public static final Random RAND = new Random();

    /**
     * Objet Scanner dont on s'en sert pour lire les données que l'utilisateur
     * va entrer dans programme.
     */
    public static final Scanner SCAN = new Scanner(System.in);

    /**
     * Méthode qui affiche 2 plateaux.
     * @param plat1 Plateau du joueur.
     * @param plat2 Plateau de l'adversaire.
     */
    public static void affiche(Plateau plat1, Plateau plat2) {
        System.out.println(getAffiche(plat1, plat2));
    }

    /**
     * Méthode qui affiche 2 plateaux et un texte en plus (e.g. des instructions).
     * @param plat1 Plateau du joueur.
     * @param plat2 Plateau de l'adversaire.
     * @param text Texte qu'on veut afficher après avoir affiché les plateaux.
     */
    public static void affiche(Plateau plat1, Plateau plat2, String text) {
        System.out.println(getAffiche(plat1, plat2) +"\n"+ text);
    }

    /**
     * Méthode qui affiche le Banner de bienvenue.
     * @throws IOException ...
     */
    public static void Banner() throws IOException {
        System.out.println(getBanner());

        try {
            System.in.read();
        } catch (Exception e) {
        }

        exe("clear");
    }

    /**
     *
     * @param command Commande qu'on veut executer dans le système, passée par un 
     * filtre.
     * @throws IOException ...
     */
    public static void exe(String command) throws IOException {
        String OS = System.getProperty("os.name").toLowerCase();
        boolean isWindows = OS.contains("win");

        String toPass = "";

        if (!isWindows) {

            switch (command) {
                case "clear":
                    toPass = (isWindows) ? ":" : "clear";
                    break;
                case "start":
                    toPass = (isWindows) ? ":" : "tput smcup";
                    break;
                case "finish":
                    toPass = (isWindows) ? ":" : "tput rmcup";
                    break;
                case "ifconfig":
                    toPass = (isWindows) ? "ipconfig" : "ifconfig | grep inet\\ ";
                    break;
                default:
                    toPass = ":"; // ':' est la commande qui ne fait rien.
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

    /**
     *
     * @param plat Plateau du joueur
     * @param plato Plateau de l'adversaire
     * @return Les deux plateux (joueur et adversaire) dans un format human-readable
     * pour qu'on puisse les afficher a l'écran.
     */
    public static String getAffiche(Plateau plat, Plateau plato) {

        int[][] plat1 = plat.getStatus();
        int[][] plat2 = plato.getStatus();

        String base = "                     BATAILLE NAVALE\n\n";

        base += "Tir de " + bcyan(plat.getPropietari()) + "\n\n";

        base = base + "      " + plat.getPropietari() + "                                       " + plato.getPropietari() + "\n";

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
            base += getLettre(i);
            for (int j = 0; j < plat1[i].length; j++) {
                switch (plat1[i][j]) {
                    case 1:
                        base += ANSI_BLUE + " ·" + ANSI_RESET + " "; //WATER
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
                        base += ANSI_PURPLE + " S" + ANSI_RESET + " "; //SUNK 
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
                            base += ANSI_BLUE + " ·" + ANSI_RESET + " ";
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
                            base += ANSI_BLUE + " · " + ANSI_RESET;
                            break;
                    }
                }
                base += ABC.charAt(i);
            }
            base += "\n";

        }

        return base + header;
    }

    /**
     *
     * @return Le banner de bienvenue en format string.
     */
    public static String getBanner() {

        String[] banner = new String[22];

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
        banner[21] = "              [Press ENTER to continue]                   ";

        String resultat = "";
        for (int i = 0; i < banner.length; i++) {
            resultat += banner[i] + "\n";
        }
        return resultat;
    }

    /**
     * Méthode qui renvoie la lettre de l'alphabet qui correspond a un numéro donné
     * en paramètre. Après le Z, elle renvoie AA, AB, AC, etc.
     * 
     * @param numero Numéro qu'on veut transformer en lettre de l'alphabet.
     * @return Lettre de l'alphabet qui est en position 'numero'.
     */
    public static String getLettre(int numero) {
        String resultat;
        for (resultat = ""; numero >= 0; numero = Integer.parseInt("" + (numero / 26)) - 1) {
            resultat = (char) (numero % 26 + 0x41) + resultat;
        }
        return resultat;
    }

    /**
     *
     * @param string String qu'on veut savoir si s'agit d'un numéro.
     * @return True si le string est un numéro, false sinon.
     */
    public static boolean isNumber(String string) {
        try {
            Long.parseLong(string);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     *
     * @param plat1 Plateau dont on veut connaître le placement des bateaux.
     * @return Le plateau dans un format human-readable pour qu'on puisse l'afficher.
     */
    public static String monPlacement(int[][] plat1) {

        String base = "";

        String headerP1 = " ";

        for (int i = 0; i < plat1[0].length; i++) {
            headerP1 += (i < 10) ? " " + i + " " : " " + i;
        }

        base += headerP1 + "\n";

        for (int i = 0; i < plat1.length; i++) {
            base += getLettre(i);
            for (int j = 0; j < plat1[i].length; j++) {
                switch (plat1[i][j]) {
                    case 1:
                        base += ANSI_BLUE + " ·" + ANSI_RESET + " "; //WATER
                        break;

                    case 5:
                        base += ANSI_PURPLE + " H" + ANSI_RESET + " "; //SHIP 
                        break;
                    default:
                        base += " · ";
                        break;
                }
            }
            base += getLettre(i);

            base += "\n";

        }

        return base + headerP1;
    }
    
    /**
     *
     * @param plat1 Plateau dont on veut connaître le placement des bateaux.
     * @param triche Plateau avec Water ou Bateau (true)
     * @return Le plateau dans un format human-readable pour qu'on puisse l'afficher.
     */
    public static String monPlacementTrichage(int[][] plat1, boolean[][] triche) {

        String base = "";

        String headerP1 = " ";

        for (int i = 0; i < plat1[0].length; i++) {
            headerP1 += (i < 10) ? " " + i + " " : " " + i;
        }

        base += headerP1 + "\n";

        for (int i = 0; i < plat1.length; i++) {
            base += getLettre(i);
            for (int j = 0; j < plat1[i].length; j++) {
                switch (plat1[i][j]) {
                    case 1:
                        base += ANSI_BLUE + " ·" + ANSI_RESET + " "; //WATER
                        break;

                    case 5:
                        base += ANSI_PURPLE + " H" + ANSI_RESET + " "; //SHIP 
                        break;
                    default:
                        base += " · ";
                        break;
                }
                
                base += "         " + ((triche[i][j])?"T":"F");
            }
            base += getLettre(i);

            base += "\n";

        }

        return base + headerP1;
    }

    /**
     *
     * @param surface La surface du plateau.
     * @return Le numéro de bateaux qu'on va mettre dans un plateau de surface surface.
     */
    public static int surface2Bateaux(int surface) {
        int resultat = 5;

        if (surface < 25) {
            resultat = 1;
        } else if (surface >= 25 && surface < 50) {
            resultat = 2;
        } else if (surface >= 50 && surface < 75) {
            resultat = 3;
        } else if (surface >= 75 && surface < 100) {
            resultat = 4;
        } else if (surface >= 100 && surface < 175) {
            resultat = 5;
        } else if (surface >= 175 && surface < 225) {
            resultat = 6;
        } else if (surface >= 225 && surface < 300) {
            resultat = 7;
        } else if (surface >= 300 && surface < 400) {
            resultat = 8;
        } else if (surface >= 400 && surface < 550) {
            resultat = 8;
        } else if (surface >= 550 && surface < 600) {
            resultat = 9;
        } else if (surface >= 600) {
            resultat = 10;
        } else {
            resultat = 5;
        }

        return resultat;
    }

    /**
     *
     * @param width La largeur du plateau.
     * @param height L'hauteur du plateau.
     * @return La surface d'un plateau de dimensions width et height.
     */
    public static int surface2Bateaux(int width, int height) {
        return surface2Bateaux(height * width);
    }
}
