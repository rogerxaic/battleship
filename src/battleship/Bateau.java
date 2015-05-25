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
public class Bateau implements BateauInterface {

    protected int taille;
    protected boolean horizontal;
    protected int x;
    protected int y;
    protected boolean positioned = false;

    public Bateau(int taille, boolean horizontal) {
        this.taille = taille;
        this.horizontal = horizontal;
    }

    public Bateau(int taille, boolean horizontal, int x, int y) {
        this.taille = taille;
        this.horizontal = horizontal;
        this.x = x;
        this.y = y;
        this.positioned = true;
    }
    

    @Override
    public boolean isPositioned() {
        return positioned;
    }

    @Override
    public void setPositionX(int x) {
        this.x = x;
    }

    @Override
    public void setPositionY(int y) {
        this.y = y;
    }

    @Override
    public String toString() {
        return "Bateau{" + "taille=" + taille + ", horizontal=" + horizontal + ", x=" + x + ", y=" + y + ", positioned=" + positioned + '}';
    }

}
