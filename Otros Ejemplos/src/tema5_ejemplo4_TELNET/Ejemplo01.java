package tema5_ejemplo4_TELNET;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.net.telnet.TelnetClient;

public class Ejemplo01 {

    private TelnetClient telnet = new TelnetClient();
    private BufferedReader reader;
    private InputStream in;
    private PrintStream out;
    private String serverName;
    private String user;
    private String password;
    public static String promptComplete;

    public Ejemplo01() {
    }

    private void connect() {
        try {

            serverName = "miServer";
            user = "miUser";
            password = "miPasswd";

            //Esta cadena es el prompt, en este caso nuestro servidor
            //muestra el prompt de la siguiente forma
            //[servidor][miuser =>
            promptComplete = "[" + serverName + "][" + user + " =>";

            //Abro la conexión al telnet por el puerto 23
            telnet.connect(serverName, 23);

            //Ahora necesito una forma de leer las respuestas que
            //me envía el telnet, para esto obtenemos un InputStream
            //del objeto telnet
            in = telnet.getInputStream();

            //Ahora necesito una forma de enviarle los comandos al telnet
            //para esto obtengo un OutputStream desde el objeto telnet
            out = new PrintStream(telnet.getOutputStream());

            //Ahora envuelvo el InputStream dentro de un BufferedReader
            //para que la lectura de las respuestas del telnet sean mucho
            //mas sencillas y mejor gestionadas
            reader = new BufferedReader(new InputStreamReader(in));

            //Ahora leemos de la consola a través de nuestro método
            //readUntil el cual lee de la consola hasta que el último
            //caracter (un char) sea -1 y que el prompt sea igual
            //al patron que le enviamos como argumento, en este caso
            //es hasta que el prompt despliegue el patron login:
            readUntil("login: ");

            //cuando el readUntil de login finaliza, procedemos a ingresar el user
            //a través del método write, el cual escribe en la consola
            write(user);

            //esperamos hasta que el prompt muestre la palabra password:
            //La palabra tiene que ser exacta a la que sale en el prompt
            readUntil("Password: ");

            //Ahora ingresamos el password
            write(password);

            //Esperamos hasta que salga el prompt de nuestro servidor
            readUntil(promptComplete);

            //Listo ya estamos logueados, ahora corramos un par de comandos
            write("ls -ltr");

            //leemos hasta que salga el prompt
            readUntil(promptComplete);

            //Para ver el directorio donde estamos
            write("pwd");

            //Esperamos a que salga el prompt
            readUntil(promptComplete);

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            try {
                //Liberamos recursos
                out.close();
                reader.close();
                in.close();
                telnet.disconnect();

            } catch (IOException ex) {
                Logger.getLogger(Ejemplo01.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

    public String readUntil(String pattern) {
        StringBuffer sb = new StringBuffer();

        try {
            char lastChar = pattern.charAt(pattern.length() - 1);

            boolean found = false;

            int check = in.read();
            char ch = (char) check;
            while (check != -1) {
                System.out.print(ch);
                sb.append(ch);
                if (ch == lastChar) {
                    if (sb.toString().endsWith(pattern)) {

                        return sb.toString();
                    }
                }
                check = in.read();
                ch = (char) check;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return sb.toString();
    }

    public void write(String value) {
        try {
            out.println(value);
            out.flush();
            System.out.println(value);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Ejemplo01 telnetClient = new Ejemplo01();
        telnetClient.connect();

    }

}
