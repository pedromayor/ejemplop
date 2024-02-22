package tema5_ejemplo2_URL;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Ejemplo01 {
    public static void main(String[] args) {
        String urlString = "https://google.com";
        String pathString = "google.html";
        
        // Crear una URLConnection
        try {
            URL url01 = new URL(urlString);
            URLConnection urlCon = url01.openConnection();
            
            InputStream inputStream = urlCon.getInputStream();
            BufferedInputStream reader = new BufferedInputStream(inputStream);
            BufferedOutputStream writer = new BufferedOutputStream(new FileOutputStream(pathString));
            
            // Crea un archivo google.html en la raíz del proyecto
            // lee el google.com y escribe lo que hay en google.html
            byte[] buffer = new byte[4096];
            int bytesRead = -1;
            while ((bytesRead = reader.read(buffer)) != -1){
                writer.write(buffer, 0, bytesRead);
            }
            
            writer.close();
            reader.close();
            
        } catch (MalformedURLException ex) {
            Logger.getLogger(Ejemplo01.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Ejemplo01.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
         // Más ejemplos en: https://www.codejava.net/java-se/networking/java-urlconnection-and-httpurlconnection-examples
        
    }
}
