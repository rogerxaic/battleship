package battleship;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Roger MIRET & Marta CORTÉS
 */
public class Battleship extends Outils {

    /**
     *
     */
    public static final Outils OUTIL = new Outils();

    /**
     *
     */
    public static final boolean IS_WINDOWS = System.getProperty("os.name").toLowerCase().contains("win");

    /**
     *
     */
    public static final String USERNAME = System.getProperty("user.name");

    /**
     * @param args the command line arguments
     * @throws java.io.IOException Quand on execute la méthode exe et qu'il y a
     * eu un erreur
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

        System.out.println("Bienvenue " + USERNAME);
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
        int width, height;
        String tailleSelected;
        while (true) {

            while (true) {
                tailleSelected = SCAN.next();
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
            int amplada = ((width * 3) + 2) * 2 + 11;
            int alsada = (height + 2 + 4 + 5);
            if (amplada <= cols && alsada <= lines) {
                break;
            }
            System.out.println(red("Cette taille risque de ne pas laisser rentrer les plateaux dans l'ecran.")
                    + ((!isInit) ? "\nIl faut démarrer le jeu avec " + green("./BatailleNavale") + ".\n"
                            + "Veuillez lire le fichier README.md, sinon la taille maximale est 13x10." : "")
                    + "\nTAILLE : ");
        }

        int nbBateaux;
        nbBateaux = surface2Bateaux(width, height);

        exe("clear");

        //PEDIR Adversaire
        String selectVs = "Combien de joueurs voulez vous jouer avec :\n"
                + "\t0. (contre Ordinateur)\n"
                + "\t#. (e.g. 2, vous et 2 autres)\n\n"
                //                + "\tN. Network playing " + bred("PAS IMPLEMENTÉ") + "\n\n"
                + "ADVERSAIRE : ";

        System.out.print(selectVs);
        boolean isComputer;
        boolean networking = false;
        int vsSelected = -1;
        while (true) {
            String inter = SCAN.next();
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
            Bateau a = new Bateau(i);
            flota1.put(getLettre(i), a);
        }

        Plateau p1 = new Plateau(height, width, flota1, USERNAME, false);
        tablero.put("A", p1);

        ////////////////////////////////////////////////////////////////////////
        String nomJoueur;
        if (!isComputer && !networking) {
            exe("clear");

            //On a un nombre de joueurs vsSelected.
            for (int i = 1; i < vsSelected + 1; i++) {
                int joueur = i + 1;
                System.out.print("Comment s'appelle-t-il le joueur " + joueur + " :\n\n"
                        + "NOM : ");
                nomJoueur = SCAN.next();

                HashMap<String, Bateau> flota = new HashMap<>();

                for (int j = 0; j < nbBateaux; j++) {
                    Bateau bato = new Bateau(j);
                    flota.put(getLettre(j), bato);
                }

                Plateau p2 = new Plateau(height, width, flota, nomJoueur, false);
                tablero.put("" + getLettre(i), p2);
            }

//            System.out.print(nomJoueur);
        } else if (isComputer) {
            nomJoueur = "Ordinateur";

            HashMap<String, Bateau> flota = new HashMap<>();

            for (int i = 0; i < nbBateaux; i++) {
                Bateau bato = new Bateau(i);
                flota.put(getLettre(i), bato);
            }

            Plateau p2 = new Plateau(height, width, flota, nomJoueur, true);
            tablero.put("B", p2);
        }

        exe("clear");

        while (true) {

            placerAll(tablero);

            clear();

            /**
             * Affichage des profs. Not working /w my code.
             * pg.afficher(p1.plateauGr());
             */
            //JEU
            do {

                /**
                 * Chaque joueur tire une fois, quand ils ont tous tiré, on
                 * recomence le while
                 */
                for (String entry : tablero.keySet()) {
                    clear();

                    Plateau tireur = tablero.get(entry); //tireur
//                    System.out.println(getAffiche(tireur.getState(), p1.getState()) + "");
                    HashMap<String, Plateau> clone = new HashMap((Map) tablero.clone());

                    System.out.println("Tir de " + tireur.getPropietari() + " : \n");
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
                            String adver = SCAN.next();
                            if (clone.containsKey(adver.toUpperCase())) {
                                cible = adver.toUpperCase();
                                break;
                            }

                            System.out.print("\nCe n'est pas une option.\nADVERSAIRE : ");

                        }
                    } else {
                        for (String placer : clone.keySet()) {
                            cible = placer;
                        }
                    }
                    clear();
                    Plateau target = tablero.get(cible);

                    clone.clear();

                    affiche(tireur, target);

