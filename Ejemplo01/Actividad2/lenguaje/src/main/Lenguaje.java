package main;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

/**
 *
 * @author Pedro
 */
public class Lenguaje {
    public static void main(String[] args) throws IOException {
        //Compruebo que se han especificado los argumentos necesarios, si no, cierro el programa.
        if(args.length != 2){
            System.out.println("Error. Especifíca 2 argumentos: Nº palabras y Nombre fichero");
            System.exit(1);
        }

        //Obtengo el número de palabras a generar y el nombre del fichero:
        int numeroPalabras = Integer.parseInt(args[0]);
        String nombreFichero = args[1];
        
        //Creo un objeto Random para hacer números aleatorios
        Random aleatorio = new Random();
        
        //Genero el numero de letras por palabra (entre 1 y 20) y la letra, de forma aletatoria.
        for (int i = 0; i < numeroPalabras; i++) {
            int numeroLetras = aleatorio.nextInt(20)+ 1;
            String palabra = "";
            for (int j = 0; j < numeroLetras; j++) {
                char letra = (char) (aleatorio.nextInt(26) + 'a');
                palabra = palabra + letra;
            }
            
            //Escribo la palabra en el fichero
            FileWriter fw = new FileWriter(nombreFichero, true);
            fw.write(palabra + "\n");
            fw.close();
        }
    }
}