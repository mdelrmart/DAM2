import java.sql.*;

public class Conexion {
    Connection conexion;

    public Connection inicializarConexionMSSQL(String nombreBD) throws ClassNotFoundException, SQLException {
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        conexion = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;databaseName=" + nombreBD + ";trustServerCertificate=true", "sa", "abc123.");
        System.out.println("Conexión a MSSQL hecha correctamente");
        return conexion;
    }

    public Connection inicializarConexionMySQL(String nombreBD) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/" + nombreBD, "root", "abc123.");
        System.out.println("Conexión a MySQL hecha correctamente");
        return conexion;
    }

    public Connection inicializarConexionSQLite(String rutaBD) throws ClassNotFoundException, SQLException {
        Class.forName("org.jdbc.JDBC");
        conexion = DriverManager.getConnection("jdbc:sqlite:" + rutaBD);
        System.out.println("Conexión a SQLite hecha correctamente");
        return conexion;
    }

}