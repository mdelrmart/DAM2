import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    // Datos de conexión, podrías moverlos a un archivo de configuración para mayor flexibilidad.
    private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=BDEmpresa;trustServerCertificate=true";
    private static final String USUARIO = "sa";
    private static final String CONTRASENHA = "abc123.";

    // Metodo para obtener la conexión a la base de datos
    public static Connection obtenerConexion() throws SQLException {
        return DriverManager.getConnection(URL, USUARIO, CONTRASENHA);
    }
}
