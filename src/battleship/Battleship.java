package battleship;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author Roger MIRET & Marta CORTÉS
 */
public class Battleship extends Outils {

    public static final Outils ou = new Outils();
    public static final Scanner sc = new Scanner(System.in);
    public static final String OS = System.getProperty("os.name").toLowerCase();
    public static final boolean isWindows = OS.contains("win");
    public static final String username = System.getProperty("user.name");

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        exe("start");
        exe("clear");

        boolean isInit = false;
        for (String s : args) {
            isInit |= s.contains("lc");
        }

        /**
         * When run with runner (i.e. ./BatailleNavale) we are able to get the
         * terminal size. If it is not run with runner, we suppose terminal size
         * to be minimal.
         *
         * TODO : Throw error message on size choice if screen is too small
         */
        int cols, lines;
        if (isInit) {
            cols = Integer.parseInt(check("tput cols"));
            lines = Integer.parseInt(check("tput lines"));

        } else {
            cols = 80;
            lines = 24;
        }

        Outils.Banner();

        System.out.println("Bienvenue " + username);
        /**
         * On cherche la taille du plateau
         *
         * Possibilité de taille perso
         */
        String selectTaille = "Introduisez la taille des plateaux :\n"
                + "(max. est 26x26)\n\n"
                + "\t1. 10x10\n"
                + "\t2. 15x10\n"
                + "\t3. 15x15\n"
                + "\t4. 15x20\n"
                + "\t5. 20x20\n\n"
                + "\tou #FILES X #COLONNES (e.g 8X18)\n\n"
                + "TAILLE : ";

        System.out.print(selectTaille);

        String tailleSelected;
        while (true) {
            tailleSelected = sc.next();
            if (isNumber(tailleSelected) && Integer.parseInt(tailleSelected) > 0 && Integer.parseInt(tailleSelected) < 6) {
                break;
            } else if (tailleSelected.toUpperCase().contains("X")) {

                int position = 0;
                for (int i = 0; i < tailleSelected.length(); i++) {
                    if (tailleSelected.toUpperCase().charAt(i) == 'X') {
                        position = i;
                        break;
                    }
                }

                if (isNumber(tailleSelected.substring(0, position))
                        && isNumber(tailleSelected.substring(position + 1))) {
                    int avant = Integer.parseInt(tailleSelected.substring(0, position));
                    int apres = Integer.parseInt(tailleSelected.substring(position + 1));
                    if ((avant > 0 && avant < 27) && (apres > 0 && apres < 27)) {
                        break;
                    }
                }
            }
            System.out.print("\nCe n'est pas une option.\nTAILLE : ");
        }
        int width, height;
        switch (tailleSelected) {
            case "1":
                width = 10;
                height = 10;
                break;
            case "2":
                width = 10;
                height = 15;
                break;
            case "3":
                width = 15;
                height = 15;
                break;
            case "4":
                width = 15;
                height = 20;
                break;
            case "5":
                width = 20;
                height = 20;
                break;
            default:
                if (tailleSelected.toUpperCase().contains("X")) {

                    int position = 0;
                    for (int i = 0; i < tailleSelected.length(); i++) {
                        if (tailleSelected.toUpperCase().charAt(i) == 'X') {
                            position = i;
                            break;
                        }
                    }

                    height = Integer.parseInt(tailleSelected.substring(0, position));
                    width = Integer.parseInt(tailleSelected.substring(position + 1));
                } else {
                    width = 10;
                    height = 10;
                }
                break;
        }

        int nbBateaux;
        nbBateaux = surface2Bateaux(width, height);

        exe("clear");

        //PEDIR Adversaire
        String selectVs = "Combien de joueurs voulez vous jouer avec :\n"
                + "\t0. (contre Ordinateur)\n"
                + "\t#. (e.g. 2, vous et 2 autres)\n\n"
                + "\tN. Network playing" + bred("PAS IMPLEMENTÉ") + "\n\n"
                + "ADVERSAIRE : ";

        System.out.print(selectVs);
        boolean isComputer;
        boolean networking = false;
        int vsSelected = -1;
        while (true) {
            String inter = sc.next();
            if (inter.toUpperCase().charAt(0) == 'N') {
                networking = true;
                break;
            } else if (isNumber(inter)) {
                vsSelected = Integer.parseInt(inter);
                break;
            }
            System.out.print("\nCe n'est pas une option.\nADVERSAIRE : ");
        }

        switch (vsSelected) {
            case 0:
                isComputer = true;
                break;
            default:
                isComputer = false;
                break;
        }
        /**
         * Création du HashMap qui contient tous les plateaux des joueurs
         */
        HashMap<String, Plateau> tablero = new HashMap<>();

