public class Config {
    public static final int PORT = 5000;
    public static final String HOST = "localhost";
    public static final String EXIT = "salir";
    public static final String SHUTDOWN = "apagar";

    enum AutorizarConexion {
        OK,
        CREDENCIALES_INCORRECTAS,
        USUARIO_YA_CONECTADO
    }
}
