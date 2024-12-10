import org.sqlite.JDBC;
import org.sqlite.SQLiteConnection;

import java.sql.*;
import java.util.Properties;

public class Main {
    public static void main(String[] args) {
        try {
            Connection mysql = DriverManager.getConnection("jdbc:mysql://localhost:3306/", "root", "abc123.");
            System.out.println("Conexion exitosa a MySQL");
            mysql.close();

            Connection sqlServer = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;trustServerCertificate=true", "sa", "abc123.");
            System.out.println("Conexion exitosa a SQL Server");
            sqlServer.close();

            Connection sqlite = DriverManager.getConnection("jdbc:sqlite:BDEmpresa.db");
            System.out.println("Conexion exitosa a SQLite");
            sqlite.close();

            crearTabla();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void crearTabla() {
        // Datos de conexión con la BBDD
        String url = "jdbc:mysql://localhost:3306/BDEmpresa";
        String usuario = "root";
        String contrasenha = "abc123.";

        // Sentencia SQL para crear la tabla "Lugar"
        String sql = """
                    CREATE TABLE Lugar (
                        Lugar VARCHAR(100) NOT NULL,
                        Num_departamento INT NOT NULL,
                        PRIMARY KEY (Lugar, Num_departamento),
                        FOREIGN KEY (Num_departamento) REFERENCES Departamento(Num_departamento)
                            ON DELETE CASCADE
                            ON UPDATE CASCADE
                    );
                """;

        try (Connection conexion = DriverManager.getConnection(url, usuario, contrasenha);
             Statement sentencia = conexion.createStatement()) {

            sentencia.executeUpdate(sql);
            System.out.println("La tabla Lugar ha sido creada correctamente.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}