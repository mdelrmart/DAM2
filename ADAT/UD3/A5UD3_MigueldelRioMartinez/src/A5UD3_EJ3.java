import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class A5UD3_EJ3 {
    public static void main(String[] args) {
        eliminarDepartamento("PERSOAL","INNOVACIÓN");
    }

    public static void eliminarDepartamento(String departamentoEliminar, String departamentoReasignar) {
        // Comprobamos existencia de los departamento antes de continuar
        if (existeDepartamento(departamentoEliminar) == 0) {
            System.out.println("No existe el departamento a eliminar indicado");
            return;
        }

        if (existeDepartamento(departamentoReasignar) == 0) {
            System.out.println("No existe el departamento a reasignar indicado");
            return;
        }

        int numDepEliminar = obtenerNumDepartamento(departamentoEliminar);
        int numDepReasignar = obtenerNumDepartamento(departamentoReasignar);

        // Cambiamos el departamento que controla los proyectos
        String sqlUpdateProxecto = """
                    UPDATE PROXECTO
                    SET Num_departamento_controla = ?
                    WHERE Num_departamento_controla = ?
        """;

        try (Connection conexion = Conexion.obtenerConexion();
             PreparedStatement sentencia = conexion.prepareStatement(sqlUpdateProxecto)) {

            sentencia.setInt(1, numDepReasignar);
            sentencia.setInt(2, numDepEliminar);

            sentencia.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        // Cambiamos los empleados de departamento
        String sqlUpdateEmpregado = """
                    UPDATE EMPREGADO
                    SET Num_departamento_pertenece = ?
                    WHERE Num_departamento_pertenece = ?
        """;

        try (Connection conexion = Conexion.obtenerConexion();
             PreparedStatement sentencia = conexion.prepareStatement(sqlUpdateEmpregado)) {

            sentencia.setInt(1, numDepReasignar);
            sentencia.setInt(2, numDepEliminar);

            sentencia.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        // Eliminamos el departamento
        String sqlEliminarDepartamento = """
                    DELETE FROM DEPARTAMENTO
                    WHERE Num_departamento = ?
        """;

        try (Connection conexion = Conexion.obtenerConexion();
             PreparedStatement sentencia = conexion.prepareStatement(sqlEliminarDepartamento)) {

            sentencia.setInt(1, numDepEliminar);

            sentencia.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static int existeDepartamento(String departamento) {
        String sql = """
                    SELECT Nome_departamento FROM DEPARTAMENTO WHERE Nome_departamento = ?
                """;

        try (Connection conexion = Conexion.obtenerConexion();
             PreparedStatement sentencia = conexion.prepareStatement(sql)) {

            sentencia.setString(1, departamento);

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
}
