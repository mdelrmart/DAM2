import java.sql.*;

public class A2UD3_EJ3 {
    public static void main(String[] args) {
        // a)
        cambiarDepartamentoProyecto("INFORMÁTICA","PROXECTO X");

        // b)
        Proxecto pr = new Proxecto(99,"Proyecto de prueba","Pontevedra",1);
        insertarNuevoProyecto(pr);

        // c)
        borrarProyecto(8);
    }

    public static void cambiarDepartamentoProyecto(String nomeDepartamento, String nomeProyecto) {
        // Datos de conexión con la BBDD
        String url = "jdbc:mysql://localhost:3306/BDEmpresa";
        String usuario = "root";
        String contrasenha = "abc123.";

        // Sentencia SQL
        String sql = "UPDATE proxecto " +
                "SET Num_departamento_controla = (SELECT Num_departamento " +
                "                                 FROM departamento " +
                "                                 WHERE Nome_departamento = ?) " +
                "WHERE Nome_proxecto = ?;";

        try (Connection conexion = DriverManager.getConnection(url, usuario, contrasenha);
             PreparedStatement sentencia = conexion.prepareStatement(sql)) {

            sentencia.setString(1, nomeDepartamento);
            sentencia.setString(2, nomeProyecto);

            int filasAfectadas = sentencia.executeUpdate();
            System.out.println("Filas actualizadas: " + filasAfectadas);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void insertarNuevoProyecto(Proxecto proxecto) {
        // Datos de conexión con la BBDD
        String url = "jdbc:mysql://localhost:3306/BDEmpresa";
        String usuario = "root";
        String contrasenha = "abc123.";

        // Obtenemos los datos del objecto con los getters de la clase proxecto
        int numProxecto = proxecto.getNum_proxecto();
        String nomeProxecto = proxecto.getNome_Proxecto();
        String lugarProxecto = proxecto.getLugar();
        int numDepartamentoControla = proxecto.getNum_departamento_controla();

        // Sentencia SQL
        String sql = "INSERT INTO proxecto VALUES (?,?,?,?)";

        try (Connection conexion = DriverManager.getConnection(url, usuario, contrasenha);
             PreparedStatement sentencia = conexion.prepareStatement(sql)) {

            sentencia.setInt(1, numProxecto);
            sentencia.setString(2, nomeProxecto);
            sentencia.setString(3, lugarProxecto);
            sentencia.setInt(4, numDepartamentoControla);

            sentencia.executeUpdate();
            System.out.println("Datos insertados correctamente.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void borrarProyecto(int numProyecto) {
        // Datos de conexión con la BBDD
        String url = "jdbc:mysql://localhost:3306/BDEmpresa";
        String usuario = "root";
        String contrasenha = "abc123.";

        // Sentencias SQL
        String sql1 = "DELETE FROM empregado_proxecto WHERE Num_proxecto = ?;";
        String sql2 = "DELETE FROM proxecto WHERE Num_proxecto = ?;";

        try (Connection conexion = DriverManager.getConnection(url, usuario, contrasenha);
             PreparedStatement sentencia1 = conexion.prepareStatement(sql1);
             PreparedStatement sentencia2 = conexion.prepareStatement(sql2)) {

            sentencia1.setInt(1, numProyecto);
            sentencia2.setInt(1, numProyecto);

            int filasAfectadas = sentencia1.executeUpdate();
            System.out.println("Filas eliminadas de la tabla proxecto: " + filasAfectadas);

            int filasAfectadas2 = sentencia2.executeUpdate();
            System.out.println("Filas eliminadas de la tabla empregado_proxecto: " + filasAfectadas2);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
