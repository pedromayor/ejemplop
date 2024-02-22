package ejercicio1;

/**
 *
 * @author Pedro
 */
public class HiloConsumidor extends Thread {
    private Buffer buffer;
    private int consumido;
    
    public HiloConsumidor(Buffer buffer){
        this.buffer = buffer;
        this.consumido = 0;
    }
    
    @Override
    public void run() {
        while (consumido < 15) {
            char c = buffer.consumir();
            this.consumido++;
            System.out.println("Recogido el caracter " + c + " en el buffer");
        }
    }
}
