package tema7_ej2_MessageDigest;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Pedro
 */
public class Prueba {
    public static void main(String[] args) {
        try {
            MessageDigest sha1 = MessageDigest.getInstance("SHA1");
            String texto = "Texto para el mensaje de ejemplo SHA1";
            sha1.update(texto.getBytes());              //obtiene el resumen
            byte[] resumen = sha1.digest();                   //completa la generación del resumen
            for (int i = 0; i < resumen.length; i++) {        //muestra el resumen
                System.out.println("(" + resumen[i] + ")");
            }
        } catch (NoSuchAlgorithmException ex) {
                ex.printStackTrace();
        }
        
    }
}