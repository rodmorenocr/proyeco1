import java.lang.Thread.State;
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

    //objeto conexión
    static Connection conecto;   
   
    // Método para conectarnos con la base de datos SQLite.
    public static Connection abrir(){
        conecto = null;
        try {
            //Carga el Driver de sqlite
            Class.forName("org.sqlite.JDBC");
            conecto  = DriverManager.getConnection("jdbc:sqlite:Programa\\src\\ficheros\\auraBoutique.db");
            System.out.print("Conexión abierta ... ");
        } catch (ClassNotFoundException ex1) {
            ex1.printStackTrace();
        } catch (SQLException ex2) {
            ex2.printStackTrace();
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


    /* 
     *  Método para comprobar que el usuario es correcto y que recoge los datos personales en las variables 
     *  declaradas en la clase Menú 
     */
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
                Menu.dnib = rs.getString("dni");
                Menu.nombreb = rs.getString("nombre");
                Menu.apellidob = rs.getString("apellido");
                Menu.puestob = rs.getString("puesto");
                Menu.emailb = rs.getString("mail");
                Menu.telefonob = rs.getInt("telefono");
                Menu.direccionb = rs.getString("direccion");
                Menu.codigoPostalb = rs.getInt("codigoPostal");
                Menu.ciudadb = rs.getString("ciudad");
                Menu.provinciab = rs.getString("provincia");
                Menu.paisb = rs.getString("pais");
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

    /*
     * Método para guardar los cambios realizados en los campos de datos personales en la base de datos
     */
    public static boolean modificaUsuario(String nombre, String apellido, String mail, String puesto, String telefono, String direccion, String codigoPostal, String ciudad, String provincia, String pais){
        boolean hecho = false;
        try {
            Conector.abrir();
            Statement statemento = conecto.createStatement();
            int telefonoInt = Integer.parseInt(telefono);
            int codigoPostalInt = Integer.parseInt(codigoPostal);
            statemento.executeUpdate("UPDATE Usuario SET nombre = '" + nombre + "', apellido = '" + apellido + "', mail = '" + mail + "', puesto = '" + puesto + "', telefono = '" + telefonoInt + "', direccion = '" + direccion + "', codigoPostal = '" + codigoPostalInt + "', ciudad = '" + ciudad + "', provincia = '" + provincia + "', pais = '" + pais + "' WHERE dni = '" + Menu.dnib + "';");
            hecho = true;
        } catch (SQLException ex) {
            ex.printStackTrace();
            hecho = false;
        }
        Conector.cerrar();
        return hecho;
    }


    /*
     * Método que devuelve la ruta a la nómina buscada
     */
    public static String consultaNominaMes(String dni, int mes, int anio) {
        String rutaNomina = "";
        try {
            
            Conector.abrir();
            Statement statemento = conecto.createStatement();
            ResultSet rs = statemento.executeQuery("SELECT * FROM Nomina WHERE dni = '" + dni + "' AND mes = '" + mes + "' AND anio = '" + anio + "';");
            while(rs.next()){
                rutaNomina = rs.getString("archivo");
            }
        } catch (SQLException ex) {
            System.out.println("Excepción: SQLException" + ex.getMessage());
        }
        

        return rutaNomina;
    }

}
