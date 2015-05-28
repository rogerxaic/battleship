/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package battleship;

import java.util.HashMap;
import java.util.Random;

/**
 *
 * @author Roger
 */
public class Plateau implements PlateauInterface {

    protected int[][] plateau; //1=WATER;2=FAIL;3=GOOD;4=SUNK;5=SHIP
    protected HashMap<String, Bateau> flota;
    protected String propietari;
    protected boolean[][] waterBateau; // Is there ship? True / false
    private static Random rnd = new Random();
    protected int tirsFaits;
    protected boolean isComputer;

    public Plateau(int width, int height, HashMap<String, Bateau> flota, String propietari, boolean isComputer) {
        this.plateau = new int[width][height];
        this.waterBateau = new boolean[width][height];
        this.flota = flota;
        this.propietari = propietari;
        this.tirsFaits = 0;
        this.isComputer = isComputer;

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

        boolean isThereShip = false;

        if ((horizontal && (x + taille) <= waterBateau[y].length) || (!horizontal && (y + taille) <= waterBateau.length)) {
            for (int i = b; i < (b + taille); i++) {
//                if(horizontal){
//                    System.out.println( i + " " +waterBateau[y][i]);
////                    isShip |= waterBateau[y][i];
//                } else {
//                    isShip |= waterBateau[i][x];
//                }
                isThereShip |= (horizontal) ? waterBateau[y][i] : waterBateau[i][x];
            }
        } else {
            isThereShip = true;
        }
        return !isThereShip;
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
            for (int i = b; i < (b + bat.getTaille()); i++) {
                if (horizontal) {
                    waterBateau[y][i] = true;
                    plateau[y][i] = 5;
                } else {
                    waterBateau[i][x] = true;
                    plateau[i][x] = 5;
                }

            }

        }
    }

    public String getPropietari() {
        return propietari;
    }

    @Override
    public void placerRandom(Bateau bat) {
        while (!bat.isPositioned()) {
            boolean horizontal = rnd.nextBoolean();
            int y = rnd.nextInt(plateau.length);
            int x = rnd.nextInt(plateau[y].length);

            if (isRoom(x, y, horizontal, bat)) {
                setBateau(bat, x, y, horizontal);
            }

        }
    }

    @Override
    public void placerAll() {
        for (String entry : flota.keySet()) {
            placerRandom(flota.get(entry));
        }

    }

    @Override
    public boolean isDeadAllBateaux() {
        boolean isDead = true;
        for (String entry : flota.keySet()) {
            isDead &= flota.get(entry).isCoule();
        }
        return isDead;
    }

    public boolean[][] getWaterBateau() {
        return waterBateau;
    }

    @Override
    public void reset() {
        for (String entry : flota.keySet()) {
            flota.get(entry).resetBateau();
        }
        for (int i = 0; i < plateau.length; i++) {
            for (int j = 0; j < plateau[i].length; j++) {
                this.plateau[i][j] = 1;
                this.waterBateau[i][j] = false;
            }
        }

    }

    public int[][] plateauGr() {
        int[][] resultat = new int[plateau.length][plateau[0].length];

        for (int i = 0; i < plateau.length; i++) {
            for (int j = 0; i < plateau[i].length; j++) {
                switch (plateau[i][j]) {
                    case 1:
                        resultat[i][j] = 0;
                    case 2:
                        resultat[i][j] = 1;
                    case 3:
                        resultat[i][j] = 2;
                    default:
                        resultat[i][j] = 2;
                }
            }
        }

        return resultat;
    }

}
