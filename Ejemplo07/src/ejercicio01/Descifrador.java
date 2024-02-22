package ejercicio01;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
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
public class Descifrador {    
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        try {
            // 1º Solicito usuario y contraseña para crear la semilla
            String semilla = creaSemilla(sc);
            // 2º Genero la clave a partir de la semilla
            SecretKey clave = generaClaveAES(semilla);
            // 3º Probarlo: Abrir fichero, descifrar e imprimir
            leeDescifrado(clave, sc);
            
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Descifrador.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchPaddingException ex) {
            Logger.getLogger(Descifrador.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidKeyException ex) {
            Logger.getLogger(Descifrador.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(Descifrador.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalBlockSizeException ex) {
            Logger.getLogger(Descifrador.class.getName()).log(Level.SEVERE, null, ex);
        } catch (BadPaddingException ex) {
            Logger.getLogger(Descifrador.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Descifrador.class.getName()).log(Level.SEVERE, null, ex);
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
    
    private static void leeDescifrado(SecretKey clave, Scanner sc) throws IllegalBlockSizeException, IOException, NoSuchAlgorithmException, BadPaddingException, FileNotFoundException, NoSuchPaddingException, InvalidKeyException {
        byte[] cipherText;
        byte[] resultText;
        String resultado;
        
        // Creo el descifrador
        Cipher descifrador = Cipher.getInstance("AES/ECB/PKCS5Padding");
        // Inicio el descifrador
        descifrador.init(Cipher.DECRYPT_MODE, clave);
        // Leer Archivo.txt
        System.out.println("Inserta nombre de archivo:");
        String archivoNombre = sc.nextLine();
        InputStream archivoEntrada = new FileInputStream(archivoNombre);
        cipherText = archivoEntrada.readAllBytes();
        // Descifro el mensaje
        resultText = descifrador.doFinal(cipherText);
        // Muestro el mensaje por consola
        resultado = new String(resultText);
        System.out.println("Mensaje descifrado: \n"+ resultado);
    }
}