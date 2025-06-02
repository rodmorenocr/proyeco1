import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

public class HttpConectorSimple {

    // URL de destino php
    private static final String PHP_SCRIPT_URL = "https://auraboutique.info/wp-content/themes/divi-child/conector.php";

    /**
     * Realiza una petición POST al script PHP.
     *
     * @param action La acción a realizar (ej. "cargarUsuario").
     * @param params Los parámetros para la petición.
     * @return La respuesta del servidor.
     * @throws Exception Si ocurre un error de red o del servidor.
     */
    private static String realizarPeticion(String action, Map<String, String> params) throws Exception {
        URL url = new URL(PHP_SCRIPT_URL); // La acción se enviará como parámetro POST
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");
        con.setDoOutput(true);
        con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        con.setUseCaches(false);
        con.setConnectTimeout(15000); // 15 segundos
        con.setReadTimeout(15000);    // 15 segundos

        // Añadir la acción a los parámetros
        params.put("action", action);

        StringJoiner sj = new StringJoiner("&");
        for (Map.Entry<String, String> entry : params.entrySet()) {
            sj.add(URLEncoder.encode(entry.getKey(), StandardCharsets.UTF_8.name()) + "="
                 + URLEncoder.encode(entry.getValue(), StandardCharsets.UTF_8.name()));
        }
        byte[] out = sj.toString().getBytes(StandardCharsets.UTF_8);
        con.setRequestProperty("Content-Length", String.valueOf(out.length));

        // Enviar datos
        try (OutputStream os = con.getOutputStream()) {
            os.write(out);
        }

        StringBuilder response = new StringBuilder();
        int responseCode = con.getResponseCode();
        System.out.println("DEBUG: HTTP Response Code: " + responseCode);

        // Determinar qué stream leer (input o error)
        InputStream inputStream = (responseCode >= HttpURLConnection.HTTP_OK && responseCode < HttpURLConnection.HTTP_MULT_CHOICE)
                                ? con.getInputStream()
                                : con.getErrorStream();

        // Leer la respuesta
        if (inputStream != null) {
            try (BufferedReader br = new BufferedReader(
                    new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
                String responseLine;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }
            }
        } else {
            response.append("FAILURE|No response body received.");
            System.err.println("ERROR: No input stream available for response code " + responseCode);
        }

        con.disconnect();

        // Verificar el código de respuesta después de leer
        if (responseCode == HttpURLConnection.HTTP_OK) {
            System.out.println("DEBUG: Server Response: " + response.toString());
            return response.toString();
        } else {
            System.err.println("ERROR: Server responded with HTTP code: " + responseCode);
            System.err.println("ERROR: Server response body: " + response.toString());
            throw new Exception("Error del servidor: " + responseCode + " - Respuesta: " + response.toString());
        }
    }

