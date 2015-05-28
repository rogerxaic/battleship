/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package battleship;

import java.io.IOException;

/**
 *
 * @author Roger
 */
public class Panneau extends Battleship {

    protected String[] banner = new String[23];

    public Panneau() {
        banner[0] = "888888b.            888             d8b 888 888          ";
        banner[1] = "888  \"88b           888             Y8P 888 888          ";
        banner[2] = "888  .88P           888                 888 888          ";
        banner[3] = "8888888K.   8888b.  888888  8888b.  888 888 888  .d88b.  ";
        banner[4] = "888  \"Y88b     \"88b 888        \"88b 888 888 888 d8P  Y8b ";
        banner[5] = "888    888 .d888888 888    .d888888 888 888 888 88888888 ";
        banner[6] = "888   d88P 888  888 Y88b.  888  888 888 888 888 Y8b.     ";
        banner[7] = "8888888P\"  \"Y888888  \"Y888 \"Y888888 888 888 888  \"Y8888  ";
        banner[8] = "                                                         ";
        banner[9] = "                                                         ";
        banner[10] = "                                                         ";
        banner[11] = "                                    888                  ";
        banner[12] = "                                    888                  ";
        banner[13] = "                                    888                  ";
        banner[14] = "88888b.   8888b.  888  888  8888b.  888  .d88b.          ";
        banner[15] = "888 \"88b     \"88b 888  888     \"88b 888 d8P  Y8b         ";
        banner[16] = "888  888 .d888888 Y88  88P .d888888 888 88888888         ";
        banner[17] = "888  888 888  888  Y8bd8P  888  888 888 Y8b.             ";
        banner[18] = "888  888 \"Y888888   Y88P   \"Y888888 888  \"Y8888          ";
        banner[19] = "                                                         ";
        banner[20] = "                                                         ";
        banner[21] = "                                                         ";
        banner[22] = "              [Press ENTER to continue]                   ";
        
    }

    public String getBanner() {
        String resultat = "";
        for (int i = 0; i < this.banner.length; i++) {
            resultat += banner[i] + "\n";
        }
        return resultat;
    }

    public void Banner() throws IOException{
        System.out.println(getBanner());

        try {
            System.in.read();
        } catch (Exception e) {
        }
        
        exe("clear");
        
    }

}
