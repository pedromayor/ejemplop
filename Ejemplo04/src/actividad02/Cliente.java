package actividad02;

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
    static final int PUERTO = 1500;

    public Cliente() {
        Scanner sc = new Scanner(System.in);
        String mensaje;

        try {
            // Creo el socket y los flujos
            Socket sCliente = new Socket(HOST, PUERTO);
            DataInputStream flujoEntrada = new DataInputStream(sCliente.getInputStream());
            DataOutputStream flujoSalida = new DataOutputStream(sCliente.getOutputStream());

            // Pido el archivo
            System.out.println("Ingresa nombre de archivo...");
            mensaje = sc.nextLine();
            flujoSalida.writeUTF(mensaje);

            // Recibo respuesta
            mensaje = flujoEntrada.readUTF();
            if (mensaje.equals("archivo encontrado")) {
                // Si es un archivo, leer y mostrar el contenido
                System.out.println("Archivo encontrado, leyendo archivo:");
                byte[] buffer = new byte[1024];
                int bytesLeidos;
                while ((bytesLeidos = flujoEntrada.read(buffer)) > 0) {
                    System.out.write(buffer, 0, bytesLeidos);
                }
                System.out.println();
                System.out.println("Lectura terminada.");
            } else {
                System.out.println(mensaje);
            }

            // Cierro el socket y el scanner
            
            System.out.println("Me desconecto.");
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
