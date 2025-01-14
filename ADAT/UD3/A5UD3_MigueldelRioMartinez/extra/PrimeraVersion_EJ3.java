import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class A5UD3_EJ3 {
    public static void main(String[] args) {

    }

    public static void eliminarDepartamento(String departamentoEliminar, String departamentoReasignar) {
        ArrayList<Integer> proxectosDoDepartamentoEliminado = new ArrayList<>();

        // 1)
        String sql = "SELECT Num_proxecto, Num_departamento_controla " +
                "FROM departamento d " +
                "INNER JOIN proxecto p ON d.Num_departamento = p.Num_departamento_controla " +
                "WHERE d.Nome_departamento = ?";

        try (Connection conexion = Conexion.obtenerConexion();
             PreparedStatement sentencia = conexion.prepareStatement(sql)) {
            
            sentencia.setString(1, departamentoEliminar);
            
            ResultSet rs = sentencia.executeQuery(sql);

            while (rs.next()) {
                proxectosDoDepartamentoEliminado.add(rs.getInt("Num_proxecto"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        // 2)
        String sql2 = "UPDATE proxecto " +
                "SET Num_departamento_controla " +
                "WHERE d.Nome_departamento = ?";

        try (Connection conexion = Conexion.obtenerConexion();
             PreparedStatement sentencia = conexion.prepareStatement(sql)) {

            sentencia.setString(1, departamentoReasignar);
            
            ResultSet rs = sentencia.executeQuery(sql);
            
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        
        
        
        
    }
}