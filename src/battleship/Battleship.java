package battleship;

import java.io.*;
import java.util.*;

/**
 *
 * @author Roger MIRET & Marta CORTÉS
 */
public class Battleship {

    public static final String ABC = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final String abc = "abcdefghijklmnopqrstuvwxyz";
    public static final Color c = new Color();
    public static final Printer pr = new Printer();
    public static final Scanner sc = new Scanner(System.in);
    

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        exe("start");
        exe("clear");
        String username = System.getProperty("user.name");
        

        Panneau banner = new Panneau();

        banner.Banner();

        System.out.println("Bienvenue " + username);
        //PEDIR TAILLE PLATEAUX
        String selectTaille = "Introduisez la taille des plateaux :\n"
                + "\t1. 10x10\n"
                + "\t2. 15x10\n"
                + "\t3. 15x15\n"
                + "\t4. 15x20\n"
                + "\t5. 20x20\n\n"
                + "TAILLE : ";

        System.out.print(selectTaille);
        boolean isNumero = false;
        int tailleSelected = 0;
        while (!isNumero) {
            tailleSelected = sc.nextInt();
            if (tailleSelected > 0 && tailleSelected < 6) {
                break;
            }
            System.out.print("\nCe n'est pas une option.\nTAILLE : ");
        }
        int width, height;
        switch (tailleSelected) {
            case 1:
                width = 10;
                height = 10;
                break;
            case 2:
                width = 10;
                height = 15;
                break;
            case 3:
                width = 15;
                height = 15;
                break;
            case 4:
                width = 15;
                height = 20;
                break;
            case 5:
                width = 20;
                height = 20;
                break;
            default:
                width = 10;
                height = 10;
                break;
        }

        exe("clear");

        //PEDIR Adversaire
        String selectVs = "Combien de joueurs voulez vous jouer avec :" + c.bred("  --PAS COMPLÈTEMENT IMPLEMENTÉ (MAX 2)--  ") + "\n"
                + "\t0. (contre Ordinateur)\n"
                + "\t#. (e.g. 2, vous et 2 autre)\n\n"
                + "\tN. Network playing" + c.bred("PAS IMPLEMENTÉ") + "\n\n"
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

        String nomJoueur = "Comment s'appelle-t-il le joueur 2 :\n\n"
                + "NOM : ";
        if (!isComputer) {
            exe("clear");

            System.out.print(nomJoueur);

            nomJoueur = sc.next();
        } else {
            nomJoueur = "Ordinateur";
        }

        exe("clear");

        HashMap<String, Plateau> tablero = new HashMap<>();

        HashMap<String, Bateau> flota1 = new HashMap<>();
        HashMap<String, Bateau> flota2 = new HashMap<>();

        /**
         * Affichage des profs. Not working /w my code.
         *
         * PlateauGraphique pg = new PlateauGraphique();
         */
        Bateau a1 = new Bateau(4); //4 BBBB V B8
        flota1.put("A", a1);
        Bateau b1 = new Bateau(2); //2 PP H G6
        flota1.put("B", b1);
        Bateau c1 = new Bateau(5); //5 AAAAA H I2
        flota1.put("C", c1);
        Bateau d1 = new Bateau(3); //3 DDD H C0
        flota1.put("D", d1);
        Bateau e1 = new Bateau(3); //3 SSS V E0
        flota1.put("E", e1);

        Bateau a2 = new Bateau(4);
        flota2.put("A", a2);
        Bateau b2 = new Bateau(2);
        flota2.put("B", b2);
        Bateau c2 = new Bateau(5);
        flota2.put("C", c2);
        Bateau d2 = new Bateau(3);
        flota2.put("D", d2);
        Bateau e2 = new Bateau(3);
        flota2.put("E", e2);

        Plateau p1 = new Plateau(height, width, flota1, username, false);
        tablero.put("A", p1);
        Plateau p2 = new Plateau(height, width, flota2, nomJoueur, isComputer);
        tablero.put("B", p2);

        

        while (true) {
//        1 porte-avions (5 cases)
//        1 croiseur (4 cases)
//        1 contre-torpilleurs (3 cases)
//        1 sous-marin (3 cases)
//        1 torpilleur (2 cases)
            if (isComputer) {
                p2.placerAll();
            }
            placerAll(tablero);

            //exe("clear");
            clear();

            System.out.println(pr.getAffiche(p1.getState(), p2.getState()) + "");
            //String ddd = sc.next();

            /**
             * Affichage des profs. Not working /w my code.
             * pg.afficher(p1.plateauGr());
             */
            //JEU
            //si tirs==, tira 1(atack 2); sinon tira qui tirs>>
            while (true) {

                /**
                 *
                 */
                for (String entry : tablero.keySet()) {
//                    dead |= tablero.get(entry).isDeadAllBateaux();
                    Plateau pla = tablero.get(entry);
                    HashMap<String, Plateau> clone = new HashMap((Map) tablero.clone());
//                    clone = tablero.clone();
                    System.out.println("Tir de " + pla.getPropietari() + " : ");
                    clone.remove(entry);

                    String target;
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
                                target = adver.toUpperCase();
                                break;
                            }
                            System.out.print("\nCe n'est pas une option.\nADVERSAIRE : ");
                        }
                    } else {
                        for (String placer : clone.keySet()) {
                            target = placer;
                        }
                    }

                    sc.next();
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

    public static void placerAll(HashMap<String, Plateau> tablero) throws IOException {
//        Color c = new Color();
        for (String entry : tablero.keySet()) {

            String key = entry;
//            Plateau value = tablero.get(key);
            exe("clear");

            Plateau plat = tablero.get(key);

            boolean errorBateau = false;
            boolean pasDePlace = false;
            while (!plat.isSetAllBateaux()) {

                exe("clear");

                String errorBat = c.red("Bateau incorrect/inexistant. \n");
                String errorPlace = c.red("Il n'y a pas de place. \n");
                String information = plat.getPropietari() + ", selectionnez bateau [" + plat.getBateauxToSet() + " R] à placer : ";

                System.out.print(pr.monPlacement(plat.getState()) + "\n"
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

    public static void clear() {
        Color c = new Color();
        System.out.print(c.clear());
    }

    public static boolean isNumber(String string) {
        try {
            Long.parseLong(string);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
