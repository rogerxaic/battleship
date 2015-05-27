/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package battleship;

/**
 *
 * @author Roger
 */
public interface PlateauInterface {

    public int[][] getState();

    public boolean isSetAllBateaux();

    public String getBateauxToSet();

    public boolean isRoom(int x, int y, boolean horizontal, int taille);

    public boolean isRoom(int x, int y, boolean horizontal, Bateau bat);

    public boolean setBateau(Bateau bat, int x, int y, boolean horizontal);
    
    public void placerRandom(Bateau bat);
    
    public void placerAll();

}
