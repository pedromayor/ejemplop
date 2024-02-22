package ejercicio01;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
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
public class Cifrador {    
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);      

        try {
            // 1º Solicito usuario y contraseña para crear la semilla
            String semilla = creaSemilla(sc);
            // 2º Genero la clave a partir de la semilla
            SecretKey clave = generaClaveAES(semilla);
            // 3º Almacenar en un fichero una cadena de texto cifrada
            escribeCifrado(clave, sc);
            
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Cifrador.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchPaddingException ex) {
            Logger.getLogger(Cifrador.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidKeyException ex) {
            Logger.getLogger(Cifrador.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(Cifrador.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalBlockSizeException ex) {
            Logger.getLogger(Cifrador.class.getName()).log(Level.SEVERE, null, ex);
        } catch (BadPaddingException ex) {
            Logger.getLogger(Cifrador.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Cifrador.class.getName()).log(Level.SEVERE, null, ex);
        }

        sc.close();
    }

    private static String creaSemilla(Scanner sc) {
        System.out.println("Inserta usuario:");
        String user = sc.nextLine();
        System.out.println("Inserta contraseña:");
        String pass = sc.nextLine();
        return user + pass;
    }
    
    private static SecretKey generaClaveAES(String semilla) throws NoSuchAlgorithmException {
        SecureRandom sr = new SecureRandom(semilla.getBytes());
        KeyGenerator kg = KeyGenerator.getInstance("AES");
        kg.init(128, sr);
        SecretKey clave = kg.generateKey();
        return clave;
    }
    
    private static void escribeCifrado(SecretKey clave, Scanner sc) throws UnsupportedEncodingException, FileNotFoundException, IOException, NoSuchAlgorithmException, NoSuchPaddingException, BadPaddingException, InvalidKeyException, IllegalBlockSizeException {
        String mensaje;
        byte[] plainText;
        byte[] cipherText;
        
        // Creo el cifrador
        Cipher cifrador = Cipher.getInstance("AES/ECB/PKCS5Padding");
        // Inicio el cifrador
        cifrador.init(Cipher.ENCRYPT_MODE, clave);
        // Solicito mensaje a cifrar
        System.out.println("Inserta mensaje:");
        mensaje = sc.nextLine();
        plainText = mensaje.getBytes("UTF-8");
        // Cifro el mensaje
        cipherText = cifrador.doFinal(plainText);
        // Escribo en el fichero (si no existe lo crea) en la raiz del proyecto
        System.out.println("Inserta nombre de archivo:");
        String archivoNombre = sc.nextLine();
        OutputStream archivoSalida = new FileOutputStream(archivoNombre);
        archivoSalida.write(cipherText);
    }
}