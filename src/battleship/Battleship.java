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

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        exe("start");
        exe("clear");
        String username = System.getProperty("user.name");
        Scanner sc = new Scanner(System.in);

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
        String selectVs = "Comment voulez vous jouer :\n"
                + "\t1. Vs. Ordinateur\n"
                + "\t2. Vs. Quelqu'un\n\n"
                + "ADVERSAIRE : ";

        System.out.print(selectVs);
        boolean isComputer = false;
        int vsSelected = 0;
        while (true) {
            vsSelected = sc.nextInt();
            if (vsSelected > 0 && vsSelected < 3) {
                break;
            }
            System.out.print("\nCe n'est pas une option.\nADVERSAIRE : ");
        }

        switch (vsSelected) {
            case 1:
                isComputer = true;
                break;
            case 2:
                isComputer = false;
                break;
            default:
                isComputer = true;
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

        HashMap<String, Bateau> flota1 = new HashMap<>();
        HashMap<String, Bateau> flota2 = new HashMap<>();

        HashMap<String, Plateau> tablero = new HashMap<>();

        Bateau a1 = new Bateau(4/*, false/*, 8, 1*/); //4 BBBB V B8
        flota1.put("A", a1);
        Bateau b1 = new Bateau(2/*, true/*, 6, 6*/); //2 PP H G6
        flota1.put("B", b1);
        Bateau c1 = new Bateau(5/*, true/*, 2, 8*/); //5 AAAAA H I2
        flota1.put("C", c1);
        Bateau d1 = new Bateau(3/*, true/*, 0, 3*/); //3 DDD H C0
        flota1.put("D", d1);
        Bateau e1 = new Bateau(3/*, false/*, 0, 4*/); //3 SSS V E0
        flota1.put("E", e1);

        Bateau a2 = new Bateau(4/*, false*/);
        flota2.put("A", a2);
        Bateau b2 = new Bateau(2/*, true*/);
        flota2.put("B", b2);
        Bateau c2 = new Bateau(5/*, true*/);
        flota2.put("C", c2);
        Bateau d2 = new Bateau(3/*, true*/);
        flota2.put("D", d2);
        Bateau e2 = new Bateau(3/*, false*/);
        flota2.put("E", e2);

        Plateau p1 = new Plateau(height, width, flota1, username);
        tablero.put("P1", p1);
        Plateau p2 = new Plateau(height, width, flota2, nomJoueur);
        tablero.put("P2", p2);

        Printer pr = new Printer();
        
        while (true) {
//        1 porte-avions (5 cases)
//        1 croiseur (4 cases)
//        1 contre-torpilleurs (3 cases)
//        1 sous-marin (3 cases)
//        1 torpilleur (2 cases)
            if (isComputer) {
                p2.placerAll();
            }
            placerAll(tablero, sc, pr);

            System.out.println(pr.getAffiche(p1.getState(), p2.getState()) + "");
            //String ddd = sc.next();
            
            //JEU
            //si tirs==, tira 1(atack 2); sinon tira qui tirs>>
            
            System.out.println("Rejouer ? [YN] ");
            String rejouer = sc.next();
            boolean rejeu = (rejouer.charAt(0) == 'Y' || rejouer.charAt(0) == 'y');
            if(!rejeu){
                break;
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

    public static void placerAll(HashMap<String, Plateau> tablero, Scanner sc, Printer pr) throws IOException {
        Color c = new Color();
        for (String entry : tablero.keySet()) {

            String key = entry;
//            Plateau value = tablero.get(key);
            exe("clear");

            boolean errorBateau = false;
            while (!tablero.get(key).isSetAllBateaux()) {

                exe("clear");

                String errorBat = c.red("Bateau incorrect/inexistant. \n");

                String information = tablero.get(key).getPropietari() + ", selectionnez bateau [" + tablero.get(key).getBateauxToSet() + " R] à placer : ";

                System.out.print(pr.monPlacement(tablero.get(key).getState()) + ((errorBateau) ? errorBat + information : information));
                errorBateau = false;

                while (true) {
                    String ddd = sc.next();
                    String dd = "" + ddd.toUpperCase().charAt(0);
                    if (tablero.get(key).flota.containsKey(dd) && !tablero.get(key).flota.get(dd).isPositioned()) {
                        String donde = "\nCe bateau a une taille de "
                                + tablero.get(key).flota.get(dd).getTaille() + " \n"
                                + "Où placer le bateau? LETTRE : ";
                        System.out.print(donde);
                        while (true) {
                            String letra = sc.next();
                            int y = 0;
                            for (int i = 0; i < ABC.length(); i++) {
                                if (letra.toUpperCase().charAt(0) == ABC.charAt(i)) {
                                    y = i;
                                }
                            }
                            System.out.print("NUMÉRO :");
                            String numero = sc.next();
                            int x;
                            x = Integer.parseInt("" + numero.charAt(0));

                            System.out.print("VERTICAL ou HORIZONTAL ? [V/H]");
                            String vouh = sc.next();
                            boolean horizontal = false;
                            horizontal = (vouh.charAt(0) == 'H' || vouh.charAt(0) == 'h');

                            if (tablero.get(key).isRoom(x, y, horizontal, tablero.get(key).flota.get(dd))) {
                                tablero.get(key).setBateau(tablero.get(key).flota.get(dd), x, y, horizontal);
                            } else {
                                System.out.println("Il n'y a pas de place. ");
                            }
                            break;

                        }
//                            System.out.println("TRUE");
                    } else if ("R".equals(dd)) {
                        tablero.get(key).placerAll();
                    } else {
                        errorBateau = true;
                    }
                    break;
                }
            }
        }
    }
}
