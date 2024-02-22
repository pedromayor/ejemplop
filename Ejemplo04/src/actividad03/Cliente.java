package actividad03;

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

            // Conecto al usuario:
            // Usuario:
            mensaje = flujoEntrada.readUTF();       // Recibe
            System.out.println(mensaje);          // Imprime
            String usuario = sc.nextLine();         // Captura
            flujoSalida.writeUTF(usuario);       // Envia
            // Contraseña:
            mensaje = flujoEntrada.readUTF();       // Recibe
            System.out.println(mensaje);          // Imprime
            String contrasena = sc.nextLine();      // Captura
            flujoSalida.writeUTF(contrasena);    // Envia

            // Compruebo: Recibo Saludo o Error
            mensaje = flujoEntrada.readUTF();       // Recibe
            System.out.println(mensaje);          // Imprime

            // Si he recibido Saludo:
            if (mensaje.startsWith("Datos correctos.")) {
                while (true) {
                    String menu = flujoEntrada.readUTF();
                    System.out.println(menu);
                    int opcion = Integer.parseInt(sc.nextLine());
                    flujoSalida.writeUTF(Integer.toString(opcion));

                    switch (opcion) {

                        // Leer directorio actual:
                        case 1:
                            mensaje = flujoEntrada.readUTF();       // Recibe
                            System.out.println(mensaje);          // Imprime
                            break;

                        // Leer archivo:
                        case 2:
                            mensaje = flujoEntrada.readUTF();       // Recibe
                            System.out.println(mensaje);          // Imprime
                            String nombreArchivo = sc.nextLine();   // Caputra
                            flujoSalida.writeUTF(nombreArchivo); // Envia
                            mensaje = flujoEntrada.readUTF();       // Recibe
                            if (mensaje.startsWith("Archivo encontrado")) {
                                // Si es un archivo, leer y mostrar el contenido
                                System.out.println(mensaje);
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
                            break;

                        // Salir:
                        case 3:
                            mensaje = flujoEntrada.readUTF();       // Recibe
                            System.out.println(mensaje);          // Imprime
                            sCliente.close();                       // Cierra
                            sc.close();
                            return;
                        default:
                            mensaje = flujoEntrada.readUTF();
                            System.out.println(mensaje);
                    }
                }
            } else {
                // Cierro el socket y el scanner
                mensaje = flujoEntrada.readUTF();
                System.out.println(mensaje);
                sCliente.close();
                sc.close();
            }

        } catch (Exception e) {
        }
    }

    public static void main(String[] args) {
        new Cliente();
    }
}