        /**
         * Création du plateau du joueur principal (celui qui execute le jeu)
         */
        ////////////////////////////////////////////////////////////////////////
        HashMap<String, Bateau> flota1 = new HashMap<>();

        for (int i = 0; i < nbBateaux; i++) {
            Bateau a = new Bateau(i, true);
            flota1.put(getLletra(i), a);
        }

//        Bateau a1 = new Bateau(4); //4 BBBB V B8
//        Bateau b1 = new Bateau(2); //2 PP H G6
//        Bateau c1 = new Bateau(5); //5 AAAAA H I2
//        Bateau d1 = new Bateau(3); //3 DDD H C0
//        Bateau e1 = new Bateau(3); //3 SSS V E0
        Plateau p1 = new Plateau(height, width, flota1, username, false);
        tablero.put("A", p1);

        ////////////////////////////////////////////////////////////////////////
        String nomJoueur;
        if (!isComputer && !networking) {
            exe("clear");

            //On a un nombre de joueurs vsSelected.
            for (int i = 1; i < vsSelected + 1; i++) {
                int joueur = i + 1;
                System.out.println("Comment s'appelle-t-il le joueur " + joueur + " :\n\n"
                        + "NOM : ");
                nomJoueur = sc.next();

                HashMap<String, Bateau> flota = new HashMap<>();

                for (int j = 0; j < nbBateaux; j++) {
                    Bateau bato = new Bateau(j, true);
                    flota.put(getLletra(j), bato);
                }

                Plateau p2 = new Plateau(height, width, flota, nomJoueur, false);
                tablero.put("" + getLletra(i), p2);
            }

//            System.out.print(nomJoueur);
        } else if (isComputer) {
            nomJoueur = "Ordinateur";

            HashMap<String, Bateau> flota = new HashMap<>();

            for (int i = 0; i < nbBateaux; i++) {
                Bateau bato = new Bateau(i, true);
                flota.put(getLletra(i), bato);
            }

            Plateau p2 = new Plateau(height, width, flota, nomJoueur, true);
            tablero.put("B", p2);
        }

        exe("clear");

