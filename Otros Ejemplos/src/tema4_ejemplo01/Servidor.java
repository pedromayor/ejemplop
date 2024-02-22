package tema4_ejemplo01;

import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    static final int PUERTO=2000;
    
    public Servidor(){
        try {
            ServerSocket sServidor = new ServerSocket(PUERTO);
            System.out.println("Escucho el puerto " + PUERTO);
            
            for (int nCli = 0; nCli < 3; nCli++) {
                Socket sCliente = sServidor.accept();
                System.out.println("Sirvo al cliente " + nCli);
                DataOutputStream flujoSalida = new DataOutputStream(sCliente.getOutputStream());
                
                flujoSalida.writeUTF("Hola cliente " + nCli);
                sCliente.close();
            }
            
            System.out.println("Se han atendido los clientes");

        } catch (Exception e) {System.out.println(e.getMessage());
        }
    }
    
    public static void main(String[] args) {
        new Servidor();
    }
    
}