    /**
     * Carga un usuario desde el servidor.
     * PHP responde con: "SUCCESS|dni|nombre|apellido|puesto|mail|telefono|direccion|cp|ciudad|provincia|pais" o "FAILURE|mensaje de error"
     */
    public static boolean cargarUsuario(String nombreUsuario, String contrasenia) {
        try {
            Map<String, String> params = new HashMap<>();
            params.put("nombreUsuario", nombreUsuario);
            params.put("contrasenia", contrasenia);

            String serverResponse = realizarPeticion("cargarUsuario", params);
            System.out.println("Respuesta del servidor (cargarUsuario): " + serverResponse);

            String[] parts = serverResponse.split("\\|", -1); // -1 para incluir campos vacíos

            if (parts.length > 0 && parts[0].equals("SUCCESS")) {
                if (parts.length == 12) {
                    Menu.dnib = parts[1];
                    Menu.nombreb = parts[2];
                    Menu.apellidob = parts[3];
                    Menu.puestob = parts[4];
                    Menu.emailb = parts[5];
                    try { Menu.telefonob = Integer.parseInt(parts[6]); }
                    catch (NumberFormatException e) { Menu.telefonob = 0; System.err.println("Error parseando telefono: " + parts[6]); }
                    Menu.direccionb = parts[7];
                    try { Menu.codigoPostalb = Integer.parseInt(parts[8]); }
                    catch (NumberFormatException e) { Menu.codigoPostalb = 0; System.err.println("Error parseando CP: " + parts[8]); }
                    Menu.ciudadb = parts[9];
                    Menu.provinciab = parts[10];
                    Menu.paisb = parts[11];
                    return true;
                } else {
                    System.err.println("Respuesta SUCCESS pero con formato incorrecto (" + parts.length + " campos): " + serverResponse);
                    return false;
                }
            } else {
                String errorMessage = (parts.length > 1) ? parts[1] : "Error desconocido desde el servidor.";
                System.err.println("Error al cargar usuario: " + errorMessage);
                return false;
            }
        } catch (Exception e) {
            System.err.println("Excepción en HttpConectorSimple.cargarUsuario: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Modifica los datos del usuario en el servidor.
     */
    public static boolean modificaUsuario(String nombre, String apellido, String mail, String puesto,
                                          String telefono, String direccion, String codigoPostal,
                                          String ciudad, String provincia, String pais, String dni) {
        try {
            Map<String, String> params = new HashMap<>();
            params.put("nombre", nombre);
            params.put("apellido", apellido);
            params.put("mail", mail);
            params.put("puesto", puesto);
            params.put("telefono", telefono);
            params.put("direccion", direccion);
            params.put("codigoPostal", codigoPostal);
            params.put("ciudad", ciudad);
            params.put("provincia", provincia);
            params.put("pais", pais);
            params.put("dni", dni); // Se envía el DNI correctamente.

            String serverResponse = realizarPeticion("modificaUsuario", params);
            System.out.println("Respuesta del servidor (modificaUsuario): " + serverResponse);

            String[] parts = serverResponse.split("\\|");

            if (parts.length > 0 && parts[0].equals("SUCCESS")) {
                String successMessage = (parts.length > 1) ? parts[1] : "Operación exitosa.";
                System.out.println(successMessage);
                return true;
            } else {
                String errorMessage = (parts.length > 1) ? parts[1] : "Error desconocido desde el servidor.";
                System.err.println("Error al modificar usuario: " + errorMessage);
                return false;
            }
        } catch (Exception e) {
            System.err.println("Excepción en HttpConectorSimple.modificaUsuario: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Consulta la ruta de una nómina en el servidor.
     * PHP responde con: "SUCCESS|ruta_nomina" o "FAILURE|mensaje de error"
     */
    public static String consultaNominaMes(String dni, int mes, int anio) {
        try {
            Map<String, String> params = new HashMap<>();
            params.put("dni", dni);
            params.put("mes", String.valueOf(mes));
            params.put("anio", String.valueOf(anio));

            String serverResponse = realizarPeticion("consultaNominaMes", params);
            System.out.println("Respuesta del servidor (consultaNominaMes): " + serverResponse);

            String[] parts = serverResponse.split("\\|");

            if (parts.length == 2 && parts[0].equals("SUCCESS")) {
                return parts[1]; // Devuelve la ruta
            } else {
                String errorMessage = (parts.length > 1) ? parts[1] : "Error o nómina no encontrada.";
                System.err.println("Error al consultar nómina: " + errorMessage);
                return ""; // Devuelve vacío si falla
            }
        } catch (Exception e) {
            System.err.println("Excepción en HttpConectorSimple.consultaNominaMes: " + e.getMessage());
            e.printStackTrace();
            return "";
        }
    }

    /**
     * Inserta un registro de horas en el servidor.
     * PHP responde con: "SUCCESS|mensaje" o "FAILURE|mensaje de error"
     */
    public static boolean insertarHoras(String dni, String fecha, String horaInicio, String horaFin, String horas) {
        try {
            Map<String, String> params = new HashMap<>();
            params.put("dni", dni);
            params.put("fecha", fecha);
            params.put("horaInicio", horaInicio);
            params.put("horaFin", horaFin);
            params.put("horas", horas);

            String serverResponse = realizarPeticion("insertarHoras", params);
            System.out.println("Respuesta del servidor (insertarHoras): " + serverResponse);

            String[] parts = serverResponse.split("\\|");

            if (parts.length > 0 && parts[0].equals("SUCCESS")) {
                System.out.println("Registro de horas enviado con éxito.");
                return true;
            } else {
                String errorMessage = (parts.length > 1) ? parts[1] : "Error desconocido.";
                System.err.println("Error al insertar horas: " + errorMessage);
                return false;
            }
        } catch (Exception e) {
            System.err.println("Excepción en HttpConectorSimple.insertarHoras: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}