package main;

import java.io.IOException;

/**
 *
 * @author Pedro
 */
public class Colaborar {
    public static void main(String[] args) throws IOException {
        //Compruebo que se pasan los argumentos necesarios:
        if(args.length != 1){
            System.out.println("Error: Especifica el número de procesos");
            System.exit(1);
        }
        
        for (int i = 1; i < 11; i++) {
            //Comando para lanzar el proceso
            String comando = "java -jar lenguaje.jar " + (i*10) + " " + args[0];
            
            //Lanzo el proceso
            Runtime.getRuntime().exec(comando);
            /*
            //Alternativa con ProcessBuilder:
            ProcessBuilder pb = new ProcessBuilder();
            pb.command(comando);
            pb.start();
            */
        }
    }
}