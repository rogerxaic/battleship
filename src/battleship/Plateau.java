/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package battleship;

import java.util.HashMap;

/**
 *
 * @author Roger
 */
public class Plateau implements PlateauInterface {

    protected int[][] plateau;
    protected HashMap<String, Bateau> flota;
    protected String propietari;
    protected boolean[][] waterBateau; // Is there ship? True / false

    public Plateau(int width, int height, HashMap<String, Bateau> flota, String propietari) {
        this.plateau = new int[width][height];
        this.waterBateau = new boolean[width][height];
        this.flota = flota;
        this.propietari = propietari;

        for (int i = 0; i < plateau.length; i++) {
            for (int j = 0; j < plateau[i].length; j++) {
                this.plateau[i][j] = 1;
                this.waterBateau[i][j] = false;
            }
        }
    }

    @Override
    public int[][] getState() {
        return plateau;
    }

    @Override
    public boolean isSetAllBateaux() {
        boolean isSet = true;
        for (String entry : flota.keySet()) {
            String key = entry;
            Bateau value = flota.get(key);
            isSet &= value.isPositioned();
        }
        return isSet;
    }

    @Override
    public String getBateauxToSet() {
        String toPlace = "";
        for (String entry : flota.keySet()) {
            String key = entry;
            Bateau value = flota.get(key);
            if (!value.isPositioned()) {
                toPlace += key;
            }
        }
        return toPlace;

    }

    @Override
    public boolean isRoom(int x, int y, boolean horizontal, int taille) {

        int b = (horizontal) ? x : y;
        int lc = (horizontal) ? y : x;

        boolean isShip = false;

        if ((horizontal && (x + taille) <= waterBateau[y].length) || (!horizontal && (y + taille) <= waterBateau.length)) {

            for (int i = b; i < (b + taille); i++) {
//                if(horizontal){
//                    System.out.println( i + " " +waterBateau[y][i]);
////                    isShip |= waterBateau[y][i];
//                } else {
//                    isShip |= waterBateau[i][x];
//                }
                isShip |= (horizontal) ? waterBateau[y][i] : waterBateau[i][x];
            }
        }
        return !isShip;
    }

    @Override
    public boolean isRoom(int x, int y, boolean horizontal, Bateau bat) {
        return isRoom(x, y, horizontal, bat.getTaille());
    }

    @Override
    public void setBateau(Bateau bat, int x, int y, boolean horizontal) {
        if (isRoom(x, y, horizontal, bat)) {

            bat.setStartPosition(x, y, horizontal);
            int b = (horizontal) ? x : y;
            for (int i = b; (i < b + bat.getTaille()); i++) {
                if (horizontal) {
                    waterBateau[y][i] = true;
                } else {
                    waterBateau[i][x] = true;
                }

            }
        }
    }

}
