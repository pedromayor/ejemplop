package ejercicio01;

import java.io.IOException;
import java.util.logging.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.Scanner;

/**
 *
 * @author Pedro
 */
public class Principal {
    
    // 0) Configurar un logger
    private static Logger logger = configurarLogger();
    
    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        // 1) Solicitar usuario:
        String nombreUsuario = solicitarNombreUsuario(sc);
        // 2) Solicitar archivo:
        String nombreArchivo = solicitarNombreArchivo(sc);
        // 3) Visualizar
        visualizarArchivo(nombreUsuario, nombreArchivo);
        sc.close();
    }

    private static String solicitarNombreUsuario(Scanner sc) {
        String nombreUsuario;
        do {            
            System.out.println("Inserta usuario (8 letras minúsculas):");
            nombreUsuario = sc.nextLine();
            logger.log(Level.INFO, capturarMomento() + " Usuario inserta: " + nombreUsuario);
        } while (!nombreUsuario.matches("[a-z]{8}"));
        return nombreUsuario;
    }

    private static String solicitarNombreArchivo(Scanner sc) {
        String nombreArchivo;
        do {            
            System.out.println("Inserta archivo :");
            nombreArchivo = sc.nextLine();
            logger.log(Level.INFO, capturarMomento() + " Usuario inserta: " + nombreArchivo);
        } while (!nombreArchivo.matches("[a-zA-Z]{1,8}\\.[a-zA-Z]{3}"));
        return nombreArchivo;
    }

    private static void visualizarArchivo(String nombreUsuario, String nombreArchivo) {
        Path path = Paths.get("c:\\datos\\" + nombreArchivo);
        String contenido;
        try {
            contenido = new String(Files.readAllBytes(path));
            System.out.println("Contenido del archvio: " + nombreArchivo + "\n" + contenido);
            logger.log(Level.INFO, capturarMomento() + " Muestro " + nombreArchivo + " a: " + nombreUsuario);
        } catch (IOException ex) {
            System.out.println("Error al leer el archivo.");
            logger.log(Level.WARNING, capturarMomento() + " Error al leer: " + nombreArchivo + " por: " + nombreUsuario);
        } catch (Exception e){
            System.out.println("Error General");
            logger.log(Level.SEVERE, capturarMomento() + " Archivo: " + nombreArchivo + " usuario: " + nombreUsuario);
        } finally{
            System.out.println("Programa terminado.");
            System.out.println("Adiós " + nombreUsuario);
        }
    }

    private static Logger configurarLogger() {
        Logger logger = Logger.getLogger("MyLog");
        try {
            // Configuro el logger y establezco el formato
            FileHandler fh = new FileHandler("D:\\Prueba\\MyLogFile.log", true);
            logger.addHandler(fh);
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return logger;
    }

    private static String capturarMomento() {
        GregorianCalendar gc = new GregorianCalendar();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd HH:mm:ss");
        String momento = sdf.format(gc.getTime());
        return momento;
    }
}