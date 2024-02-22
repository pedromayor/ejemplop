package tema5_ejemplo3_FTP;

//librerías de apache para FTP
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

//librerías de java
import java.io.IOException;
import java.io.FileOutputStream;
import java.net.SocketException;


// Clase para recuperar un fichero de un servidorFTP, en la carpeta raiz del proyecto.
// Puede ser necesario deshabilitar el cortafuegos.

public class Ejercicio01 {

    //objeto FTPClient, con métodos para interactuar con archvios de un servidor FTP.
    private static FTPClient clienteFTP;
    //flujo de salida para la escritura de datos en un fichero.
    private static FileOutputStream ficheroObtenido;
    //URL del servidor
    private static String servidorURL = "ftp.rediris.es";
    //ruta relativa (en Servidor FTP) de la carpeta que contiene el fichero a descargar.
    private static String rutaFichero = "debian";
    //nombre del fichero (este carece de extensión, es texto plano).
    private static String nombreFichero = "README";
    //usuario
    private static String usuario = "anonymous";
    //contraseña
    private static String password = "";
    //array de carpetas disponibles
    private static String[] nombreCarpeta;

    
    
    public static void main(String[] args) {
        try {
            int reply;
            //creación del objeto cliente FTP
            clienteFTP = new FTPClient();
            //conexión del cliente al servidor FTP
            clienteFTP.connect(servidorURL);
            //omprobación de la conexión
            reply = clienteFTP.getReplyCode();
            //si la conexión  es satisfactoria
            if (FTPReply.isPositiveCompletion(reply)) {
                //abre una sesión con el usuario anónimo
                clienteFTP.login(usuario, password);
                //lista las carpetas de primer nivel del servidor FTP
                System.out.println("Carpetas disponibles en el Servidor:");
                nombreCarpeta = clienteFTP.listNames();
                for (int i = 0; i < nombreCarpeta.length; i++) {
                    System.out.println(nombreCarpeta[i]);
                }
                //nombre que el que va a recuperarse
                ficheroObtenido = new FileOutputStream(nombreFichero);
                //mensaje
                System.out.println("\nDescargando el fichero " + nombreFichero + " de "
                        + "la carpeta " + rutaFichero);
                //recupera el contenido del fichero en el Servidor, y lo escribe en el
                //nuevo fichero del directorio del proyecto
                clienteFTP.retrieveFile("/" + rutaFichero + "/"
                        + nombreFichero, ficheroObtenido);
                //cierra el nuevo fichero
                ficheroObtenido.close();
                //cierra la conexión con el Servidor
                clienteFTP.disconnect();
                //
                System.out.println("Descarga finalizada correctamente");
            } else {
                //desconecta
                clienteFTP.disconnect();
                System.err.println("FTP ha rechazado la conexión esblecida");
                System.exit(1);
            }
        } catch (SocketException ex) {
            //error de Socket
            System.out.println(ex.toString());
        } catch (IOException ex) {
            //error de fichero
            System.out.println(ex.toString());
        }
    }
}