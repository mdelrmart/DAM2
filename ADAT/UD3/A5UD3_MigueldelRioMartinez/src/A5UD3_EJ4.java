import Clases.Proyecto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

public class A5UD3_EJ4 {
    public static void main(String[] args) {
        //Clases.Proyecto arrayProyectos[] = new Clases.Proyecto[2];
        //arrayProyectos[0] = new Clases.Proyecto(61, "Clases.Proyecto 1", "CAMARIÑAS", 4);
        //arrayProyectos[1] = new Clases.Proyecto(62, "Clases.Proyecto 2", "SANTIAGO", 4);
        ArrayList<Proyecto> arrayProyectos = new ArrayList<>();

        arrayProyectos.add(new Proyecto(61, "Clases.Proyecto 1", "CAMARIÑAS", 4));
        arrayProyectos.add(new Proyecto(62, "Clases.Proyecto 2", "SANTIAGO", 4));

        agregarProyectos(arrayProyectos);
    }

    // Metodo para agregar proyectos utilizando procesamiento por lotes
    public static void agregarProyectos(ArrayList<Proyecto> proyectos) {
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