package tema7_ej4_Cipher;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

/**
 *
 * @author Pedro
 */
public class Ejemplo03_Archivos {
    // Lee una clave por teclado, convierte la clave leída a un algoritmo DES
    // 1º parámetro args[0] = -c cifra -d descifra
    // 2º parámetro args[1] = fichero de entrada, desde donde se leen los datos
    // 3º parámetro args[2] = fichero de salida, donde se dejará el resultado cifrado o descifrado
    
    public static void main(String[] args) {
        String comando1 = "-c";
        String comando2 = "-d";
        
        // Comando 1 o Comando 2
        if((comando1.equals(args[0])) || (comando2.equals(args[0]))){
            try {
                // Leer clave por teclado
                InputStreamReader leer_clave = new InputStreamReader(System.in);
                BufferedReader buff_clave = new BufferedReader(leer_clave);
                System.out.println("Escriba una clave: ");
                String clave = buff_clave.readLine();
                
                // Pasar clave a la clase SecretKey
                SecretKeyFactory skf = SecretKeyFactory.getInstance("DES");
                DESKeySpec kspec = new DESKeySpec(clave.getBytes());
                SecretKey ks = skf.generateSecret(kspec);
                
                // Inicializar el cifrado
                Cipher cifrado = Cipher.getInstance("DES");
                
                // Escojo el modo según sea Cifrar o Descifrar
                if(comando1.equals(args[0])) cifrado.init(Cipher.ENCRYPT_MODE, ks); // Cifrar
                if(comando2.equals(args[0])) cifrado.init(Cipher.DECRYPT_MODE, ks); // Descifrar
                
                // Leer fichero
                InputStream archivoEntrada = new FileInputStream(args[1]);
                OutputStream archivoSalida = new FileOutputStream(args[2]);
                byte[] buffer = new byte[1024];
                byte[] bloque_cifrado;
                String textoCifrado = new String();
                int fin_archivo =-1;
                int leidos;
                leidos = archivoEntrada.read(buffer);
                while(leidos != fin_archivo){
                    bloque_cifrado = cifrado.update(buffer, 0, leidos);
                    textoCifrado = textoCifrado + new String(bloque_cifrado, "ISO-8859-1");
                    leidos = archivoEntrada.read(buffer);
                }
                archivoEntrada.close();
                bloque_cifrado = cifrado.doFinal();
                textoCifrado = textoCifrado + new String(bloque_cifrado, "ISO-8859-1");
                
                // Escribir fichero
                archivoSalida.write(textoCifrado.getBytes("ISO-8859-1"));
                
            } catch (IOException ex) {
                ex.printStackTrace();
            } catch (NoSuchAlgorithmException ex) {
                ex.printStackTrace();
            } catch (InvalidKeyException ex) {
                ex.printStackTrace();
            } catch (InvalidKeySpecException ex) {
                ex.printStackTrace();
            } catch (NoSuchPaddingException ex) {
                ex.printStackTrace();
            } catch (IllegalBlockSizeException ex) {
                ex.printStackTrace();
            } catch (BadPaddingException ex) {
                ex.printStackTrace();
            }
        }
    }
}