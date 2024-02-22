/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tema2_salaConferencias;

import static java.lang.Math.random;
import static java.lang.StrictMath.random;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Pedro
 */
public class Principal {
    
    public static void main(String[] args) throws InterruptedException {
        
        Sala s1 = new Sala();
        
        ArrayList<Thread> hilos = new ArrayList<Thread>();
        
        Persona p1 = new Persona("Pepe", s1);
        Thread h1 = new Thread(p1);
        hilos.add(h1);
        Persona p2 = new Persona("Juan", s1);
        Thread h2 = new Thread(p2);
        hilos.add(h2);
        Persona p3 = new Persona("Antonio", s1);
        Thread h3 = new Thread(p3);
        hilos.add(h3);
        Persona p4 = new Persona("Coral", s1);
        Thread h4 = new Thread(p4);
        hilos.add(h4);
        Persona p5 = new Persona("Jesus", s1);
        Thread h5 = new Thread(p5);
        hilos.add(h5);
        Persona p6 = new Persona("Juanvi", s1);
        Thread h6 = new Thread(p6);
        hilos.add(h6);
        Persona p7 = new Persona("Roberto", s1);
        Thread h7 = new Thread(p7);
        hilos.add(h7);
        Persona p8 = new Persona("Jorge", s1);
        Thread h8 = new Thread(p8);
        hilos.add(h8);
        Persona p9 = new Persona("Beatriz", s1);
        Thread h9 = new Thread(p9);
        hilos.add(h9);
        Persona p10 = new Persona("Miguel", s1);
        Thread h10 = new Thread(p10);
        hilos.add(h10);
        
        Random random = new Random();
        Long espera = random.nextLong(1000,4000);
        
        for (Thread hilo : hilos) {
            hilo.start();
            hilo.sleep(espera);
        }
        
        for (Thread hilo : hilos) {
            hilo.join();
        }
        
    }
    
}
