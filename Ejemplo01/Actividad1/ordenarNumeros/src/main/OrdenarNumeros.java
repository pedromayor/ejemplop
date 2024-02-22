/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Pedro
 */
public class OrdenarNumeros {

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        
        try {
            //Creo el isr y el bf
            InputStreamReader isr = new InputStreamReader(System.in);
            BufferedReader bf = new BufferedReader(isr);
            
            System.out.println("Dime numeros");
            String lineaTeclado = null;
            while ((lineaTeclado = bf.readLine()) != null){
                //Cojo el string de números, lo troceo y lo guardo a trozos en un array
                String datos[] = lineaTeclado.split(" ");
                int numeros[] = new int[datos.length];
                //Lo convierto a int para poder ordenarlo
                for (int i = 0; i < numeros.length; i++) {
                    numeros[i] = Integer.parseInt(datos[i]);
                }
                //Lo ordeno
                Arrays.sort(numeros);
                //Lo imprimo
                for (int i = 0; i < numeros.length; i++) {
                    System.out.print(numeros[i] + " ");
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(OrdenarNumeros.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}