        /**
         * Affichage des profs. Not working /w my code.
         *
         *            //PlateauGraphique pg = new PlateauGraphique();
         *
         */
        while (true) {
//        1 porte-avions (5 cases)
//        1 croiseur (4 cases)
//        1 contre-torpilleurs (3 cases)
//        1 sous-marin (3 cases)
//        1 torpilleur (2 cases)

            placerAll(tablero);

            //exe("clear");
            clear();

            System.out.println(getAffiche(p1.getState(), p1.getState()) + "");
            //String ddd = sc.next();

            /**
             * Affichage des profs. Not working /w my code.
             * pg.afficher(p1.plateauGr());
             */
            //JEU
            while (true) {

                /**
                 *
                 */
                for (String entry : tablero.keySet()) {
                    clear();
                    
//                    dead |= tablero.get(entry).isDeadAllBateaux();
                    Plateau pla = tablero.get(entry);
                    System.out.println(getAffiche(pla.getState(), p1.getState()) + "");
                    HashMap<String, Plateau> clone = new HashMap((Map) tablero.clone());
//                    clone = tablero.clone();
                    System.out.println("Tir de " + pla.getPropietari() + " : ");
                    clone.remove(entry);

                    /**
                     * On cherche qui doit-on attacker. Si il y a plusieurs
                     * plateaux, il faut choisir quel plateau on veut attacker.
                     * S'il en y a qu'un, c'est pas la peine (^^'). On détermine
                     * target.
                     */
                    String cible = "";
                    if (clone.size() > 1) {
                        //Qui attacker?
                        String toAttack = "";
                        for (String placer : clone.keySet()) {
                            Plateau value = clone.get(placer);
                            String prop = value.getPropietari();
                            toAttack += " - [" + placer + "] " + prop;
                        }
                        System.out.println("Selectionnez qui attacker : " + toAttack.substring(3));

                        while (true) {
                            String adver = sc.next();
                            if (clone.containsKey(adver.toUpperCase())) {
                                System.out.println("BO!");
                                cible = adver.toUpperCase();

                                System.out.print("\nCe n'est pas une option.\nADVERSAIRE : ");
                            }
                        }
                    } else {
                        for (String placer : clone.keySet()) {
                            cible = placer;
                        }
                    }
                    Plateau target = tablero.get(cible);

                    clone.clear();

                    String donde = "\nOù voulez vous tirer? LETTRE ou LETTRE+NUMÉRO : ";
                    boolean badShot = false;
                    while (true) {
                        System.out.print((badShot)?red("Ce n'est pas possible de tirer dans cette case.")+donde :donde);
                        badShot = false;
                        String letra = sc.next();
                        int y = 0;
                        for (int i = 0; i < ABC.length(); i++) {
                            if (letra.toUpperCase().charAt(0) == ABC.charAt(i)) {
                                y = i;
                            }
                        }
                        int x;
                        if (letra.length() > 1) {
                            x = Integer.parseInt(letra.substring(1));
                        } else {
                            System.out.print("NUMÉRO :");
                            String numero = sc.next();

                            x = Integer.parseInt("" + numero);
                        }

                        if (target.tirer(x, y)) {
                            break;
                        } else {
                            badShot = true;
                        }
                    }

//                    sc.next();
                }

                boolean dead = false;
                for (String entry : tablero.keySet()) {
                    dead |= tablero.get(entry).isDeadAllBateaux();
                }
                if (dead) {
                    break;
                }
            }

            System.out.println("Rejouer ? [YN] ");
            String rejouer = sc.next();
            boolean rejeu = (rejouer.charAt(0) == 'Y' || rejouer.charAt(0) == 'y');
            if (!rejeu) {
                break;
            } else {
                for (String entry : tablero.keySet()) {
                    tablero.get(entry).reset();
                }
            }

        }
        //System.out.println("---" + ddd + "---");
        exe("finish");
    }

    public static void placerAll(HashMap<String, Plateau> tablero) throws IOException {
//        Color c = new Color();
        for (String entry : tablero.keySet()) {

            String key = entry;
//            Plateau value = tablero.get(key);
            exe("clear");

            Plateau plat = tablero.get(key);

            boolean errorBateau = false;
            boolean pasDePlace = false;

            if (plat.isComputer) {
                plat.placerAll();
            }

            while (!plat.isSetAllBateaux()) {

                exe("clear");

                String errorBat = red("Bateau incorrect/inexistant. \n");
                String errorPlace = red("Il n'y a pas de place. \n");
                String information = plat.getPropietari() + ", selectionnez bateau [" + plat.getBateauxToSet() + " R] à placer : ";

                System.out.print(monPlacement(plat.getState()) + "\n"
                        + ((errorBateau) ? errorBat + information : "")
                        + ((pasDePlace) ? errorPlace + information : "")
                        + ((!pasDePlace && !errorBateau) ? information : ""));
                errorBateau = false;
                pasDePlace = false;

                while (true) {
                    String ddd = sc.next();
                    String dd = "" + ddd.toUpperCase().charAt(0);
                    if (plat.flota.containsKey(dd) && !plat.flota.get(dd).isPositioned()) {
                        String donde = "\nCe bateau a une taille de "
                                + plat.flota.get(dd).getTaille() + " \n"
                                + "Où placer le bateau? LETTRE ou LETTRE+NUMÉRO : ";
                        System.out.print(donde);
                        while (true) {
                            String letra = sc.next();
                            int y = 0;
                            for (int i = 0; i < ABC.length(); i++) {
                                if (letra.toUpperCase().charAt(0) == ABC.charAt(i)) {
                                    y = i;
                                }
                            }
                            int x;
                            if (letra.length() > 1) {
                                x = Integer.parseInt(letra.substring(1));
                            } else {
                                System.out.print("NUMÉRO :");
                                String numero = sc.next();

                                x = Integer.parseInt("" + numero.charAt(0));
                            }
                            System.out.print("VERTICAL ou HORIZONTAL ? [V/H]");
                            String vouh = sc.next();
                            boolean horizontal = false;
                            horizontal = (vouh.charAt(0) == 'H' || vouh.charAt(0) == 'h');

                            if (plat.isRoom(x, y, horizontal, plat.flota.get(dd))) {
                                plat.setBateau(plat.flota.get(dd), x, y, horizontal);
                            } else {
                                pasDePlace = true;
                            }
                            break;

                        }
//                            System.out.println("TRUE");
                    } else if ("R".equals(dd)) {
                        plat.placerAll();
                    } else {
                        errorBateau = true;
                    }
                    break;
                }
            }
        }
    }

    public static String check(String command) throws IOException {
        String resultat = "";
        if (!isWindows) {
            Process p = Runtime.getRuntime().exec(command);
            try (BufferedReader br = new BufferedReader(
                    new InputStreamReader(p.getInputStream()))) {
                String line;
                while ((line = br.readLine()) != null) {
                    resultat += line;
                }
            }
        }
        return resultat;
    }

}
