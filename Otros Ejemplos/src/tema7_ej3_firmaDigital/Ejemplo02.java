/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tema7_ej3_firmaDigital;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.SignatureException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Pedro
 */
public class Ejemplo02 {
    public static void main(String[] args) {
        try {
            // Creamos un objeto firma
            Signature signature = Signature.getInstance("SHA236WithDSA");
            
            // Inicializamos el objeto firma con un número aleatorio seguro
            SecureRandom secureRandom = new SecureRandom();
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("DSA");
            KeyPair keyPair = keyPairGenerator.generateKeyPair();
            signature.initSign(keyPair.getPrivate(), secureRandom);
            
            // Creamos una firma de un texto
            byte[] data = "En un lugar de la mancha...".getBytes("UTF-8");
            signature.update(data);
            byte[] digitalSignature = signature.sign();
            
            // Verificamos la firma digital
            Signature signatureVerify = Signature.getInstance("SHA256WithDSA");
            signatureVerify.initVerify(keyPair.getPublic());
            
            // Para verificar necesitamos el mensaje original que firmó
            byte[] data2 = "En un lugar de la mancha...".getBytes("UTF-8");
            signatureVerify.update(data2);
            if(signatureVerify.verify(digitalSignature)){
                System.out.println("firma correcta");
            }else{
                System.out.println("firma incorrecta");
            }
            
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Ejemplo02.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidKeyException ex) {
            Logger.getLogger(Ejemplo02.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(Ejemplo02.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SignatureException ex) {
            Logger.getLogger(Ejemplo02.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
