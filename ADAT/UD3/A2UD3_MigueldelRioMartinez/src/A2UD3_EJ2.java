import java.sql.*;

public class A2UD3_EJ2 {
    public static void main(String[] args) {
        consultarEmpleadoLocalidad("Pontevedra");
    }

    public static void consultarEmpleadoLocalidad(String nomeLocalidade) {
        // Datos de conexión con la BBDD
        String url = "jdbc:mysql://localhost:3306/BDEmpresa";
        String usuario = "root";
        String contrasenha = "abc123.";

        // Sentencia SQL
        String sql = "SELECT NOME, APELIDO_1, APELIDO_2, LOCALIDADE, SALARIO, DATA_NACEMENTO, Nome_departamento, " +
                "       (SELECT Nome " +
                "        FROM EMPREGADO jefe " +
                "        WHERE jefe.NSS = e.NSS_Supervisa) AS Nome_jefe " +
                "FROM EMPREGADO e INNER JOIN DEPARTAMENTO d ON e.Num_departamento_pertenece = d.Num_departamento " +
                "WHERE LOCALIDADE = '" + nomeLocalidade + "';";

        try (Connection conexion = DriverManager.getConnection(url, usuario, contrasenha);
             Statement sentencia = conexion.createStatement()) {

            ResultSet rs = sentencia.executeQuery(sql);

            StringBuilder sb = new StringBuilder();

            while (rs.next()) {
                sb.append("Nombre: ");
                sb.append(rs.getString("Nome")).append(" ").append(rs.getString("Apelido_1")).append(" ").append(rs.getString("Apelido_2"));
                sb.append("\n");

                sb.append("Localidad: ");
                sb.append(rs.getString("Localidade"));
                sb.append("\n");

                sb.append("Salario: ");
                sb.append(rs.getString("Salario"));
                sb.append("\n");

                sb.append("Fecha de Nacimiento: ");
                sb.append(rs.getString("Data_nacemento"));
                sb.append("\n");

                sb.append("Nombre del Jefe: ");
                sb.append(rs.getString("Nome_Jefe"));
                sb.append("\n");

                sb.append("Nombre del departamento: ");
                sb.append(rs.getString("Nome_departamento"));
                sb.append("\n");

                sb.append("---------------------------");
                sb.append("\n");
            }

            System.out.println(sb);

            System.out.println("La operación se ha ejecutado correctamente.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
