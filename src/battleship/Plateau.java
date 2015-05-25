/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package battleship;

import java.util.logging.Logger;

/**
 *
 * @author Roger
 */
public class Plateau implements PlateauInterface{

    protected int [][] plateau;
    private static final Logger LOG = Logger.getLogger(Plateau.class.getName());
    
    public Plateau(int width, int height) { 
        this.plateau = new int[width][height];
        
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
}
