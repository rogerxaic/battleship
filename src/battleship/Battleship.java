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
        //clear();
        
        Plateau p1 = new Plateau(10,10);
        Plateau p2 = new Plateau(25,10);
        
        Printer pr = new Printer(p1,p2);
        
        System.out.println("Bienvenu " + username);
        String base = "                     BATAILLE NAVALE\n" +
"      Mon plateau			Son plateau\n" +
"  0 1 2 3 4 5 6 7 8 9               0 1 2 3 4 5 6 7 8 9\n" +
"A · · · · · · · · · · A           A · · · · · · · · · · A\n" +
"B · · · · · · · · · · B           B · · · · · · · · · · B\n" +
"C · · · · · · · · · · C           C · · · · · · · · · · C\n" +
"D · · · · · · · · · · D           D · · · · · · · · · · D\n" +
"E · · · · · · · · · · E           E · · · · · · · · · · E\n" +
"F · · · · · · · · · · F           F · · · · · · · · · · F\n" +
"G · · · · · · · · · · G           G · · · · · · · · · · G\n" +
"H · · · · · · · · · · H           H · · · · · · · · · · H\n" +
"I · · · · · · · · · · I           I · · · · · · · · · · I\n" +
"J · · · · · · · · · · J           J · · · · · · · · · · J\n" +
"  0 1 2 3 4 5 6 7 8 9               0 1 2 3 4 5 6 7 8 9";
        System.out.println(pr.getAffiche());
    }

    public static void clear() throws IOException {
        String OS = System.getProperty("os.name").toLowerCase();
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
