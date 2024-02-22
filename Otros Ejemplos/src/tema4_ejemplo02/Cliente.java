package tema4_ejemplo02;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

public class Cliente {
    static final String HOST = "localhost";
    static final int PUERTO = 2000;
    
    public Cliente(){
        try {
            Socket sCliente = new Socket(HOST, PUERTO);
            
            //Creo los flujos
            DataInputStream flujoEntrada = new DataInputStream(sCliente.getInputStream());
            DataOutputStream flujoSalida = new DataOutputStream(sCliente.getOutputStream());
            
            //Tareas cliente
            String datos = flujoEntrada.readUTF();
            System.out.println(datos);
            
            sCliente.close();
            
        } catch (Exception e) {System.out.println(e.getMessage());
        }
    }
    
    public static void main(String[] args) {
        new Cliente();
    }
}
