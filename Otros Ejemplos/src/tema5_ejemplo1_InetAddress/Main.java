package tema5_ejemplo1_InetAddress;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    public static void main(String[] args) {
            
        try {
            // RED LOCAL
            System.out.println("*** RED LOCAL ***");
            
            // Obtener el objeto InetAddress de localhost:
            InetAddress host = InetAddress.getByName("localhost");
            // Imprimir sus datos:
            System.out.println("IP de localhost: " + host.getHostAddress());
            System.out.println("Nombre: " + host.getHostName());
            
            // EN INTERNET
            System.out.println("*** INTERNET ***");
            // Obtener el objeto InetAddress de ead.murciaeduca.es
            InetAddress hostEAD = InetAddress.getByName("ead.murciaeduca.es");
            // Imprimir sus datos:
            System.out.println("IP de EAD: " + hostEAD.getHostAddress());
            System.out.println("Nombre: " + hostEAD.getHostName());
            
            // GOOGLE
            InetAddress[] hostMatriz = InetAddress.getAllByName("google.com");
            System.out.println("Google tiene, " + hostMatriz.length + ":");
            for (InetAddress hostIndividual : hostMatriz) {
                System.out.println(hostIndividual.getHostAddress());
            }
            
        } catch (UnknownHostException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
}
