/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package battleship;

import java.util.HashMap;
import java.util.logging.Logger;

/**
 *
 * @author Roger
 */
public class Plateau implements PlateauInterface{

    protected int [][] plateau;
    private static final Logger LOG = Logger.getLogger(Plateau.class.getName());
    protected HashMap<String, Bateau> flota; 
    
    public Plateau(int width, int height, HashMap<String, Bateau> flota) { 
        this.plateau = new int[width][height];
        this.flota = flota;
        
        for(int i=0;i<plateau.length;i++){
            for(int j=0;j<plateau[i].length;j++){
                this.plateau[i][j]=1;
            }
        }
    }

    @Override
    public int[][] getState() {
        return plateau;
    }

    @Override
    public boolean isSetAllBateaux() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
            //System.out.println(value.toString());

            // do what you have to do here
            // In your case, an other loop.
        }
        return toPlace;
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isRoom(int x, int y, boolean horizontal, int taille) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isRoom(int x, int y, boolean horizontal, Bateau bat) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setBateau(Bateau bat, int x, int y, boolean horizontal) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
