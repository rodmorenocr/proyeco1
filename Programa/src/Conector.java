import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;

//import java.sql.PreparedStatement;

/*
 * Clase con los métodos necesarios para conectarnos con la base de datos SQlite
 * Contiene los métodos genéricos para consultas utilizadas en la gestión y uso de la bd
 */
public class Conector {
    //variables para conexión y ubilicación del fichero de la base de datos
    //public static String cadenaBd = "Programa\\src\\ficheros\\auraBoutique.db";
    //objeto conexión
    static Connection conecto;   
   
    /**
     * Método para conectarnos con la base de datos SQLite.
     */
    public static Connection abrir(){
        conecto = null;
        try {
            //Carga el Driver de sqlite
            Class.forName("org.sqlite.JDBC");
            conecto  = DriverManager.getConnection("jdbc:sqlite:Programa\\src\\ficheros\\auraBoutique.db");
            //conecto  = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\dani\\\\datos.properties");
            System.out.print("Conexión abierta ... ");
        } catch (ClassNotFoundException ex1) {
            ex1.printStackTrace();
        } catch (SQLException ex2) {
            ex2.printStackTrace();
            //System.out.println("Excepción: SQLException" + ex2.getMessage());
        }
       
        return conecto;
    }
   
   
    /*
     * Método para cerrar la conexión con la base de datos
     */
    public static void cerrar(){
        try{
            conecto.close();
            System.out.println("Conexión cerrada ..... ");
        }catch(SQLException ex){
            System.out.println("Excepción: SQLException" + ex.getMessage());
        }
    }

    // Método para cargar el usuario
    public static boolean cargarUsuario(String nombreUsuario, String contrasenia) {
        boolean hecho = false;
        ResultSet rs = null;
        
        try{
            Conector.abrir();
            Statement statemento = conecto.createStatement();
            rs = statemento.executeQuery("SELECT * FROM Usuario WHERE userName = '" + nombreUsuario + "' AND password = '" + contrasenia + "';");
            int contador = 0;
            while (rs.next()) {
                contador++;
            }
            if (contador == 1) {
                hecho = true;
            } else {
                hecho = false;
            }
            
        } catch (SQLException ex1){
            System.out.println("Excepción: SQLException" + ex1.getMessage());
            hecho = false;
        } catch (NullPointerException ex2) {
            System.out.println("Excepción: NullPointerException" + ex2.getMessage());
            hecho = false;
        }

        return hecho;

    }


}
