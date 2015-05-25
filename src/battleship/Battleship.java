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
        int width ,height;
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
        
        
        //PEDIR TAILLE PLATEAUX
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

        Bateau a1 = new Croiseur();
        Bateau b1 = new Torpilleur();
        Bateau c1 = new PorteAvions();
        Bateau d1 = new ContreTorpilleurs();
        Bateau e1 = new SousMarin();
        
        Bateau a2 = new Croiseur();
        Bateau b2 = new Torpilleur();
        Bateau c2 = new PorteAvions();
        Bateau d2 = new ContreTorpilleurs();
        Bateau e2 = new SousMarin();
        
        Plateau p1 = new Plateau(height, width);
        Plateau p2 = new Plateau(height, width);
        

        Printer pr = new Printer();

//        1 porte-avions (5 cases)
//        1 croiseur (4 cases)
//        1 contre-torpilleurs (3 cases)
//        1 sous-marin (3 cases)
//        1 torpilleur (2 cases)
        System.out.println(pr.getAffiche(p1.getState(), p2.getState()));
    }

    public static void clear() throws IOException {
        String OS = System.getProperty("os.name").toLowerCase();
        if (OS.indexOf("win") >= 0) {
        } else {
            String neteja = (OS.indexOf("win") >= 0) ? "clear" : "clear";

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
