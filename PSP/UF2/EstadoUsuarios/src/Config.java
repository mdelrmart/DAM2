public class Config {
    static final int PUERTO = 5000;
    static final String CMD_SALIR = "salir";
    static final String CMD_APAGAR = "shutdown";

    enum AutorizarConexion {OK, CredencialesIncorrectas, UsuarioYaConectado;}
}