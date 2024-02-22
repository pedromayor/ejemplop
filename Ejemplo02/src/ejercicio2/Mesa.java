package ejercicio2;

import java.util.concurrent.Semaphore;

/**
 *
 * @author Pedro
 */
public class Mesa {

    private Semaphore[] palillos;

    public Mesa(int numeroPalillos) {
        palillos = new Semaphore[numeroPalillos];
        for (int i = 0; i < numeroPalillos; i++) {
            palillos[i] = new Semaphore(1);
        }
    }

    public void cogerPalillos(int id) throws InterruptedException {
        //Palillo izquierdo (mismo id que filiosofo)
        palillos[id].acquire();
        //Palillo derecho (id - 1, si es id = 0 cogerá el 4)
        if (id == 0) {
            palillos[(4)].acquire();
        } else {
            palillos[(id - 1)].acquire();
        }
    }

    public void dejarPalillos(int id) {
        //Palillo izquierdo (mismo id que filiosofo)
        palillos[id].release();
        //Palillo derecho (id filosofo - 1, si el id = 0 cogerá el 4)
        if (id == 0) {
            palillos[(4)].release();
        } else {
            palillos[(id - 1)].release();
        }
    }
}