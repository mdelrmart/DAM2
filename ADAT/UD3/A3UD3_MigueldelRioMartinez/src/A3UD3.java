// Nos dijo Pepa, que hicieramos el ejercicio 1, 2 y 7.
import java.sql.Connection;

public class A3UD3 {

    static Connection con;

    public static void main(String[] args) {
        try {
            Conexion conexionMYSQL = new Conexion();
            con = conexionMYSQL.inicializarConexionMySQL("BDEmpresa");

            // 1)
            Metodos.mostrarMetadatos(con);

            // 2)
            // a)
            Metodos.mostrarTodasLasTablas(con);
            // b)
            Metodos.mostrarInfoTabla(con, "PROXECTO");
            // c)
            Metodos.mostrarProcedimientos(con);
            // d)
            Metodos.obtenerClavePrimaria(con, "BDEmpresa", "EMPREGADO");
            // e)
            Metodos.obtenerClavesForaneas(con, "BDEmpresa", "EMPREGADO_PROXECTO");

            // 7)
            Metodos.metadatosDeResultSet(con, "SELECT * FROM PROXECTO");
            
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
