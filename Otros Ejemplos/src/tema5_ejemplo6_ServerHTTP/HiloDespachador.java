/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tema5_ejemplo6_ServerHTTP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 * @author Pedro
 */
class HiloDespachador extends Thread {

    private Socket socCliente;

    public HiloDespachador(Socket socCliente) {
        this.socCliente = socCliente;
    }

    public void run() {
        try {
            //atiendo un cliente
            System.out.println("Atendiendo al cliente ");
            procesaPeticion(socCliente);
            //cierra la conexión entrante
            socCliente.close();
            System.out.println("cliente atendido");
        } catch (IOException ex) {
        }
    }

    /**
     *****************************************************************************
     * procesa la petición recibida
     *
     * @throws IOException
     */
    private static void procesaPeticion(Socket socketCliente) throws IOException {
        //variables locales
        String peticion;
        String html;

        //Flujo de entrada
        InputStreamReader inSR = new InputStreamReader(socketCliente.getInputStream());
        //espacio en memoria para la entrada de peticiones
        BufferedReader bufLeer = new BufferedReader(inSR);

        //objeto de java.io que entre otras características, permite escribir 'línea a línea' en un flujo de salida
        PrintWriter printWriter = new PrintWriter(socketCliente.getOutputStream(), true);

        //mensaje petición cliente
        peticion = bufLeer.readLine();

        //para compactar la petición y facilitar así su análisis, suprimimos todos los espacios en blanco que contenga
        peticion = peticion.replaceAll(" ", "");

        //si realmente se trata de una petición 'GET' (que es la única que vamos a implementar en nuestro Servidor)
        if (peticion.startsWith("GET")) {
            //extrae la subcadena entre 'GET' y 'HTTP/1.1'
            peticion = peticion.substring(3, peticion.lastIndexOf("HTTP"));

            //si corresponde a la página de inicio
            if (peticion.length() == 0 || peticion.equals("/")) {
                //sirve la página
                html = Paginas.html_index;
                printWriter.println(Mensajes.lineaInicial_OK);
                printWriter.println(Paginas.primeraCabecera);
                printWriter.println("Content-Length: " + html.length() + 1);
                printWriter.println("\n");
                printWriter.println(html);
            } //si corresponde a la página del Quijote
            else if (peticion.equals("/quijote")) {
                //sirve la página
                html = Paginas.html_quijote;
                printWriter.println(Mensajes.lineaInicial_OK);
                printWriter.println(Paginas.primeraCabecera);
                printWriter.println("Content-Length: " + html.length() + 1);
                printWriter.println("\n");
                printWriter.println(html);
            } //en cualquier otro caso
            else {
                //sirve la página
                html = Paginas.html_noEncontrado;
                printWriter.println(Mensajes.lineaInicial_NotFound);
                printWriter.println(Paginas.primeraCabecera);
                printWriter.println("Content-Length: " + html.length() + 1);
                printWriter.println("\n");
                printWriter.println(html);
            }

        }
    }

}
