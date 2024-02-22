package ejercicio1;

/**
 *
 * @author Pedro
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        Buffer buffer = new Buffer(6);
        HiloProductor hp = new HiloProductor(buffer);
        HiloConsumidor hc = new HiloConsumidor(buffer);
        
        hp.start();
        hc.start();
        
        hp.join();
        hc.join();
    }
}