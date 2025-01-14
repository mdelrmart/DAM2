import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class A5UD3_EJ4 {
    public static void main(String[] args) {
        Proyecto arrayProyectos[] = new Proyecto[2];
        arrayProyectos[0] = new Proyecto(61, "Proyecto 1", "CAMARIÑAS", 4);
        arrayProyectos[1] = new Proyecto(62, "Proyecto 2", "SANTIAGO", 4);

        agregarProyectos(arrayProyectos);
    }

    // Metodo para agregar proyectos utilizando procesamiento por lotes
    public static void agregarProyectos(Proyecto[] proyectos) {
        String sqlInsertarProyecto = """
                INSERT INTO PROXECTO (Num_proxecto, Nome_proxecto, Lugar, Num_departamento_controla)
                VALUES (?, ?, ?, ?)
                """;

        try (Connection conexion = Conexion.obtenerConexion();
             PreparedStatement sentencia = conexion.prepareStatement(sqlInsertarProyecto)) {

            for (Proyecto proyecto : proyectos) {
                sentencia.setInt(1, proyecto.Num_proxecto);
                sentencia.setString(2, proyecto.Nome_proxecto);
                sentencia.setString(3, proyecto.Lugar);
                sentencia.setInt(4, proyecto.Num_departamento_controla);
                sentencia.addBatch();
            }

            // Ejecutar el lote
            sentencia.executeBatch();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}