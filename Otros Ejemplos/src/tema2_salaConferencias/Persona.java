/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tema2_salaConferencias;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Pedro
 */
public class Persona implements Runnable{
    
    private String nombrePersona;
    private Sala sala;
   
    public Persona(String nombrePersona, Sala sala){
        this.nombrePersona = nombrePersona;
        this.sala = sala;
    }
    
    public void solicitarAccesoEntrada() throws InterruptedException{
        System.out.println(""+nombrePersona+" solicita acceso de entrada");
        sala.entrar(nombrePersona);
        System.out.println(""+nombrePersona+" se sienta");
        Thread.sleep(6000); //está sentado 6seg
        System.out.println(""+nombrePersona+" se levanta");

    }
    
    public void solicitarAccesoSalida() throws InterruptedException{
        System.out.println(""+nombrePersona+" solicita acceso de salida");
        sala.salir(nombrePersona);
    }
    
    
    @Override
    public void run() {
        try {
            solicitarAccesoEntrada();
            solicitarAccesoSalida();
        } catch (InterruptedException ex) {
            Logger.getLogger(Persona.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
