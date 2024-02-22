package tema7_ej4_Cipher;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;

/**
 *
 * @author Pedro
 */
public class Ejemplo01_Simetrico {
    // Esto solicita un mensaje para cifrarlo simétricamente.
    // La clave de cifrado/descifrado se genera usando una semilla segura de SecureRandom.
    // Luego se muestra el mensaje cifrado y el descifrado.
    
    public static void main(String[] args) {
        try {
            // Arrays Strings y Scanner
            byte[] plainText;
            byte[] cipherText;
            byte[] resultText;
            String mensaje;
            String resultado;
            Scanner sc = new Scanner(System.in);
            
            
            
            // Generamos la clave a partir de una semilla
            SecureRandom sr = new SecureRandom(("user1234").getBytes());
            KeyGenerator keyGen = KeyGenerator.getInstance("AES");
            keyGen.init(128, sr);
            SecretKey clave = keyGen.generateKey();
            
            
            // Creamos e inicializamos el cifrador
            Cipher cifrador = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cifrador.init(Cipher.ENCRYPT_MODE, clave);
            
            
            // Solicito al usuario el mensaje a cifrar
            System.out.print("Introduce el mensaje a encriptar: ");
            mensaje = sc.nextLine();
            
            
            // Encripto el mensaje y lo muestro
            // Como únicamente voy a encriptar un único bloque de datos
            // utilizo directamente el método "doFinal()" y no uso "update()"
            plainText = mensaje.getBytes("UTF-8");
            cipherText = cifrador.doFinal(plainText);
            System.out.println("Mensaje cifrado: " + cipherText.toString());
            
            
            // Creamos e inicializamos el descifrador
            // Es importante que usemos la misma clave, ya que si la clave
            // es una distinta no descifrará correctamente
            Cipher descifrador = Cipher.getInstance("AES/ECB/PKCS5Padding");
            descifrador.init(Cipher.DECRYPT_MODE, clave);
            
            
            // Descifro el mensaje encriptado y lo muestro
            // Igual que al cifrar, como sólo descifraré un bloque de datos
            // no utilizo el método update(), sólo utilizo "doFinal()"
            resultText = descifrador.doFinal(cipherText);
            
            
            // IMPORTANTE --> a la hora de mostrar el mensaje cifrado, en lugar
            // de utilizar el método "toString()" del array de bytes, hay que
            // instanciar un nuevo String con dicho array
            resultado = new String(resultText);
            System.out.println("Mensaje descifrado: " + resultado);
            
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Ejemplo01_Simetrico.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchPaddingException ex) {
            Logger.getLogger(Ejemplo01_Simetrico.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidKeyException ex) {
            Logger.getLogger(Ejemplo01_Simetrico.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(Ejemplo01_Simetrico.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalBlockSizeException ex) {
            Logger.getLogger(Ejemplo01_Simetrico.class.getName()).log(Level.SEVERE, null, ex);
        } catch (BadPaddingException ex) {
            Logger.getLogger(Ejemplo01_Simetrico.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}