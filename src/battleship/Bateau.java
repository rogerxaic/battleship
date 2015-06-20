/**
 *
 * @author Roger && Marta
 */
public class Bateau extends Outils implements BateauInterface {

    /**
     * Taille du bateau
     */
    protected int taille;

    /**
     * True si le bateau est en position horizontale, false s'il est en position verticale
     */
    protected boolean horizontal;

    /**
     * Abscisse où la première case du bateau est placée
     */
    protected int x = -1;

    /**
     * Ordonnée où la première case du bateau est placée
     */
    protected int y = -1;

    /**
     * On s'en sert pour savoir si le bateau est placé ou non
     */
    protected boolean positioned = false;

    /**
     * Tableau avec l'état du bateau, true pour la partie qui flote, false pour
     * la partie qui est touchée
     */
    protected boolean[] status; 

    /**
     * État [coulé] du bateau, true s'il est coulé, false sinon
     */
    protected boolean coule;

    /**
     * Nom du bateau. On l'obtient automatiquement avec sa taille.
     */
    protected String nom;

    
    
    /**
     * Constructeur d'un bateau d'après sa taille.
     * @param taille Taille du bateau
     */
    public Bateau(int taille) {
        int[] tailles = new int[11];
        tailles[0] = 2;
        tailles[1] = 4;
        tailles[2] = 3;
        tailles[3] = 5;
        tailles[4] = 3;
        tailles[5] = 4;
        tailles[6] = 5;
        tailles[7] = 2;
        tailles[8] = 2;
        tailles[9] = 3;
        tailles[10] = 4;

        int tailleAvant = tailles[taille];
        this.taille = tailleAvant;
        this.status = new boolean[tailleAvant];
        for (int i = 0; i < status.length; i++) {
            status[i] = true;
        }

        this.nom = getNomBateaux(tailleAvant);
    }

    /**
     *
     * @return True si le bateau est déjà mis sur le plateau, false sinon.
     */
    @Override
    public boolean isPositioned() {
        return (positioned || (x > -1 && y > -1));
    }

    /**
     * Méthode pour changer la abscisse où le bateau commence.
     * @param x
     */
    @Override
    public void setPositionX(int x) {
        this.x = x;
    }

    /**
     * Méthode pour changer l'ordonnée où le bateau commence.
     * @param y
     */
    @Override
    public void setPositionY(int y) {
        this.y = y;
    }

    /**
     * 
     * @return La taille du bateau.
     */
    @Override
    public int getTaille() {
        return taille;
    }

    /**
     * Méthode qui établit la direction d'un bateau.
     * @param horizontal
     */
    @Override
    public void setHorizontal(boolean horizontal) {
        this.horizontal = horizontal;
    }

    /**
     * Méthode qu'on utilise lorsqu'on met un bateau dans un plateau.
     * @param x La abscisse où la première case du bateau est placée.
     * @param y La ordonnée où la première case du bateau est placée. 
     * @param horizontal True si le bateau est en horizontal, false sinon.
     */
    @Override
    public void setStartPosition(int x, int y, boolean horizontal) {
        this.x = x;
        this.y = y;
        this.horizontal = horizontal;
        this.positioned = true;
    }

    /**
     *
     * @return True si le bateau est positionné en horizontal, false sinon.
     */
    @Override
    public boolean isHorizontal() {
        return horizontal;
    }

    /**
     *
     * @return La abscisse où le bateau commence.
     */
    @Override
    public int getX() {
        return x;
    }

    /**
     *
     * @return La ordonnée où le bateau commence.
     */
    @Override
    public int getY() {
        return y;
    }

    /**
     * Méthode qui renvoie si des coordonnées x et y appartiennent au bateau.
     * @param x Abscisse qu'on veut vérifier.
     * @param y Ordonnée qu'on veut vérifier.
     * @return True si elles y appartiennent, false sinon.
     */
    @Override
    public boolean isBateau(int x, int y) {

        boolean appartient = (horizontal)
                ? (this.y == y) && (x >= this.x && x < (this.x + this.taille))
                : (this.x == x) && (y >= this.y && y < (this.y + this.taille));
        return appartient;
    }

    /**
     * Méthode qui sert a notifier un bateau qu'on vient de tirer aux coordonnées
     * x et y.
     * @param x Abscisse où on vient de tirer.
     * @param y Ordonnée où on vient de tirer.
     * @return True si on a touché le bateau, false sinon.
     */
    @Override
    public boolean tir(int x, int y) {

        boolean tir = false;
//        if (horizontal) {
//            for (int i = this.x; i < this.x + this.taille; i++) {
//                if (x == i && y == this.y) {
//                    tir = true;
//                    status[x-this.x]= false;  
//                    break;
//                } else {
//                    tir = false;
//                }
//                
//            }
//        } else {
//            for (int i = this.y; i < this.y + this.taille; i++) {
//                if (y == i && x == this.x) {
//                    tir = true;
//                    status[y-this.y]= false;
//                    break;
//                } else {
//                    tir = false;
//                }
//            }
//        }
        if (isBateau(x, y)) {
            tir = true;
            if (horizontal) {
                status[x - this.x] = false;
            } else {
                status[y - this.y] = false;
            }
        }

        return tir;
    }

    /**
     * Méthode qu'on utilise pour connaître l'état d'un bateau.
     * @return True si le bateau est coulé, false sinon.
     */
    @Override
    public boolean isCoule() {
        boolean resultat = false;
        for (int i = 0; i < status.length; i++) {
            resultat |= status[i];
        }
        return !resultat;

    }

    /**
     * Méthode utilisée lorsqu'on joue une deuxième (ou n-ième) fois. Elle 
     * mets les valeurs du bateau a celles qu'on avait avant de démarrer le jeu
     * pour qu'on puisse jouer encore une fois.
     */
    @Override
    public void resetBateau() {

        for (int i = 0; i < status.length; i++) {
            status[i] = true;
        }
        this.coule = false;
        this.x = -1;
        this.y = -1;
        this.positioned = false;
    }

    /**
     * Méthode qu'on utilise pour obtenir automatiquement le nom d'un bateau
     * d'après sa taille.
     * @param taille Taille du bateau.
     * @return Nom du bateau d'après la taille.
     */
    @Override
    public String getNomBateaux(int taille) {

//        1 contre-torpilleurs (3 cases)
//        1 sous-marin (3 cases)
        String resultat = "";
        switch (taille) {
            case 5:
                resultat = "Porte-avions";
                break;
            case 4:
                resultat = "Croiseur";
                break;
            case 3:

                if (RAND.nextBoolean()) {
                    resultat = "Contre-torpilleurs";
                } else {
                    resultat = "Sous-marin";
                }
                break;
            case 2:
                resultat = "Torpilleur";
                break;
            default:
                resultat = "Bateau sans nom ^^'";
                break;
        }
        return resultat;
    }

    /**
     * Méthode pour connaître le nom d'un bateau crée.
     * @return Nom du bateau
     */
    @Override
    public String getNom() {
        return nom;
    }

}
