import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class A2UD3_EJ4 {
    public static void main(String[] args) {
        //proyectosControladosPorDepartamento("INFORMÁTICA");

        String nomeDepartamento = "INFORMÁTICA";

        System.out.println("Proyectos controlados por el departamento: " + nomeDepartamento);
        System.out.println("-------------------------------------------------------");

        for (Proxecto proxecto : proyectosControladosPorDepartamento(nomeDepartamento)) {
            System.out.println(proxecto.getNome_Proxecto());
        }

    }

    public static List<Proxecto> proyectosControladosPorDepartamento (String nomeDepartamento) {
        // Datos de conexión con la BBDD
        String url = "jdbc:mysql://localhost:3306/BDEmpresa";
        String usuario = "root";
        String contrasenha = "abc123.";

        // Sentencia SQL
        String sql = "SELECT * " +
                    "FROM proxecto p " +
                    "INNER JOIN departamento d ON p.Num_departamento_controla = d.Num_departamento " +
                    "WHERE d.Nome_departamento = ?";

        List<Proxecto> proyectos = new ArrayList<>();

        try (Connection conexion = DriverManager.getConnection(url, usuario, contrasenha);
             PreparedStatement sentencia = conexion.prepareStatement(sql)) {

            sentencia.setString(1, nomeDepartamento);

            ResultSet rs = sentencia.executeQuery();

            while (rs.next()) {
                rs.getString("Num_departamento_controla");

                int numProyecto = rs.getInt("Num_proxecto");
                String nomeProyecto = rs.getString("Nome_proxecto");
                String lugar = rs.getString("Lugar");
                int numDepartamentoControla = rs.getInt("Num_departamento_controla");

                Proxecto pr = new Proxecto(numProyecto,nomeProyecto,lugar,numDepartamentoControla);
                proyectos.add(pr);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return proyectos;
    }
}
