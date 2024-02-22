package actividad03;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 *
 * @author Pedro
 */
public class Servidor extends Thread {

    private static final int PUERTO = 1500;
    private static final String USUARIO = "arturo";
    private static final String CONTRASENA = "secreta";

    private Socket sCliente;
    private int idCliente;

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

            // Conecto al usuario:
            // Usuario:
            flujoSalida.writeUTF("Introduce usuario:");
            String usuario = flujoEntrada.readUTF();
            // Contraseña:
            flujoSalida.writeUTF("Introduce contraseña:");
            String contrasena = flujoEntrada.readUTF();

            // Compruebo:
            if (usuario.equals(USUARIO) && contrasena.equals(CONTRASENA)) {
                // Envio Saludo
                flujoSalida.writeUTF("Datos correctos. Hola, " + usuario);

                // Inicia el programa
                while (true) {
                    flujoSalida.writeUTF("Principal: \n"
                            + "1 - Ver contenido del directorio actual. \n"
                            + "2 - Mostrar contenido de un archivo. \n"
                            + "3 - Salir.");
                    int opcion = Integer.parseInt(flujoEntrada.readUTF());

                    switch (opcion) {

                        // Leer directorio actual:
                        case 1:
                            File directorioActual = new File(".");
                            File[] archivos = directorioActual.listFiles();
                            ArrayList<String> nombresArchivos = new ArrayList<String>();
                            for (File archivo : archivos) {
                                nombresArchivos.add(archivo.getName());
                            }
                            flujoSalida.writeUTF("Contenido directorio: \n"
                                    + nombresArchivos.toString());
                            break;

                        // Leer archivo:
                        case 2:
                            flujoSalida.writeUTF("Inserta nombre de archivo:");
                            String nombreArchivo = flujoEntrada.readUTF();
                            File archivo = new File(nombreArchivo);

                            if (archivo.exists()) {
                                FileInputStream fileInput = new FileInputStream(nombreArchivo);
                                byte[] buffer = new byte[1024];
                                flujoSalida.writeUTF("Archivo encontrado, leyendo archivo:");
                                int bytesLeidos;
                                while ((bytesLeidos = fileInput.read(buffer)) > 0) {
                                    flujoSalida.write(buffer, 0, bytesLeidos);
                                }
                                fileInput.close();
                            } else {
                                flujoSalida.writeUTF("Error: El archivo '" + nombreArchivo + "' no existe.");
                            }
                            break;

                        // Salir:
                        case 3:
                            flujoSalida.writeUTF("Hasta pronto.");
                            return;
                        default:
                            flujoSalida.writeUTF("Opción no válida. Inserta 1, 2 o 3.");
                    }
                }

            } else {
                // Envio Error
                flujoSalida.writeUTF("Datos incorrectos, desconectando.");
            }

            // Cierro sockets:
            sCliente.close();
            System.out.println("Conexión con Cliente " + idCliente + " cerrada.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
