package ejercicio2;

import java.io.File;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Servidor {

    public static void main(String[] args) {
        try {
            // Creo el DatagramSocket
            DatagramSocket sSocket = new DatagramSocket(1501);
            // Construye la dirección del socket del receptor
            InetAddress maquina = InetAddress.getByName("localhost");
            int puerto = 1500;

            boolean activo = true;

            // Crea el mensaje
            byte[] cadena = new byte[1000];

            System.out.println("Servidor escuchando...");
            while (activo) {
                // Recibe mensaje
                DatagramPacket mensajeRecibido = new DatagramPacket(cadena, cadena.length);
                sSocket.receive(mensajeRecibido);
                String nombreArchivo = new String(mensajeRecibido.getData(), 0, mensajeRecibido.getLength());
                System.out.println("Mensaje Recibido: " + nombreArchivo);

                // Crea el archivo y comprueba si el fichero existe
                // Archivo.txt está en la raíz del proyecto. Si preguntamos por ese archivo, acierta.
                File archivo = new File(nombreArchivo);
                if (archivo.exists()) {
                    // Si existe, envía el fichero al cliente y activo off
                    String mensajeExito = "El archivo existe";
                    byte[] enviar = mensajeExito.getBytes();
                    DatagramPacket mensajeEnviadoExitoso = new DatagramPacket(enviar, enviar.length, maquina, puerto);
                    sSocket.send(mensajeEnviadoExitoso);
                    
                    //Envía el archivo
                    cadena = Files.readAllBytes(Paths.get(nombreArchivo));
                    DatagramPacket enviarArchivo = new DatagramPacket(cadena, cadena.length, maquina, puerto);
                    sSocket.send(enviarArchivo);
                    
                    //Salgo del bucle
                    activo = false;

                } else {
                    // Si no existe, envía un mensaje de error y repite
                    String mensajeError = "El archivo no existe, dime otro:";
                    byte[] enviar = mensajeError.getBytes();
                    DatagramPacket mensajeEnviadoError = new DatagramPacket(enviar, enviar.length, maquina, puerto);
                    sSocket.send(mensajeEnviadoError);
                }
            }

            // Cierra la conexión
            sSocket.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
