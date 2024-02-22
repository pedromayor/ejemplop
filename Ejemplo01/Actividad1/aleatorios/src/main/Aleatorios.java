/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

/**
 *
 * @author Pedro
 */
public class Aleatorios {

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        
        //Números a generar
        int cantidadGenerados = 50;
        
        //bucle que imprimirá los números
        for (int i = 0; i < cantidadGenerados; i++) {
            System.out.print(generaNumeroAleatorio(0,90) + " ");
        }       
    }

    //Método para generar números aleatorios
    private static int generaNumeroAleatorio(int minimo, int maximo) {
        int numero = (int)(Math.random()*(maximo-minimo+1)+(minimo));
        return numero;
    }
}