                    String donde = /*"Tir de " + bcyan(tireur.getPropietari()) + */ "\nOù voulez vous tirer? LETTRE ou LETTRE+NUMÉRO : ";
                    boolean badShot = false, goodShot = false;
                    do {
                        int y = -1;
                        int x = -1;

                        /**
                         * On cherche les coordonnées où on va attacker.
                         */
                        if (!tireur.isComputer) {
                            if (badShot) {
                                System.out.print(red("Ce n'est pas possible de tirer dans cette case.\n") + donde);
                            } else if (goodShot) {
                                System.out.print("Où voulez vous tirer? LETTRE ou LETTRE+NUMÉRO : ");
                            } else {
                                System.out.print(donde);
                            }
                            badShot = false;
                            String letra = SCAN.next();

                            for (int i = 0; i < ABC.length(); i++) {
                                if (letra.toUpperCase().charAt(0) == ABC.charAt(i)) {
                                    y = i;
                                }
                            }

                            if (letra.length() > 1) {
                                x = Integer.parseInt(letra.substring(1));
                            } else {
                                System.out.print("NUMÉRO :");
                                String numero = SCAN.next();

                                x = Integer.parseInt("" + numero);
                            }
                        } else {

                            /**
                             * VIVE L'INTÉLIGENCE ARTIFICIELLE
                             */
                            y = RAND.nextInt(tireur.plateau.length);
                            x = RAND.nextInt(tireur.plateau[y].length);

                        }

                        /**
                         * On regarde si le tir est valide et on tire
                         */
                        if (target.isTirValide(x, y)) {
                            if (target.tir(x, y)) {
                                goodShot = true;
                                if (target.isIsLastCoule()) {
                                    tireur.addNbDeaths();
                                }

                                clear();
                                affiche(tireur, target);
                                //System.out.println("TIR DE " + bcyan(tireur.getPropietari().toUpperCase()));
                                System.out.println("Votre tir [" + getLettre(y) + x + "] a touché un bateau, vous avez le droit à un autre. ");
                            } else {
                                //The other one's turn
                                clear();
                                affiche(tireur, target);
                                if (!isComputer) {
                                    System.out.println("Tir à l'eau :/\nAppuyez sur [INTRO] pour laisser jouer le prochain joueur.");
                                    try {
                                        System.in.read();
                                    } catch (Exception e) {
                                    }
                                } else {
                                    clear();
                                }
                                break;
                            }
                        } else {
                            badShot = true;
                            clear();
                            affiche(tireur, target);
                        }
                    } while (!isGameOver(tablero));

                    if (isGameOver(tablero)) {
                        break;
                    }
                }
            } while (!isGameOver(tablero));

            /**
             * On cherche qui a coulé plus de bateaux.
             */
            String winner = "";
            int maxDeaths = 0;
            int nbWinners = 0;
            for (String onePlat : tablero.keySet()) {
                Plateau plat = tablero.get(onePlat);
                if (plat.nbDeaths > maxDeaths) {
                    maxDeaths = plat.nbDeaths;
                    winner = plat.getPropietari();
                    nbWinners = 1;
                } else if (plat.nbDeaths == maxDeaths) {
                    winner += " et " + plat.getPropietari();
                    nbWinners++;
                }
            }
            System.out.println(((nbWinners > 1) ? "Les gagneurs sont " : "Le gagneur est ") + winner + ".");

            System.out.println("Rejouer ? [YN] ");
            String rejouer = SCAN.next();
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

    /**
     * Méthode qui démande au joueur de placer tous ses bateaux. Le joueur a
     * l'option de les placer au hasard s'il veut.
     *
     * @param tablero
     * @throws IOException
     */
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
                String information = plat.getPropietari() + ", selectionnez bateau "/*[" + plat.getBateauxToSet() + " R] */ + "à placer : ";
                information += "\n" + plat.getListeBateauxToSet() + "BATEAU : ";

                System.out.print(monPlacement(plat.getStatus()) + "\n"
                        + ((errorBateau) ? errorBat + information : "")
                        + ((pasDePlace) ? errorPlace + information : "")
                        + ((!pasDePlace && !errorBateau) ? information : ""));
                errorBateau = false;
                pasDePlace = false;

                while (true) {
                    String ddd = SCAN.next();
                    String dd = "" + ddd.toUpperCase().charAt(0);
                    if (plat.flota.containsKey(dd) && !plat.flota.get(dd).isPositioned()) {
                        String donde = "\nCe bateau a une taille de "
                                + plat.flota.get(dd).getTaille() + " \n"
                                + "Où placer le bateau? LETTRE ou LETTRE+NUMÉRO : ";
                        System.out.print(donde);
                        while (true) {
                            String letra = SCAN.next();
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
                                String numero = SCAN.next();

                                x = Integer.parseInt("" + numero.charAt(0));
                            }
                            System.out.print("VERTICAL ou HORIZONTAL ? [V/H]");
                            String vouh = SCAN.next();
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

    /**
     *
     * @param command
     * @return
     * @throws IOException
     */
    public static String check(String command) throws IOException {
        String resultat = "";
        if (!IS_WINDOWS) {
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

    public static boolean isGameOver(HashMap<String, Plateau> tablero) {
        boolean gameOver = false;
        for (String key : tablero.keySet()) {
            gameOver |= tablero.get(key).isDeadAllBateaux();
        }
        return gameOver;
    }

}
