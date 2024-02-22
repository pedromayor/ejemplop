package tema7_ej01_KeyPair;

import java.security.KeyPair;
import java.security.KeyPairGenerator;

/**
 *
 * @author Pedro
 */
public class Main {
    // Crea una pareja de claves (pública y privada) y las muestra:
    public static void main(String[] args) {
        
        //Creo un objeto claves y le asigno claves usando el método:
        KeyPair claves = GeneraParejaClaves();
        
        //Imprimo el valor de las claves:
        System.out.println(
                "Algoritmo KPrivada: " + claves.getPrivate().getAlgorithm()+ "\n" + 
                "Codificación KPrivada" + claves.getPrivate().getFormat() + "\n" + 
                "Bytes KPrivada" + claves.getPrivate().toString());
        System.out.println(
                "Algoritmo KPública: " + claves.getPublic().getAlgorithm()+ "\n" + 
                "Codificación KPública" + claves.getPublic().getFormat() + "\n" + 
                "Bytes KPública" + claves.getPublic().toString());
    }

    //Método que genera una clave tipo KeyPair
    private static KeyPair GeneraParejaClaves() {
        KeyPair claves = null;
        try {
            KeyPairGenerator genera = KeyPairGenerator.getInstance("RSA");
            genera.initialize(512);     //tamaño clave
            claves = genera.generateKeyPair();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return claves;
    }
}