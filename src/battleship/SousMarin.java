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
public class SousMarin extends Bateau {
    
    private char[] sousmarin;
    
    public SousMarin(){
        sousmarin = new char[3];
        for(int i =0; i<= sousmarin.length-1; i++){
            sousmarin[i]= 'X';
        }
    }
    public String toString() { 
		String message = "[";                      
		for(int i =0; i<= sousmarin.length-1; i++){     
			if (i == sousmarin.length-1) { 			   
				message = message + sousmarin[i] + "]";  
			} else {
				message = message + sousmarin[i];	
			}
			
		}
		return message;
    }

}
