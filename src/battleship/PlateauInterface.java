/**
 *
 * @author Roger
 */
public interface PlateauInterface {

    /**
     *
     * @return
     */
    public int[][] getStatus();

    /**
     *
     * @return
     */
    public boolean isSetAllBateaux();

    /**
     *
     * @return
     */
    public String getBateauxToSet();
    
    /**
     *
     * @return
     */
    public String getListeBateauxToSet();

    /**
     *
     * @param x
     * @param y
     * @param horizontal
     * @param taille
     * @return
     */
    public boolean isRoom(int x, int y, boolean horizontal, int taille);

    /**
     *
     * @param x
     * @param y
     * @param horizontal
     * @param bat
     * @return
     */
    public boolean isRoom(int x, int y, boolean horizontal, Bateau bat);

    /**
     *
     * @param bat
     * @param x
     * @param y
     * @param horizontal
     */
    public void setBateau(Bateau bat, int x, int y, boolean horizontal);
    
    /**
     *
     * @param bat
     */
    public void placerRandom(Bateau bat);
    
    /**
     *
     */
    public void placerAll();
    
    /**
     *
     * @return
     */
    public boolean isDeadAllBateaux();
    
    /**
     *
     */
    public void reset();
    
    /**
     *
     * @param x
     * @param y
     * @return
     */
    public boolean tir(int x, int y);
    
    /**
     *
     * @param x
     * @param y
     * @return
     */
    public boolean isTirValide(int x, int y);

}
