/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tema2_aseoMeon;

import java.util.Random;

/**
 *
 * @author Pedro
 */
public class Persona implements Runnable{
    private String nombre;
    private boolean accion;
    private Aseo aseo;
    
    public Persona(String nombre, Aseo aseo){
        this.nombre = nombre;
        //accion es random si es true mea y si es false caga
        Random r = new Random();
        this.accion = r.nextBoolean();
        this.aseo = aseo;
    }

    @Override
    public void run() {
        try {
            aseo.entrarAseo(this);
            aseo.ocuparWC(this);
            aseo.dejarWC(this);            
            aseo.salirAseo(this);
            
        } catch (Exception e) {
        }
    }

    public String getNombre() {
        return nombre;
    }

    public boolean getAccion() {
        return accion;
    }
    
}
