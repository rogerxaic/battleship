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
public class Croiseur extends Bateau {
    
    private char[] croiseur;

    public Croiseur(int taille, boolean horizontal) {
        super(taille, horizontal);
    }
    
    
    
//    public Croiseur(){
//        croiseur = new char[4];
//    
//        for(int i =0; i<= croiseur.length-1; i++){
//            croiseur[i]= 'X';
//        }
//    }
    public String toString() { 
		String message = "[";                      
		for(int i =0; i<= croiseur.length-1; i++){     
			if (i == croiseur.length-1) { 			   
				message = message + croiseur[i] + "]";  
			} else {
				message = message + croiseur[i];	
			}
			
		}
		return message;
    }

}
