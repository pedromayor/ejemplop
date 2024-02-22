package ejercicio01;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
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
public class Principal {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        try {
            // 1º Solicito usuario y contraseña para crear la semilla
            String semilla = creaSemilla(sc);
            // 2º Genero la clave a partir de la semilla
            SecretKey clave = generaClaveAES(semilla);
            // 3º Almacenar en un fichero una cadena de texto cifrada
            escribeCifrado(clave, sc);
            // 4º Probarlo: Abrir fichero, descifrar e imprimir
            leeDescifrado(clave);
            
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchPaddingException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidKeyException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalBlockSizeException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (BadPaddingException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
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

    private static void escribeCifrado(SecretKey clave, Scanner sc) throws InvalidKeyException, IOException, NoSuchAlgorithmException, FileNotFoundException, IllegalBlockSizeException, UnsupportedEncodingException, NoSuchPaddingException, BadPaddingException {
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
        OutputStream archivoSalida = new FileOutputStream("Archivo.txt");
        archivoSalida.write(cipherText);
    }

    private static void leeDescifrado(SecretKey clave) throws IllegalBlockSizeException, InvalidKeyException, BadPaddingException, FileNotFoundException, NoSuchPaddingException, IOException, NoSuchAlgorithmException {
        byte[] cipherText;
        byte[] resultText;
        String resultado;

        // Creo el descifrador
        Cipher descifrador = Cipher.getInstance("AES/ECB/PKCS5Padding");
        // Inicio el descifrador
        descifrador.init(Cipher.DECRYPT_MODE, clave);
        // Leer Archivo.txt
        InputStream archivoEntrada = new FileInputStream("Archivo.txt");
        cipherText = archivoEntrada.readAllBytes();
        // Descifro el mensaje
        resultText = descifrador.doFinal(cipherText);
        // Muestro el mensaje por consola
        resultado = new String(resultText);
        System.out.println("Mensaje descifrado: \n" + resultado);
    }
}