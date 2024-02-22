package actividad01;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;
import java.util.concurrent.Semaphore;

/**
 *
 * @author Pedro
 */
public class Servidor extends Thread {

    Socket sCliente;
    static final int PUERTO = 2000;
    int idCliente;
    static Semaphore capacidad = new Semaphore(10);

    public Servidor(Socket sCliente, int idCliente) {
        this.sCliente = sCliente;
        this.idCliente = idCliente;
    }

    public static void main(String[] args) {
        int idCliente = 1;
        try {
            // Inicio el servidor:
            ServerSocket sServidor = new ServerSocket(PUERTO);
            System.out.println("Servidor rulando... Escuchando puerto: " + PUERTO);

            // Arranco el servidor
            while (true) {
                // Atiendo a un máximo de 10 clinetes simultáneos:
                // Se conecta un cliente
                Socket sCliente = sServidor.accept();
                System.out.println("Cliente " + idCliente + " conectado.");

                // Lo atiendo en su hilo propio:
                new Servidor(sCliente, idCliente).start();
                idCliente++;
                capacidad.acquire();

            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Hubo un error");
        }
    }

    @Override
    public void run() {
        // Inicio el juego
        Random aleatorio = new Random();
        int numeroSecreto = aleatorio.nextInt(100);
        System.out.println("El número del cliente " + idCliente + " es: " + numeroSecreto);
        boolean victoria = false;
        String mensaje;

        try {
            DataOutputStream flujoSalida = new DataOutputStream(sCliente.getOutputStream());
            DataInputStream flujoEntrada = new DataInputStream(sCliente.getInputStream());

            // Mando pregunta
            flujoSalida.writeUTF("Dime un número del 0 al 100");

            do {
                // Recibo el mensaje y lo parseo de String a int
                mensaje = flujoEntrada.readUTF();
                int numeroCliente = Integer.parseInt(mensaje);

                // Compruebo y respondo (según proceda)
                System.out.println("Cliente " + idCliente + " dice: " + numeroCliente);
                if (numeroCliente < numeroSecreto) {
                    flujoSalida.writeUTF("El número secreto es mayor, dime otro.");
                } else if (numeroCliente > numeroSecreto) {
                    flujoSalida.writeUTF("El número secreto es menor, dime otro.");
                } else {
                    flujoSalida.writeUTF("Enhorabuena, has acertado!");
                    victoria = true;
                }

            } while (!victoria);

            // Cierro los sockets:
            sCliente.close();
            capacidad.release();

        } catch (Exception e) {
        }
    }
}
