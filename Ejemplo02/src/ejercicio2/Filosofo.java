package ejercicio2;

import java.util.logging.Logger;

/**
 *
 * @author Pedro
 */
public class Filosofo extends Thread {

    private String nombre;
    private int id;
    private Mesa mesa;

    public Filosofo(String nombre, int id, Mesa m) {
        this.nombre = nombre;
        this.id = id;
        this.mesa = m;
    }

    public void pensar() throws InterruptedException {
        System.out.println(nombre + "(" + id + ")" + " esta pensando");
        Thread.sleep((long) (Math.random() * 3000));
    }

    public void comer() throws InterruptedException {
        System.out.println(nombre + "(" + id + ")" + " esta comiendo");
        Thread.sleep((long) (Math.random() * 3000));
    }

    @Override
    public void run() {
        while (true) {
            try {
                pensar();
                System.out.println(nombre + "(" + id + ")" + " tiene hambre");
                mesa.cogerPalillos(id);
                this.comer();
                System.out.println(nombre + "(" + id + ")" + " termina de comer");
                mesa.dejarPalillos(id);
            } catch (InterruptedException ex) {
                Logger.getLogger(Filosofo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }
        }
    }
}
