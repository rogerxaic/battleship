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
public class Plateau extends Outils implements PlateauInterface {

    protected int[][] plateau; //1=WATER;2=FAIL;3=GOOD;4=SUNK;5=SHIP
    protected HashMap<String, Bateau> flota;
    protected String propietari;
    protected boolean[][] waterBateau; // Is there ship? True / false
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
    public int[][] getStatus() {
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
//            if (horizontal) {
//                boolean existsColAvant = false;
//                if (x - 1 > 0) {
//                    waterBateau[y][b - 1] = true;
//                    existsColAvant = true;
//                }
//                boolean existsColApres = false;
//                if (x + bat.getTaille() < this.plateau[y].length) {
//                    waterBateau[y][b + bat.getTaille()] = true;
//                    existsColApres = true;
//                }
//                if (y - 1 > 0) {
//                    for (int i = (existsColAvant) ? (x - 1) : x;
//                            i < ((existsColApres) ? (b + bat.getTaille()) : b + bat.getTaille() - 1);
//                            i++) {
//                        waterBateau[y - 1][i] = true;
//                    }
//                }
//                if (y + 1 < this.plateau.length) {
//                    for (int i = (existsColAvant) ? (x - 1) : x;
//                            i < ((existsColApres) ? (b + bat.getTaille()) : b + bat.getTaille() - 1);
//                            i++) {
//                        waterBateau[y + 1][i] = true;
//                    }
//                }
//            } else {
//                boolean existsFilAvant = false;
//                if (y - 1 > 0) {
//                    waterBateau[y - 1][x] = true;
//                    existsFilAvant = true;
//                }
//                boolean existsFilApres = false;
//                if (y + bat.getTaille() < this.plateau.length) {
//                    waterBateau[y + bat.getTaille()][x] = true;
//                    existsFilApres = true;
//                }
//                if (x - 1 > 0) {
//                    for (int i = (existsFilAvant) ? (x - 1) : x;
//                            i < ((existsFilApres) ? (b + bat.getTaille()) : b + bat.getTaille() - 1);
//                            i++) {
//                        waterBateau[i][x - 1] = true;
//                    }
//                }
//                if (x + 1 < this.plateau[y].length) {
//                    for (int i = (existsFilAvant) ? (x + 1) : x;
//                            i < ((existsFilApres) ? (b + bat.getTaille()) : b + bat.getTaille() - 1);
//                            i++) {
//                        waterBateau[i][x + 1] = true;
//                    }
//                }
//            }

        }
    }

    public String getPropietari() {
        return propietari;
    }

    @Override
    public void placerRandom(Bateau bat) {
        while (!bat.isPositioned()) {
            boolean horizontal = RAND.nextBoolean();
            int y = RAND.nextInt(plateau.length);
            int x = RAND.nextInt(plateau[y].length);

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

    /**
     * Cette methode tire et nous renvoie true si le tir a touché un bateau (ou coulé)
     * et false si le tir est dans l'eau.
     * @param x Coordonnée X du plateau
     * @param y Coordonnée Y du plateau 
     * @return True if shot was available and done, false if (e.g) we had
     * already shoot there.
     */
    @Override
    public boolean bonTir(int x, int y) {

        boolean resultat = false;

        if (this.plateau[y][x] == 5) {

            boolean touche = true;
            boolean coule = false;
            
            this.plateau[y][x] = 3;

            for (String entry : flota.keySet()) {
                Bateau bat = flota.get(entry);
                
                touche = bat.tir(x, y);
                coule = bat.isCoule();
                if (coule) {
                    if (bat.isHorizontal()) {
                        for (int i = bat.getX(); i < (bat.getX() + bat.getTaille()); i++) {
                            this.plateau[bat.getY()][i] = 4;
                        }
                    } else {
                        for (int i = bat.getY(); i < (bat.getY() + bat.getTaille()); i++) {
                            this.plateau[i][bat.getX()] = 4;
                        }
                    }
                    bell();
                    break;
                } else if (touche && !coule) {
                    this.plateau[y][x] = 3;
                    bell();
                    break;
                }

            }
            resultat = true;

        } else if (this.plateau[y][x] == 1) {
            this.plateau[y][x] = 2;
            resultat = false;
        }

        return resultat;
    }

    @Override
    public boolean isTirValid(int x, int y) {
        return (this.plateau[y][x] == 1 || this.plateau[y][x] == 5);
    }

}
