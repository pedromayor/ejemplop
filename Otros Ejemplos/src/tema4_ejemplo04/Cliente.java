package tema4_ejemplo04;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Date;

public class Cliente {
    static final String HOST = "localhost";
    static final int PUERTO = 2000;
    
    public Cliente(){
        String datos = "";
        String num_cliente = "";
        
        // Leer del teclado
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        
        try {
            // Conectar
            Socket sCliente = new Socket(HOST, PUERTO);
            
            // Crear flujos
            DataInputStream flujoEntrada = new DataInputStream(sCliente.getInputStream());
            DataOutputStream flujoSalida = new DataOutputStream(sCliente.getOutputStream());
            
            // Cuerpo del Algoritmo
            datos = flujoEntrada.readUTF();
            long tiempo1 = Long.valueOf(datos);
            long tiempo2 = new Date().getTime();
            System.out.println("El tiempo es: " + (tiempo2 - tiempo1) + " ms.");
            
            // Cerrar conexión
            sCliente.close();
            
        } catch (Exception e) {
            e.getMessage();
        }
    }
    
    public static void main(String[] args) {
        new Cliente();
    }
}
