import java.io.*;
import java.net.*;

import org.json.*;

public class Operaciones {
    public static void insertarEvento(String nombre, int maxAsistentes) {
        URL url = null;
        HttpURLConnection con = null;
        String json = "";
        String strURL = "http://localhost/eventos/rest.php/insertarEvento";

        try {
            String parametros =
                    "nombre=" + URLEncoder.encode(nombre, "UTF-8") + "&maxAsistentes=" + maxAsistentes;

            url = new URL(strURL);
            con = (HttpURLConnection) url.openConnection();

            // le pasamos los parámetros en el cuerpo de la petición
            con.setRequestMethod("POST");
            con.setDoOutput(true);
            PrintWriter out = new PrintWriter(con.getOutputStream());
            out.print(parametros);
            out.close();

            con.connect();
            if (con.getResponseCode() == 201) {
                /* Si en la inserción devolvemos un JSON con la clave generada, aquí deberíamos
                recuperar el JSON y analizarlo para obtenerla por si la necesitamos */

                System.out.println("Se ha insertado el evento correctamente");
            } else {
                System.out.println("Problemas al insertar el evento. Respuesta: (" + con.getResponseCode() + ") " + con.getResponseMessage());
            }
        } catch (IOException ex) {
            System.out.println("Error en la conexión");
        }
    }

    public static void consultarDisponibilidad(String nombre, int maxAsistentes) {
        URL url = null;
        HttpURLConnection con = null;
        String json = "";
        String strURL = "http://localhost/eventos/rest.php/insertarEvento";

        try {
            String parametros =
                    "nombre=" + URLEncoder.encode(nombre, "UTF-8") + "&maxAsistentes=" + maxAsistentes;

            url = new URL(strURL);
            con = (HttpURLConnection) url.openConnection();

            // le pasamos los parámetros en el cuerpo de la petición
            con.setRequestMethod("POST");
            con.setDoOutput(true);
            PrintWriter out = new PrintWriter(con.getOutputStream());
            out.print(parametros);
            out.close();

            con.connect();
            if (con.getResponseCode() == 201) {
                /* Si en la inserción devolvemos un JSON con la clave generada, aquí deberíamos
                recuperar el JSON y analizarlo para obtenerla por si la necesitamos */
                System.out.println("Se ha insertado el evento correctamente");
            } else {
                System.out.println("Problemas al insertar el evento. Respuesta: (" + con.getResponseCode() + ") " + con.getResponseMessage());
            }
        } catch (IOException ex) {
            System.out.println("Error en la conexión");
        }
    }

    public static void eliminarEvento(String nombre) {
        URL url = null;
        HttpURLConnection con = null;
        String json = "";
        String strURL = "http://localhost/eventos/rest.php/eliminarEvento";

        try {
            String parametros =
                    "nombre=" + URLEncoder.encode(nombre, "UTF-8");

            url = new URL(strURL);
            con = (HttpURLConnection) url.openConnection();

            // le pasamos los parámetros en el cuerpo de la petición
            con.setRequestMethod("POST");
            con.setDoOutput(true);
            PrintWriter out = new PrintWriter(con.getOutputStream());
            out.print(parametros);
            out.close();

            con.connect();
            if (con.getResponseCode() == 200) {
                /* Si en la inserción devolvemos un JSON con la clave generada, aquí deberíamos
                recuperar el JSON y analizarlo para obtenerla por si la necesitamos */

                System.out.println("Se ha eliminado el evento correctamente");
            } else {
                System.out.println("Problemas al eliminar el evento. Respuesta: (" + con.getResponseCode() + ") " + con.getResponseMessage());
            }
        } catch (IOException ex) {
            System.out.println("Error en la conexión");
        }
    }

    public static void visualizarDatos() {
        URL url = null;
        HttpURLConnection con = null;
        String json = "";
        String strURL = "http://localhost/eventos/rest.php/eventos";

        try {
            url = new URL(strURL);
            con = (HttpURLConnection) url.openConnection();
            con.connect();
            if (con.getResponseCode() == 200) {
                BufferedReader bufferIn = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String linea;

                while ((linea = bufferIn.readLine()) != null) {
                    json += linea;
                }

                bufferIn.close();

                /* Analizamos el JSON devuelto, que sabemos que es un array de objetos cliente */
                JSONArray datos = new JSONArray(json);
                for (int i = 0; i < datos.length(); i++) {
                    JSONObject cliente = datos.getJSONObject(i);

                    String nombre = cliente.getString("nombre");
                    int maxAsistentes = cliente.getInt("maxAsistentes");

                    System.out.printf("Evento: %s - Max. Asistentes: %d\n", nombre, maxAsistentes);

                    // Visualizar asistentes del evento
                    visualizarAsistentes(nombre);
                }
            } else {
                System.out.println("Problemas. Respuesta: (" + con.getResponseCode() + ") " + con.getResponseMessage());
            }
        } catch (IOException ex) {
            System.out.println("Error en la conexión");
        }
    }

    public static void visualizarAsistentes(String nombreEvento) throws UnsupportedEncodingException {
        URL url = null;
        HttpURLConnection con = null;
        String json = "";
        String strURL = "http://localhost/eventos/rest.php/asistentesevento?evento=" + URLEncoder.encode(nombreEvento, "UTF-8");

        try {
            url = new URL(strURL);
            con = (HttpURLConnection) url.openConnection();
            con.connect();
            if (con.getResponseCode() == 200) {
                BufferedReader bufferIn = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String linea;

                while ((linea = bufferIn.readLine()) != null) {
                    json += linea;
                }

                bufferIn.close();

                /* Analizamos el JSON devuelto, que sabemos que es un array de objetos asistente */
                JSONArray datos = new JSONArray(json);

                System.out.println("Asistentes:");
                for (int i = 0; i < datos.length(); i++) {
                    JSONObject asistente = datos.getJSONObject(i);

                    String nombre = asistente.getString("nombre");

                    System.out.printf("- %s\n", nombre);
                }
                System.out.println("\n");
            } else if (con.getResponseCode() == 404) {
                System.out.println("No hay asistentes para este evento");
                System.out.println("\n");
            } else {
                System.out.println("Problemas. Respuesta: (" + con.getResponseCode() + ") " + con.getResponseMessage());
                System.out.println("\n");
            }
        } catch (IOException ex) {
            System.out.println("Error en la conexión");
        }
    }

}
