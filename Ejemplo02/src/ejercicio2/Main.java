package ejercicio2;

/**
 *
 * @author Pedro
 */
public class Main {
    public static void main(String[] args) {
        Mesa m = new Mesa(5);
        Filosofo f1 = new Filosofo("Pitagoras", 0, m);
        Filosofo f2 = new Filosofo("Socrates", 1, m);
        Filosofo f3 = new Filosofo("Platon", 2, m);
        Filosofo f4 = new Filosofo("Aristoteles", 3, m);
        Filosofo f5 = new Filosofo("Descartes", 4, m);
        f1.start();
        f2.start();
        f3.start();
        f4.start();
        f5.start();
    }
}
