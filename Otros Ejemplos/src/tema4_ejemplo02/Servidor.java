/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tema4_ejemplo02;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Pedro
 */
public class Servidor extends Thread{
    Socket sCliente;
    static final int PUERTO = 2000;
    
    public Servidor(Socket sCliente){
        this.sCliente = sCliente;
    }
    
    public static void main(String[] args) {
        try {
            // Iniciar el servidor en el puerto
            ServerSocket sServidor = new ServerSocket(PUERTO);
            System.out.println("Escucho el puerto" + PUERTO);
            
            while(true){
                // Se conecta un cliente
                Socket sCliente = sServidor.accept();
                System.out.println("Cliente conectado");
                
                // Atiendo al cliente en un hilo
                new Servidor(sCliente).start();
            }
            
        } catch (Exception e) {System.out.println(e.getMessage());
        }
    }
    
    public void run(){
        try {
            // Crear flujos
            DataInputStream flujoEntrada = new DataInputStream(sCliente.getInputStream());
            DataOutputStream flujoSalida = new DataOutputStream(sCliente.getOutputStream());
            
            // Atender cliente
            flujoSalida.writeUTF("Se ha conectado el cliente de forma correcta");
            
            //Cerrar conexión
            sCliente.close();
            System.out.println("Cliente desconectado");
        } catch (Exception e) {System.out.println(e.getMessage());
        }
    }
}
