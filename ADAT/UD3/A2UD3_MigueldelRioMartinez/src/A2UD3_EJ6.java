import java.sql.*;

public class A2UD3_EJ6 {
    public static void main(String[] args) {
        // a)
        visualizarTiposResultSet();

        // b)
        Proxecto proxecto = new Proxecto(12, "PRUEB2A", "PONTEVEDRA", 3);
        insertarDatosProyecto(proxecto);

        // c)
        incrementarSalarioDepartamento(100,200);

        // d)
        obtenerInfoEmpleadosProyectosAsignadosMayorQue(1);

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
            Statement sentencia = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE))  {

            ResultSet rs = sentencia.executeQuery(sql);

            rs.beforeFirst();

            rs.moveToInsertRow();
            rs.updateInt(1, proxecto.getNum_proxecto());
            rs.updateString(2, proxecto.getNome_Proxecto());
            rs.updateString(3, proxecto.getLugar());
            rs.updateInt(4, proxecto.getNum_departamento_controla());

            rs.insertRow();
            rs.moveToCurrentRow();

            System.out.println("Se insertó el proyecto " + proxecto.getNome_Proxecto());
        } catch (SQLException e) {
            System.out.println("Error al insertar el proyecto: " + e.getMessage());
        }
    }

    public static void incrementarSalarioDepartamento(int numDepartamento, float cantidad) {
        // Datos de conexión con la BBDD
        String url = "jdbc:sqlserver://localhost:1433;databaseName=BDEmpresa;trustServerCertificate=true";
        String usuario = "sa";
        String contrasenha = "abc123.";

        String sql = "SELECT * FROM EMPREGADO WHERE Num_departamento_pertenece = ?";

        try(Connection connection = DriverManager.getConnection(url, usuario, contrasenha);
            PreparedStatement sentencia = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE))  {

            sentencia.setInt(1, numDepartamento);
            ResultSet rs = sentencia.executeQuery();

            while (rs.next()) {
                rs.updateFloat("Salario", rs.getFloat("Salario") + cantidad);
                rs.updateRow();
            }

            System.out.println("Se actualizaron los salarios del departamento con número " + numDepartamento);
        } catch (SQLException e) {
            System.out.println("Error al insertar el proyecto: " + e.getMessage());
        }
    }

    public static void obtenerInfoEmpleadosProyectosAsignadosMayorQue(int numProyectos) {
        // Datos de conexión con la BBDD
        String url = "jdbc:sqlserver://localhost:1433;databaseName=BDEmpresa;trustServerCertificate=true";
        String usuario = "sa";
        String contrasenha = "abc123.";

        String sql = "SELECT NSS, Nome + ' ' + Apelido_1 + ' ' + Apelido_2 AS NomeCompleto, Localidade, Salario " +
                     "FROM EMPREGADO " +
                     "WHERE NSS IN (SELECT NSS_empregado FROM EMPREGADO_PROXECTO GROUP BY NSS_empregado HAVING COUNT(*) > ? )";

        try (Connection connection = DriverManager.getConnection(url, usuario, contrasenha);
            PreparedStatement sentencia = connection.prepareStatement(sql, ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE)) {

            sentencia.setInt(1, numProyectos);

            ResultSet rs = sentencia.executeQuery();
            rs.first();
                System.out.println("Primeiro: " + rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3) + " " + rs.getFloat(4));
            rs.last();
                System.out.println("Último: " + rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3) + " " + rs.getFloat(4));
            rs.relative(-2);
                System.out.println("Antepenúltimo: " + rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3) + " " + rs.getFloat(4));
            rs.afterLast();

            System.out.println("Datos do ResultSet do último ao primeiro\n ");
            while (rs.previous()) {
                System.out.println(rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3) + " " + rs.getFloat(4));
            }

            rs.close();
        } catch (SQLException e) {
            System.out.println("Error al insertar el proyecto: " + e.getMessage());
        }

    }

}