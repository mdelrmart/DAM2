import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Obsoleto {

    // Sustituido por un procedimiento como pide ella en el ejercicio.
    public static int obtenerNumDepartamento(String departamento) {
        String sql = """
                    SELECT Num_departamento FROM DEPARTAMENTO WHERE Nome_departamento = ?
                """;

        try (Connection conexion = Conexion.obtenerConexion();
             PreparedStatement sentencia = conexion.prepareStatement(sql)) {

            sentencia.setString(1, departamento);

            ResultSet rs = sentencia.executeQuery();

            // Mover el cursor a la primera fila y comprobar si hay resultados
            if (rs.next()) {
                return rs.getInt("Num_departamento");
            } else {
                // Devolvemos -1 si no se encuentra el departamento
                System.out.println("No se ha encontrado el departamento " + departamento);
                return -1;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // Sustituido por una función como pide ella en el ejercicio.
    public static int comprobarMatricula(String matricula) {
        String sql = """
                    SELECT Matricula FROM VEHICULOS WHERE Matricula = ?
                """;

        try (Connection conexion = Conexion.obtenerConexion();
             PreparedStatement sentencia = conexion.prepareStatement(sql)) {

            sentencia.setString(1, matricula);

            ResultSet rs = sentencia.executeQuery();

            if (rs.next()) {
                return 1;
            } else {
                return 0;
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
