/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tema7_ej5_Sockets;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

/**
 *
 * @author Pedro
 */
public class Ejemplo02_Cliente {

    public static void main(String[] args) {
        try {
            //Declara un objeto tipo Factory para crear sockets SSL
            SSLSocketFactory facty = (SSLSocketFactory) SSLSocketFactory.getDefault();
            //Crea un socket seguro
            SSLSocket socketSsl = (SSLSocket) facty.createSocket("localhost", 5000);
            //Consola que lee la entrada del usuario
            BufferedReader entrada = new BufferedReader(new InputStreamReader(System.in));
            //Canal de comunicación con el servidor
            BufferedWriter salida = new BufferedWriter(new OutputStreamWriter(socketSsl.getOutputStream()));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
