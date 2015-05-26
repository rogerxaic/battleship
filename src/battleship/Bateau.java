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
    protected int x = -1;
    protected int y = -1;
    protected boolean positioned = false;
    protected boolean[] status; //1 flottant, 0 touché

    public Bateau(int taille) {
        this.taille = taille;
        this.status = new boolean[taille];
    }

    public Bateau(int taille, boolean horizontal) {
        this.taille = taille;
        this.horizontal = horizontal;
        this.status = new boolean[taille];
    }

    public Bateau(int taille, boolean horizontal, int x, int y) {
        this.taille = taille;
        this.horizontal = horizontal;
        this.x = x;
        this.y = y;
        this.positioned = true;
        this.status = new boolean[taille];
    }

    @Override
    public boolean isPositioned() {
        return (positioned || (x > -1 && y > -1));
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
        return "Bateau{" + "taille=" + taille + ", horizontal=" + horizontal + ", x=" + x + ", y=" + y + ", positioned=" + positioned + ", status=" + status + '}';
    }

    

    @Override
    public int getTaille() {
        return taille;
    }

    @Override
    public void setHorizontal(boolean horizontal) {
        this.horizontal = horizontal;
    }

    @Override
    public void setStartPosition(int x, int y, boolean horizontal) {
        this.x = x;
        this.y = y;
        this.horizontal = horizontal;
        this.positioned = true;
    }

    public boolean isHorizontal() {
        return horizontal;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public boolean isBateau(int x, int y) {

        boolean appartient = (horizontal)
                ? (this.y == y) && (x >= this.x && x < (this.x + this.taille))
                : (this.x == x) && (y >= this.y && y < (this.y + this.taille));
        return true;
    }
}
