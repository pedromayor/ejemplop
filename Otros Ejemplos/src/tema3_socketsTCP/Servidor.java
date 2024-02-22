package tema3_socketsTCP;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

    static final int PUERTO = 2000;

    public Servidor() {
        try {
            ServerSocket socketServidor = new ServerSocket(PUERTO);
            System.out.println("Esucho el puerto " + PUERTO);

            for (int nCliente = 0; nCliente < 3; nCliente++) {
                Socket socketCliente = socketServidor.accept();
                System.out.println("Sirvo al cliente " + nCliente);
                DataOutputStream flujoSalida = new DataOutputStream(socketCliente.getOutputStream());
                flujoSalida.writeUTF("Hola cliente " + nCliente);
                socketCliente.close();
            }
            System.out.println("Ya se ha atendido a 3 clientes");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Servidor();
    }
}
