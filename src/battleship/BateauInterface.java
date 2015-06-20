/**
 *
 * @author Roger
 */
public interface BateauInterface {

    /**
     *
     * @return
     */
    public boolean isPositioned();

    /**
     *
     * @param x
     */
    public void setPositionX(int x);

    /**
     *
     * @param y
     */
    public void setPositionY(int y);

    /**
     *
     * @return
     */
    public int getTaille();

    /**
     *
     * @param horizontal
     */
    public void setHorizontal(boolean horizontal);

    /**
     *
     * @param x
     * @param y
     * @param horizontal
     */
    public void setStartPosition(int x, int y, boolean horizontal);

    /**
     *
     * @param x
     * @param y
     * @return
     */
    public boolean isBateau(int x, int y);

    /**
     *
     * @return
     */
    public boolean isCoule();

    /**
     *
     * @param x
     * @param y
     * @return
     */
    public boolean tir(int x, int y);

    public boolean isHorizontal();

    public int getX();

    public int getY();

    public void resetBateau();

    public String getNomBateaux(int taille);

    public String getNom();
}
