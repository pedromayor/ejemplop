package ejercicio1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author Pedro
 */
public class Cliente {
    
    static final String HOST = "localhost";
    static final int PUERTO = 2000;
    
    public Cliente() {
            //Creo el scanner, númeroUsuario, la condición de victoria y el mensaje
            Scanner sc = new Scanner(System.in);
            int numeroUsuario;
            boolean victoria = false;
            String mensaje;
        
        try {
            //Creo el socket y los flujos de entrada y salida
            Socket socketCliente = new Socket(HOST, PUERTO);
            DataInputStream flujoEntrada = new DataInputStream(socketCliente.getInputStream());
            DataOutputStream flujoSalida = new DataOutputStream(socketCliente.getOutputStream());
            
            //Recibo el mensaje del servidor (el de adivina de 0 a 100)
            mensaje = flujoEntrada.readUTF().toString();
            System.out.println(mensaje);
            do {
                //pido numero y envio el número del usuario
                numeroUsuario = sc.nextInt();
                flujoSalida.writeUTF(Integer.toString(numeroUsuario));
                
                //Leo el mensaje de vuelta y se lo muestro al cliente
                mensaje = flujoEntrada.readUTF().toString();
                System.out.println(mensaje);
                
                //compruebo si acierta
                if (mensaje.equals("Enhorabuena, has acertado!")) {
                    victoria = true;
                }
            } while (!victoria);
            
            //Cierro el socket y el scanner
            socketCliente.close();
            sc.close();
            
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Hubo un error");
        }
    }
    
    public static void main(String[] args) {
        new Cliente();
    }
}