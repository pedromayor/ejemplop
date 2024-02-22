/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tema2_salaConferencias;

import java.util.concurrent.Semaphore;

/**
 *
 * @author Pedro
 */
public class Sala {
    
    private Semaphore puerta = new Semaphore(3);
    private boolean[] asientos = new boolean[10];
    
    public Sala(){
        for (int i = 0; i < asientos.length; i++) {
            this.asientos[i]= false;
        }
    }
    
    
    public void entrar(String nombrePersona) throws InterruptedException{
        puerta.acquire();
        System.out.println("" + nombrePersona + " entra.");
    }
    
    public void salir(String nombrePersona){
        puerta.release();
        System.out.println("" + nombrePersona + " sale.");
    }
    
}