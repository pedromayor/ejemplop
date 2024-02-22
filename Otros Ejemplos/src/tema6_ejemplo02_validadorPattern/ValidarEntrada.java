package tema6_ejemplo02_validadorPattern;

import java.io.*;
import java.util.regex.*;

class ValidarEntrada {

    public ValidarEntrada() {
        String dni_cliente = new String();
        Pattern pat = null;
        Matcher mat = null;

        // para leer del teclado
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        try {
            System.out.println("Introduce tu DNI (Formato 00000000-A):");
            dni_cliente = reader.readLine();

            pat = Pattern.compile("[0-9]{8}-[a-zA-Z]");
            mat = pat.matcher(dni_cliente);

            if (mat.find()) {
                System.out.println("Correcto!!  " + dni_cliente);
            } else {
                System.out.println("El DNI esta mal  " + dni_cliente);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] arg) {
        new ValidarEntrada();
    }

}
