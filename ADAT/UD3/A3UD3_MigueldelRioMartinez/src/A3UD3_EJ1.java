import java.sql.Connection;

public class A3UD3_EJ1 {

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

            // 3)
            Metodos.metadatosDeResultSet(con, "SELECT * FROM PROXECTO");

            //Metodos.obtenerFuncionesProcedimientosEtc(con);
            //Metodos.metadatosDeLimites(con);
            //Metodos.metadatosSobreTranasacciones(con);
            //Metodos.metadatosSobreSoporteDeCaracteristicas(con);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
