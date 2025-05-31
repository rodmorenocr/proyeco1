import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*
 * Clase con los métodos necesarios para conectarnos con la base de datos SQlite
 * Contiene los métodos genéricos para consultas utilizadas en la gestión y uso de la bd
 */
public class Conector {
    //variables para conexión y ubilicación del fichero de la base de datos
    public static String cadenaBd = "ficheros/auraBoutique.db";
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
            conecto  = DriverManager.getConnection("jdbc:sqlite:"+Conector.cadenaBd);
            System.out.print("Conexión abierta ... ");
        } catch (ClassNotFoundException ex) {
            //
        } catch (SQLException ex) {
            //
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
            //
        }
    }
}
