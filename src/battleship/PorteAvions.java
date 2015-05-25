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
public class PorteAvions extends Bateau{
    
    private char[] porteavions; 
    
    public PorteAvions(){
        porteavions= new char[5];
        
        for(int i =0; i<= porteavions.length-1; i++){
            porteavions[i]= 'X';
        }
        
    }
    
    public String toString() { 
		String message = "[";                      
		for(int i =0; i<= porteavions.length-1; i++){     
			if (i == porteavions.length-1) { 			   
				message = message + porteavions[i] + "]";  
			} else {
				message = message + porteavions[i];	
			}
			
		}
		return message;
    }
}
