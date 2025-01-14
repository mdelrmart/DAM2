import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class A5UD3_EJ5 {
    public static void main(String[] args) {
        eliminarProyecto(65);
    }

    public static void eliminarProyecto(int numProyecto) {
        // SQL para eliminar los registros relacionados en la tabla Empleado_Proyecto
        String sqlEliminarEmpleadoProyecto = """
                DELETE FROM EMPREGADO_PROXECTO
                WHERE Num_proxecto = ?
        """;

        // SQL para eliminar el proyecto de la tabla PROXECTO
        String sqlEliminarProyecto = """
                DELETE FROM PROXECTO
                WHERE Num_proxecto = ?
        """;

        try (Connection conexion = Conexion.obtenerConexion()) {
            // Iniciar la transacción
            conexion.setAutoCommit(false);

            try (PreparedStatement sentencia1 = conexion.prepareStatement(sqlEliminarEmpleadoProyecto);
                 PreparedStatement sentencia2 = conexion.prepareStatement(sqlEliminarProyecto)) {

                // Eliminar registros en Empleado_Proyecto
                sentencia1.setInt(1, numProyecto);
                sentencia1.executeUpdate();

                // Eliminar el proyecto en PROXECTO
                sentencia2.setInt(1, numProyecto);
                sentencia2.executeUpdate();

                // Confirmar la transacción
                conexion.commit();
                System.out.println("Proyecto y registros relacionados eliminados correctamente.");
            } catch (SQLException e) {
                // En caso de error, revertir la transacción
                conexion.rollback();
                System.err.println("Error al eliminar el proyecto. Se ha revertido la transacción.");
                e.printStackTrace();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al conectar con la base de datos.", e);
        }
    }
}
