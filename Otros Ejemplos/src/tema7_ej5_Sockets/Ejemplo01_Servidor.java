package tema7_ej5_Sockets;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocket;

/**
 *
 * @author Pedro
 */
public class Ejemplo01_Servidor {

    public static void main(String[] args) {
        try {
            //Declara objeto tipo Factory para crear socket SSL servidor
            SSLServerSocketFactory facto = (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();
            //Crea un socket servidor seguro
            SSLServerSocket socketServidorSsl = (SSLServerSocket) facto.createServerSocket(5000);
            SSLSocket socketSsl = (SSLSocket) socketServidorSsl.accept();
            //crea canal seguro sobre el socket abierto
            BufferedReader entrada = new BufferedReader(new InputStreamReader(socketSsl.getInputStream()));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}