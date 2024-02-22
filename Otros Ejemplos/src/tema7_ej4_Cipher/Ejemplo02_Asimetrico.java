package tema7_ej4_Cipher;

import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

/**
 *
 * @author Pedro
 */
public class Ejemplo02_Asimetrico {
    public static void main(String[] args) {
        try {
            // Generamos una pareja de claves
            SecureRandom sr = new SecureRandom();
            KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
            kpg.initialize(2048, sr);
            KeyPair kp = kpg.generateKeyPair();
            
            // Encriptamos con Cipher y la clave pública
            Cipher cifrador = Cipher.getInstance("RSA");
            cifrador.init(Cipher.ENCRYPT_MODE, kp.getPublic());
            byte[] datos = "En un lugar de la mancha".getBytes();
            byte[] datosCifrados = cifrador.doFinal(datos);
            System.out.println("Datos cifrados: \n" + new String(datosCifrados));
            
            // Creo otro cipher y descifro con la clave privada
            Cipher descifrador = Cipher.getInstance("RSA");
            descifrador.init(Cipher.DECRYPT_MODE, kp.getPrivate());
            byte[] datosDescifrados = descifrador.doFinal(datosCifrados);
            System.out.println("Datos descifrados: \n" + new String(datosDescifrados));
            
            
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Ejemplo02_Asimetrico.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchPaddingException ex) {
            Logger.getLogger(Ejemplo02_Asimetrico.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidKeyException ex) {
            Logger.getLogger(Ejemplo02_Asimetrico.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalBlockSizeException ex) {
            Logger.getLogger(Ejemplo02_Asimetrico.class.getName()).log(Level.SEVERE, null, ex);
        } catch (BadPaddingException ex) {
            Logger.getLogger(Ejemplo02_Asimetrico.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
