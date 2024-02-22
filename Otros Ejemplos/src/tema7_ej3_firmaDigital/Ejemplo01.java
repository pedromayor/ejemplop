package tema7_ej3_firmaDigital;

import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;

/**
 *
 * @author Pedro
 */
public class Ejemplo01 {
    public static void main(String[] args) {
        String texto = "Texto de prueba para ser firmado";
        KeyPair clave = generarClaves();
        byte[] textoFirmado = hacerFirma(texto.getBytes(), clave.getPrivate());
        if(verificarFirma(texto.getBytes(), clave.getPublic(), textoFirmado)){
            System.out.println("Firma realizada y verificada");
        }else{
            System.out.println("Firma incorrecta");
        }
    }

    
    // Método que genera una pareja de claves pública/privada
    private static KeyPair generarClaves() {
        KeyPair claves = null;
        try {
            KeyPairGenerator generador = KeyPairGenerator.getInstance("DSA");
            claves = generador.genKeyPair();
        } catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace();
        }
        return claves;
    }

    
    // Método que realiza la firma digital y la devuelve
    private static byte[] hacerFirma(byte[] datos, PrivateKey clave) {
        byte[] firmado = null;
        try {
            Signature firma = Signature.getInstance("DSA"); // Crea el objeto firma
            firma.initSign(clave);                         // Inicializa la firma con la clave
            firma.update(datos);                                // Obtiene el resumen del mensaje
            firmado = firma.sign();                                  // Obtiene la firma digital
        } catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace();
        } catch (InvalidKeyException ex) {
            ex.printStackTrace();
        } catch (SignatureException ex) {
            ex.printStackTrace();
        }
        return firmado;
    }

    
    // Método que verifica la firma digital true (correcta) false (incorrecta)
    private static boolean verificarFirma(byte[] texto, PublicKey clave, byte[] textoFirmado) {
        try {
            Signature firma = Signature.getInstance("DSA");
            firma.initVerify(clave);                        // Verifica la clave pública
            firma.update(texto);                                 // Actualiza el resumen de mensaje
            return (firma.verify(textoFirmado));            // Devuelve la verificación
        } catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace();
        } catch (InvalidKeyException ex) {
            ex.printStackTrace();
        } catch (SignatureException ex) {
            ex.printStackTrace();
        }
        return false;
    }
}