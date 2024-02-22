package tema3_socketsUDP;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class Emisor {

    public static void main(String[] args) {

        //Comprobamos los argumentos
        //if (args.length != 2) {
        //    System.err.print("USo: java EmisorUDP maquina mensaje");
        //} else
        try {
            //Crea el socket
            DatagramSocket sSocket = new DatagramSocket();

            //Construye la dirección del socket del receptor
            //InetAddress maquina = InetAddress.getByName(args[0]);
            InetAddress maquina = InetAddress.getByName("localhost");
            int Puerto = 1500;

            //Crea el mensaje
            //byte[] cadena = args[1].getBytes();
            String cadenaString = "un ejemplo del mensaje";
            byte[] cadena = cadenaString.getBytes();
            //DatagramPacket mensaje = new DatagramPacket(cadena, args[1].length(), maquina, Puerto);
            DatagramPacket mensaje = new DatagramPacket(cadena, cadenaString.length(), maquina, Puerto);


            //Envía el mensaje
            sSocket.send(mensaje);

            //Cierra el socket
            sSocket.close();
        } catch (UnknownHostException e) {
            System.err.println("Desconocido: " + e.getMessage());
        } catch (SocketException e) {
            System.err.println("Socket: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("E/S: " + e.getMessage());
        }

    }
}
