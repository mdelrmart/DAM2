import java.sql.*;

public class A2UD3_EJ6 {
    public static void main(String[] args) {
        // a)
        visualizarTiposResultSet();

        // b)

    }

    public static void visualizarTiposResultSet() {
        // Datos de conexión con la BBDD
        String url = "jdbc:sqlserver://localhost:1433;databaseName=BDEmpresa;trustServerCertificate=true";
        String usuario = "sa";
        String contrasenha = "abc123.";

        try(Connection connection = DriverManager.getConnection(url, usuario, contrasenha))  {
            DatabaseMetaData metaData = connection.getMetaData();

            System.out.println("Tipos de ResultSet soportados:");
            if (metaData.supportsResultSetType(ResultSet.TYPE_FORWARD_ONLY)) {
                System.out.println("- TYPE_FORWARD_ONLY");
            }
            if (metaData.supportsResultSetType(ResultSet.TYPE_SCROLL_INSENSITIVE)) {
                System.out.println("- TYPE_SCROLL_INSENSITIVE");
            }
            if (metaData.supportsResultSetType(ResultSet.TYPE_SCROLL_SENSITIVE)) {
                System.out.println("- TYPE_SCROLL_SENSITIVE");
            }

            System.out.println("\nTipos de concurrencia soportados:");
            if (metaData.supportsResultSetConcurrency(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY)) {
                System.out.println("- TYPE_FORWARD_ONLY + CONCUR_READ_ONLY");
            }
            if (metaData.supportsResultSetConcurrency(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE)) {
                System.out.println("- TYPE_FORWARD_ONLY + CONCUR_UPDATABLE");
            }
            if (metaData.supportsResultSetConcurrency(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
                System.out.println("- TYPE_SCROLL_INSENSITIVE + CONCUR_READ_ONLY");
            }
            if (metaData.supportsResultSetConcurrency(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
                System.out.println("- TYPE_SCROLL_INSENSITIVE + CONCUR_UPDATABLE");
            }
            if (metaData.supportsResultSetConcurrency(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY)) {
                System.out.println("- TYPE_SCROLL_SENSITIVE + CONCUR_READ_ONLY");
            }
            if (metaData.supportsResultSetConcurrency(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE)) {
                System.out.println("- TYPE_SCROLL_SENSITIVE + CONCUR_UPDATABLE");
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener los tipos de ResultSet y concurrencia: " + e.getMessage());
        }
    }

    public static void insertarDatosProyecto(Proxecto proxecto) {
        // Datos de conexión con la BBDD
        String url = "jdbc:sqlserver://localhost:1433;databaseName=BDEmpresa;trustServerCertificate=true";
        String usuario = "sa";
        String contrasenha = "abc123.";

        String sql = "SELECT * FROM PROXECTO";

        try(Connection connection = DriverManager.getConnection(url, usuario, contrasenha);
            PreparedStatement sentencia = connection.prepareStatement(sql))  {

        } catch (SQLException e) {
            System.out.println("Error al obtener los tipos de ResultSet y concurrencia: " + e.getMessage());
        }
    }

}