package tema4_ejemplo04;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class Servidor {
    static final int PUERTO = 2000;
    
    public Servidor(){
        try {
            // Inicio el servidor
            ServerSocket sServidor = new ServerSocket(PUERTO);
            System.out.println("Escucho el puerto " + PUERTO);
            
            // Se conecta un cliente
            Socket sCliente = sServidor.accept();
            System.out.println("Cliente conectado");
            
            // Creo los flujos
            DataInputStream flujoEntrada = new DataInputStream(sCliente.getInputStream());
            DataOutputStream flujoSalida = new DataOutputStream(sCliente.getOutputStream());
            
            // Cuerpo del Algoritmo
            long tiempo1 = new Date().getTime();
            flujoSalida.writeUTF(Long.toString(tiempo1));
            
            // Cerrar Conexión
            sCliente.close();
            System.out.println("Cliente desconectado");
            
        } catch (Exception e) {
            e.getMessage();
        }
    }
    
    public static void main(String[] args) {
        new Servidor();
    }
}
