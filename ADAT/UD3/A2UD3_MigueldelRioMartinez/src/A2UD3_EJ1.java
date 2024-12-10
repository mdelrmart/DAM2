import java.sql.*;

public class A2UD3_EJ1 {
    public static void main(String[] args) {
        // a)
        subirSalario(200, "PERSOAL");

        // b)
        nuevoDepartamento(7,"FINANZAS","9998888");

        // c)
        borrarEmpleadoProyecto("9990009",7);
    }

    public static void subirSalario(int cantAumento, String nomeDepartamento) {
        // Datos de conexión con la BBDD
        String url = "jdbc:mysql://localhost:3306/BDEmpresa";
        String usuario = "root";
        String contrasenha = "abc123.";

        // Sentencia SQL
        String sql = "UPDATE EMPREGADO e " +
                "INNER JOIN DEPARTAMENTO d ON e.Num_departamento_pertenece = d.Num_departamento " +
                "SET e.SALARIO = e.SALARIO + '" + cantAumento + "' " +
                "WHERE d.Nome_departamento = '" + nomeDepartamento + "';";

        try (Connection conexion = DriverManager.getConnection(url, usuario, contrasenha);
             Statement sentencia = conexion.createStatement()) {

            sentencia.executeUpdate(sql);
            System.out.println("La operación se ha ejecutado correctamente.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void nuevoDepartamento(int numDepartamento, String nomeDepartamento, String nssEmpregadoDirector) {
        // Datos de conexión con la BBDD
        String url = "jdbc:mysql://localhost:3306/BDEmpresa";
        String usuario = "root";
        String contrasenha = "abc123.";

        // Sentencia SQL
        String sql = "INSERT INTO DEPARTAMENTO " +
                "VALUES ('" + numDepartamento +"', '" + nomeDepartamento + "', '" + nssEmpregadoDirector + "', CURRENT_DATE);";

        try (Connection conexion = DriverManager.getConnection(url, usuario, contrasenha);
             Statement sentencia = conexion.createStatement()) {

            sentencia.executeUpdate(sql);
            System.out.println("La operación se ha ejecutado correctamente.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void borrarEmpleadoProyecto(String nssEmpleado, int numProyecto) {
        // Datos de conexión con la BBDD
        String url = "jdbc:mysql://localhost:3306/BDEmpresa";
        String usuario = "root";
        String contrasenha = "abc123.";

        // Sentencia SQL
        String sql = "DELETE FROM EMPREGADO_PROXECTO WHERE NSS_empregado = '" + nssEmpleado + "' AND Num_proxecto = " + numProyecto;

        try (Connection conexion = DriverManager.getConnection(url, usuario, contrasenha);
             Statement sentencia = conexion.createStatement()) {

            sentencia.executeUpdate(sql);
            System.out.println("La operación se ha ejecutado correctamente.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}