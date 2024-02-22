package tema3_socketsTCP;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Cliente {

    static final String HOST = "localhost";
    static final int PUERTO = 2000;

    public Cliente() {
        try {
            Socket socketCliente = new Socket(HOST, PUERTO);
            DataInputStream flujoEntrada = new DataInputStream(socketCliente.getInputStream());
            System.out.println(flujoEntrada.readUTF());
            socketCliente.close();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Cliente();
    }
}
