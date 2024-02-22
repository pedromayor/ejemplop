package ejercicio2;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

/*
    - Entiendo que no es lo más aconsejable hacerlo con UDP, porque aquí se van a comunicar cliente/servidor
    y van a estar mandándose mensajes el uno al otro, y si el emisor usa el puerto UDP para enviar, el
    receptor no podrá hacer uso del mismo puerto, tendrá que usar otro.
    - Además DatagramPacket no es aconsejable para enviar archivos, ya que no ordena y verifica los datos 
    que se han enviado.
    - Pero aún así, me he decidido a hacerlo por UDP porque ya había usado TCP en el primer ejercicio y quería probar.

*/

public class Cliente {

    public static void main(String[] args) {
        try {
            // Creo el DatagramSocket
            DatagramSocket sSocket = new DatagramSocket(1500);
            // Construye la dirección del socket del receptor
            InetAddress maquina = InetAddress.getByName("localhost");
            int puerto = 1501;
            
            Scanner sc = new Scanner(System.in);
            boolean finaliza = false;

            // Crea el mensaje
            byte[] cadena;

            while(!finaliza){
            // Pido el nombre del archivo y lo envío
            // Archivo.txt está en la raíz del proyecto. Si preguntamos por ese archivo, acierta.
            System.out.println("Dime el nombre de un archivo con su extensión");
            String nombreArchivo = sc.nextLine();
            cadena = nombreArchivo.getBytes();
            DatagramPacket mensajeEnviado = new DatagramPacket(cadena, cadena.length, maquina, puerto);
            sSocket.send(mensajeEnviado);

            // Recibe la respuesta
            cadena = new byte[1000];
            DatagramPacket mensajeRecibido = new DatagramPacket(cadena, cadena.length);
            sSocket.receive(mensajeRecibido);
            String nombreMensaje = new String(mensajeRecibido.getData(), 0, mensajeRecibido.getLength());
            System.out.println("Mensaje Recibido: " + nombreMensaje);
            
            if(nombreMensaje.equals("El archivo existe")) {
                // Recibe el archivo
                cadena = new byte[1000];
                DatagramPacket archivoRecibido = new DatagramPacket(cadena,cadena.length);
                sSocket.receive(archivoRecibido);
                
                // Finaliza el bucle
                finaliza=true;
            }
            
            }

            // Finaliza la conexión y el scanner
            sSocket.close();
            sc.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
