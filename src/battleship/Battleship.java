 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package battleship;

import java.io.*;
import java.util.*;

/**
 *
 * @author Roger MIRET & Marta CORTÉS
 */
public class Battleship {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {

        String username = System.getProperty("user.name");
        Scanner sc = new Scanner(System.in);
        clear();

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

        clear();

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

        clear();

        HashMap<String, Bateau> bat1 = new HashMap<>();
        HashMap<String, Bateau> bat2 = new HashMap<>();

        Bateau a1 = new Bateau(4, false); //4 BBBB V B8
        bat1.put("A", a1);
        Bateau b1 = new Bateau(2, true); //2 PP H G6
        bat1.put("B", b1);
        Bateau c1 = new Bateau(5, true); //5 AAAAA H I2
        bat1.put("C", c1);
        Bateau d1 = new Bateau(3, true); //3 DDD H C0
        bat1.put("D", d1);
        Bateau e1 = new Bateau(3, false); //3 SSS V E0
        bat1.put("E", e1);

        Bateau a2 = new Bateau(4, false);
        bat2.put("A", a2);
        Bateau b2 = new Bateau(2, true);
        bat2.put("B", b2);
        Bateau c2 = new Bateau(5, true);
        bat2.put("C", c2);
        Bateau d2 = new Bateau(3, true);
        bat2.put("D", d2);
        Bateau e2 = new Bateau(3, false);
        bat2.put("E", e2);

        Plateau p1 = new Plateau(height, width, bat1);
        Plateau p2 = new Plateau(height, width, bat2);

        Printer pr = new Printer();

//        1 porte-avions (5 cases)
//        1 croiseur (4 cases)
//        1 contre-torpilleurs (3 cases)
//        1 sous-marin (3 cases)
//        1 torpilleur (2 cases)
        String toPlace = "";
        for (String entry : bat1.keySet()) {
            String key = entry;
            Bateau value = bat1.get(key);
            if (!value.isPositioned()) {
                toPlace += key;
            }
            //System.out.println(value.toString());

            // do what you have to do here
            // In your case, an other loop.
        }

        String information = "Selectionnez bateau [" + toPlace + "] à placer";
        System.out.println(pr.getAffiche(p1.getState(), p2.getState()) + information);
        String ddd = sc.next();

        System.out.println("---" + ddd + "---");

    }

    public static void clear() throws IOException {
        String OS = System.getProperty("os.name").toLowerCase();
        if (OS.contains("win")) {
        } else {
            String neteja = (OS.contains("win")) ? "clear" : "clear";

            //        J'ai créé le fichier clear.exe (trouvable dans 
            //                                        ./external/clear/bin/Debug)
            //        J'ai placé ce fichier dans System32 pour que Windows ne 
            //        donne pas d'erreurs. La commande clear est cls, mais NetBeans ne veut
            //        pas la prendre car c'est cmd qui interprète cls, donc n'est pas un 
            //        executable Windows que NetBeans puisse executer.
            Process p = Runtime.getRuntime().exec(neteja);

            try (BufferedReader br = new BufferedReader(
                    new InputStreamReader(p.getInputStream()))) {

                String line;
                while ((line = br.readLine()) != null) {
                    System.out.println(line);
                }
            }
        }
    }

}
