package actividad01;

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
    
    public Cliente(){
        // Creo el scanner, numeroUsuario, condicion victoria y mensaje:
        Scanner sc = new Scanner(System.in);
        int numeroUsuario;
        boolean victoria = false;
        String mensaje;
        
        try {
            // Creo el socket y los flujos
            Socket sCliente = new Socket(HOST, PUERTO);
            DataInputStream flujoEntrada = new DataInputStream(sCliente.getInputStream());
            DataOutputStream flujoSalida = new DataOutputStream(sCliente.getOutputStream());
            
            // Recibo el mensaje del servidor
            mensaje = flujoEntrada.readUTF().toString();
            System.out.println(mensaje);
            do {      
                // Pido numero al usuario y se lo envío al servidor
                numeroUsuario = sc.nextInt();
                flujoSalida.writeUTF(Integer.toString(numeroUsuario));
                
                // Leo el mensaje de vuelta y se lo muestro al cliente
                mensaje = flujoEntrada.readUTF().toString();
                System.out.println(mensaje);
                
                // Compruebo si acierta
                if(mensaje.equals("Enhorabuena, has acertado!"))
                    victoria = true;
                
            } while (!victoria);
            
            // Cierro el socket y el scanner
            sCliente.close();
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
