/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package battleship;

/**
 *
 * @author MartaCD
 */
public class Torpilleur extends Bateau {

    private char[] torpilleur;

    public Torpilleur(int taille, boolean horizontal) {
        super(taille, horizontal);
        this.torpilleur = torpilleur;
    }

//    public Torpilleur() {
//        torpilleur = new char[2];
//        for (int i = 0; i <= torpilleur.length - 1; i++) {
//            torpilleur[i] = 'X';
//        }
//    }

    @Override
    public String toString() {
        String message = "[";
        for (int i = 0; i <= torpilleur.length - 1; i++) {
            if (i == torpilleur.length - 1) {
                message = message + torpilleur[i] + "]";
            } else {
                message = message + torpilleur[i];
            }

        }
        return message;
    }

}
