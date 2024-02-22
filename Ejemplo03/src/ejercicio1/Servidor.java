package ejercicio1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Pedro
 */
public class Servidor {

    static final int PUERTO = 2000;

    public Servidor() {

        //Genero un numero aleatorio de 0 a 100 y la condción de victoria
        Random aleatorio = new Random();
        int numeroSecreto = aleatorio.nextInt(100);
        boolean victoria = false;
        System.out.println("El numero es: " + numeroSecreto);
        
        try {
            //Creo los sockets y flujos de entrada y salida
            ServerSocket socketServidor = new ServerSocket(PUERTO);
            System.out.println("Servidor rulando... Escuchando puerto: " + PUERTO);
            Socket socketCliente = socketServidor.accept();
            DataOutputStream flujoSalida = new DataOutputStream(socketCliente.getOutputStream());
            DataInputStream flujoEntrada = new DataInputStream(socketCliente.getInputStream());
            
            //Mando pregunta
            flujoSalida.writeUTF("Dime un número del 0 al 100");

            do {
                //Parseo entrada String a int
                int numeroCliente = Integer.parseInt(flujoEntrada.readUTF());
                
                //Compruebo y respondo según proceda
                System.out.println("Cliente dice " + numeroCliente);
                if (numeroCliente < numeroSecreto) {
                    flujoSalida.writeUTF("El número secreto es mayor. Dime otro:");
                }else if (numeroCliente > numeroSecreto) {
                    flujoSalida.writeUTF("El número secreto es menor. Dime otro:");
                } else {
                    flujoSalida.writeUTF("Enhorabuena, has acertado!");
                    victoria = true;
                }
            } while (!victoria);

            //Cierro los sockets
            socketCliente.close();
            socketServidor.close();
            
        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void main(String[] args) {
        new Servidor();
    }
}