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
public interface BateauInterface {
    public boolean isPositioned();
    public void setPositionX(int x);
    public void setPositionY(int y);
    public int getTaille();
    public void setHorizontal(boolean horizontal);
    public void setStartPosition(int x, int y, boolean horizontal);
    public boolean isBateau(int x, int y);
    public boolean isCoule();
}
