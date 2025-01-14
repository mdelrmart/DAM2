import java.sql.*;

public class A5UD3_EJ1 {
    public static void main(String[] args) {
        if (args.length < 4) {
            System.out.println("Uso: <base_datos> <usuario> <contraseña> <sentencias_sql>");
            return;
        }

        String database = args[0];
        String user = args[1];
        String password = args[2];
        String sqlStatements = args[3];

        // Construcción de la URL de conexión
        String connectionUrl = "jdbc:sqlserver://localhost:1433;databaseName=" + database + ";encrypt=true;trustServerCertificate=true;";

        try (Connection connection = DriverManager.getConnection(connectionUrl, user, password)) {
            System.out.println("Conexión exitosa a la base de datos.");

            // Dividimos las sentencias SQL por punto y coma
            String[] sentencias = sqlStatements.split(";");

            for (String sentencia : sentencias) {
                if (sentencia.trim().isEmpty()) {
                    // Ignoramos las sentencias vacías
                    continue;
                }

                try (Statement stmt = connection.createStatement()) {
                    boolean tieneResultSet = stmt.execute(sentencia);

                    if (tieneResultSet) {
                        try (ResultSet resultSet = stmt.getResultSet()) {
                            ResultSetMetaData metaData = resultSet.getMetaData();
                            int columnCount = metaData.getColumnCount();

                            // Imprimimos los encabezados de las columnas
                            for (int i = 1; i <= columnCount; i++) {
                                System.out.print(metaData.getColumnName(i) + "\t");
                            }
                            System.out.println();

                            // Imprimimos las filas de los resultados
                            while (resultSet.next()) {
                                for (int i = 1; i <= columnCount; i++) {
                                    System.out.print(resultSet.getObject(i) + "\t");
                                }
                                System.out.println();
                            }
                        }
                    } else {
                        int updateCount = stmt.getUpdateCount();
                        System.out.println("Sentencia ejecutada. Filas afectadas: " + updateCount);
                    }

                } catch (SQLException e) {
                    System.err.println("Error al ejecutar la sentencia: " + sentencia);
                    System.err.println("Mensaje de error: " + e.getMessage());
                }
            }

        } catch (SQLException e) {
            System.err.println("Error al conectar con la base de datos.");
            System.err.println("Mensaje: " + e.getMessage());
        }
    }
}