/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package battleship;

import java.io.*;
import java.util.*;

/**
 *
 * @author Roger MIRET & Marta CORTÉS
 */
public class Battleship {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {

        String OS = System.getProperty("os.name").toLowerCase();
        String neteja = (OS.indexOf("win") >= 0) ? "ipconfig" : "clear";
        String username = System.getProperty("user.name");

        Process p = Runtime.getRuntime().exec(neteja);

    }

}
