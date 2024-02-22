package actividad02;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Pedro
 */
public class Servidor extends Thread {

    Socket sCliente;
    static final int PUERTO = 1500;
    int idCliente;

    public Servidor(Socket sCliente, int idCliente) {
        this.sCliente = sCliente;
        this.idCliente = idCliente;
    }

    public static void main(String[] args) {
        int idCliente = 1;
        try {
            // Inicio el servidor:
            ServerSocket sServidor = new ServerSocket(PUERTO);
            System.out.println("Servidor rulando... Escuchando puerto: " + PUERTO);

            // Arranco el servidor
            while (true) {
                // Se conecta un cliente
                Socket sCliente = sServidor.accept();
                System.out.println("Cliente " + idCliente + " conectado.");

                // Lo atiendo en su hilo propio:
                new Servidor(sCliente, idCliente).start();
                idCliente++;
            }

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Hubo un error");
        }
    }

    @Override
    public void run() {

        try {
            DataOutputStream flujoSalida = new DataOutputStream(sCliente.getOutputStream());
            DataInputStream flujoEntrada = new DataInputStream(sCliente.getInputStream());
            String nombreArchivo;

            // Recibo el nombre del archivo:
            nombreArchivo = flujoEntrada.readUTF();
            System.out.println("Solicita archivo: " + nombreArchivo);

            // Compruebo si el archivo existe, si existe lo envío y si no envío un error:
            File archivo = new File(nombreArchivo);
            if (archivo.exists()) {
                FileInputStream fileInput = new FileInputStream(nombreArchivo);
                byte[] buffer = new byte[1024];

                // Envío indicador de archivo al cliente:
                flujoSalida.writeUTF("archivo encontrado");

                // Enviar contenido del archivo
                int bytesLeidos;
                while ((bytesLeidos = fileInput.read(buffer)) > 0) {
                    flujoSalida.write(buffer, 0, bytesLeidos);
                }

                fileInput.close();
            } else {
                flujoSalida.writeUTF("Error: El archivo '" + nombreArchivo + "' no existe.");
            }

            // Cierro sockets:
            sCliente.close();
            System.out.println("Conexión con Cliente " + idCliente + " cerrada.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
