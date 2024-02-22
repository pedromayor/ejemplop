package ejercicio1;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Pedro
 */
public class Buffer {

    private char[] buffer;      //recurso que se va a compartir
    private int conteido;       //variable que almacena los valores que se crean
    private boolean lleno;
    private boolean vacio;

    public Buffer(int size) {
        this.buffer = new char[size];
        this.conteido = 0;
        this.lleno = false;
        this.vacio = true;
    }

    public synchronized void producir(char c) {
        //mientras esté lleno, espera.
        while (this.lleno) {    
            try {

                wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(Buffer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        buffer[conteido] = c;
        this.conteido++;
        this.vacio = false;
        if (this.conteido == this.buffer.length) {
            this.lleno = true;
        }
        notifyAll();
    }

    public synchronized char consumir() {
        //mientras esté vacío, espera.
        while (this.vacio) {
            try {
                wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(Buffer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        this.conteido--;
        if (this.conteido == 0) {
            this.vacio = true;
        }
        notifyAll();
        return this.buffer[this.conteido];
    }

}
