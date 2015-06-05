package battleship;

import java.util.HashMap;

/**
 *
 * @author Roger && Marta
 */
public class Plateau extends Outils implements PlateauInterface {

    /**
     *
     */
    protected int[][] plateau; //1=WATER;2=FAIL;3=GOOD;4=SUNK;5=SHIP

    /**
     *
     */
    protected HashMap<String, Bateau> flota;

    /**
     *
     */
    protected String propietari;

    /**
     *
     */
    protected boolean[][] waterBateau; // Is there ship? True / false

    /**
     *
     */
    protected int tirsFaits;

    /**
     *
     */
    protected boolean isComputer;

    /**
     *
     */
    protected boolean isLastCoule; //true if last shot has coulé 1 ship

    /**
     *
     */
    protected int nbDeaths;

    /**
     *
     * @param width
     * @param height
     * @param flota
     * @param propietari
     * @param isComputer
     */
    public Plateau(int width, int height, HashMap<String, Bateau> flota, String propietari, boolean isComputer) {
        this.plateau = new int[width][height];
        this.waterBateau = new boolean[width][height];
        this.flota = flota;
        this.propietari = propietari;
        this.tirsFaits = 0;
        this.isComputer = isComputer;
        this.isLastCoule = false;

        for (int i = 0; i < plateau.length; i++) {
            for (int j = 0; j < plateau[i].length; j++) {
                this.plateau[i][j] = 1;
                this.waterBateau[i][j] = false;
            }
        }
    }

    /**
     *
     */
    public void addNbDeaths() {
        this.nbDeaths++;
    }

    /**
     *
     * @return
     */
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

    /**
     *
     * @return
     */
    @Override
    public String getListeBateauxToSet() {
        String toPlace = "";
        for (String entry : flota.keySet()) {
            String key = entry;
            Bateau value = flota.get(key);
            if (!value.isPositioned()) {
                toPlace += "\t" + key + ". " + value.getNom() + " [TAILLE " + value.getTaille() + "]\n";
            }
        }
        toPlace += "\n\tR. Random\n";
        return toPlace;
    }
    
    /**
     *
     * @return
     */
    public String getPropietari() {
        return propietari;
    }

    /**
     *
     * @return
     */
    @Override
    public int[][] getStatus() {
        return plateau;
    }
    
    /**
     *
     * @return
     */
    public boolean[][] getWaterBateau() {
        return waterBateau;
    }
    
    /**
     *
     * @return
     */
    @Override
    public boolean isDeadAllBateaux() {
        boolean isDead = true;
        for (String entry : flota.keySet()) {
            isDead &= flota.get(entry).isCoule();
        }
        return isDead;
    }
    
    /**
     *
     * @return
     */
    public boolean isIsLastCoule() {
        return isLastCoule;
    }

    /**
     *
     * @param x
     * @param y
     * @param horizontal
     * @param bat
     * @return
     */
    @Override
    public boolean isRoom(int x, int y, boolean horizontal, Bateau bat) {
        return isRoom(x, y, horizontal, bat.getTaille());
    }

    /**
     *
     * @param x
     * @param y
     * @param horizontal
     * @param taille
     * @return
     */
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

    /**
     *
     * @return
     */
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
    
    /**
     *
     * @param x
     * @param y
     * @return
     */
    @Override
    public boolean isTirValide(int x, int y) {
        boolean isInPlateau = (x >= 0 && x < this.plateau[0].length) && (y >= 0 && y < this.plateau.length);
        return isInPlateau && (this.plateau[y][x] == 1 || this.plateau[y][x] == 5);
    }

    /**
     *
     * @param bat
     * @param x
     * @param y
     * @param horizontal
     */
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
            if (horizontal) {
                boolean existsColAvant = false;
                if (x - 1 >= 0) {
                    waterBateau[y][b - 1] = true;
                    existsColAvant = true;
                }
                boolean existsColApres = false;
                if (x + bat.getTaille() < this.plateau[y].length) {
                    waterBateau[y][b + bat.getTaille()] = true;
                    existsColApres = true;
                }
                if (y - 1 >= 0) {
                    for (int i = (existsColAvant) ? (x - 1) : x;
                            i < ((existsColApres) ? (b + bat.getTaille()) : b + bat.getTaille() - 1);
                            i++) {
                        waterBateau[y - 1][i] = true;
                    }
                }
                if (y + 1 < this.plateau.length) {
                    for (int i = (existsColAvant) ? (x - 1) : x;
                            i < ((existsColApres) ? (b + bat.getTaille()) : b + bat.getTaille() - 1);
                            i++) {
                        waterBateau[y + 1][i] = true;
                    }
                }
            } else {
                boolean existsFilAvant = false;
                if (y - 1 >= 0) {
                    waterBateau[y - 1][x] = true;
                    existsFilAvant = true;
                }
                boolean existsFilApres = false;
                if (y + bat.getTaille() < this.plateau.length) {
                    waterBateau[y + bat.getTaille()][x] = true;
                    existsFilApres = true;
                }
                if (x - 1 >= 0) {
                    for (int i = (existsFilAvant) ? (x - 1) : x;
                            i < ((existsFilApres) ? (b + bat.getTaille()) : b + bat.getTaille() - 1);
                            i++) {
                        waterBateau[i][x - 1] = true;
                    }
                }
                if (x + 1 < this.plateau[y].length) {
                    for (int i = (existsFilAvant) ? (x + 1) : x;
                            i < ((existsFilApres) ? (b + bat.getTaille()) : b + bat.getTaille() - 1);
                            i++) {
                        waterBateau[i][x + 1] = true;
                    }
                }
            }

        }
    }

    /**
     * Méthode pour placer au hasard tous les bateaux.
     */
    @Override
    public void placerAll() {
        for (String entry : flota.keySet()) {
            placerRandom(flota.get(entry));
        }

    }

    /**
     * Méthode pour placer au hasard un bateau.
     * @param bat Bateau qu'on veut placer.
     */
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

    /**
     * Méthode pour rétablir le plateau. Il rétablit aussi les bateaux que le
     * plateau contient.
     */
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

    /**
     * Méthode pour l'affichage des profs. On s'en sert pas.
     * @return Un tableau int[][] pour l'affichage des profs (Moodle2).
     */
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
     * Cette methode tire et nous renvoie true si le tir a touché un bateau (ou
     * coulé) et false si le tir est dans l'eau.
     *
     * @param x Coordonnée X du plateau
     * @param y Coordonnée Y du plateau
     * @return True if shot was available and done, false if (e.g) we had
     * already shoot there.
     */
    @Override
    public boolean tir(int x, int y) {

        boolean resultat = false;
        isLastCoule = false;

        if (this.plateau[y][x] == 5) {

            this.plateau[y][x] = 3;

            for (String entry : flota.keySet()) {
                Bateau bat = flota.get(entry);
                boolean touche = false;
                boolean coule = false;

                touche = bat.tir(x, y);
                coule = bat.isCoule();

                if (touche && coule) {
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
                    isLastCoule = true;
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

    

    

}
