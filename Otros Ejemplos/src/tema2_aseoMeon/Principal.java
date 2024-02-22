/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tema2_aseoMeon;

import java.util.ArrayList;

/**
 *
 * @author Pedro
 */
public class Principal {
    
    public static void main(String[] args) throws InterruptedException {
        
        Aseo aeropuerto = new Aseo(5,1);
        ArrayList<Thread> hilos = new ArrayList<Thread>();
        
        Persona p1 = new Persona("Pepe", aeropuerto);
        Thread h1 = new Thread(p1);
        hilos.add(h1);
        Persona p2 = new Persona("Juan", aeropuerto);
        Thread h2 = new Thread(p2);
        hilos.add(h2);
        Persona p3 = new Persona("Antonio", aeropuerto);
        Thread h3 = new Thread(p3);
        hilos.add(h3);
        Persona p4 = new Persona("Coral", aeropuerto);
        Thread h4 = new Thread(p4);
        hilos.add(h4);
        Persona p5 = new Persona("Jesus", aeropuerto);
        Thread h5 = new Thread(p5);
        hilos.add(h5);
        Persona p6 = new Persona("Juanvi", aeropuerto);
        Thread h6 = new Thread(p6);
        hilos.add(h6);
        Persona p7 = new Persona("Roberto", aeropuerto);
        Thread h7 = new Thread(p7);
        hilos.add(h7);
        Persona p8 = new Persona("Jorge", aeropuerto);
        Thread h8 = new Thread(p8);
        hilos.add(h8);
        Persona p9 = new Persona("Beatriz", aeropuerto);
        Thread h9 = new Thread(p9);
        hilos.add(h9);
        Persona p10 = new Persona("Miguel", aeropuerto);
        Thread h10 = new Thread(p10);
        hilos.add(h10);
        
        for (Thread hilo : hilos) {
            hilo.start();
        }
        
        for (Thread hilo : hilos) {
            hilo.join();            //esperara en la puerta a que salgan los demás
            System.out.println("alguien ha terminado");
        }
        
        System.out.println("todos han hecho sus cosas");
       
    }
    
}
