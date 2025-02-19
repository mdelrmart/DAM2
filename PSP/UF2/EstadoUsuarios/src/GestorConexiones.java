import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.HashMap;

public class GestorConexiones {
    private ServerSocket serverSocket;
    private HashMap<String, Usuario> datos;
    private ArrayList<ConexionServidor> listaConexiones;

    public GestorConexiones(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
        datos = new HashMap<>();
        listaConexiones = new ArrayList<>();
    }

    public synchronized void nuevaConexionSinAutorizar(ConexionServidor nuevoHilo) {
        listaConexiones.add(nuevoHilo);
    }

    Config.AutorizarConexion autorizarNuevaConexion(String nombreUsuario, String password) {
        if (!credencialesOk(nombreUsuario, password)) return Config.AutorizarConexion.CredencialesIncorrectas;
        Usuario usuario = datos.get(nombreUsuario);
        if (usuario == null) datos.put(nombreUsuario, new Usuario(nombreUsuario));
        else {
            if (usuario.estaONLINE()) return Config.AutorizarConexion.UsuarioYaConectado;
            usuario.login();
        }
        return Config.AutorizarConexion.OK;
    }

    private boolean credencialesOk(String usuario, String password) {
        return password.equalsIgnoreCase(usuario + usuario.length());
    }

    String getInfoUsuario(String nombreUsuario) {
        Usuario usuario = datos.get(nombreUsuario);
        if (usuario != null) return usuario.toString();
        return "Usuario no encontrado";
    }

    void cortarConexion(String nombreUsuario, ConexionServidor conexion) {
        setUsuarioOFFLINE(nombreUsuario);
        conexion.cerrarCliente();
        listaConexiones.remove(conexion);
    }

    private void setUsuarioOFFLINE(String nombreUsuario) {
        Usuario usuario = datos.get(nombreUsuario);
        if (usuario != null) usuario.logout();
    }

    void shutdown() throws IOException {
        serverSocket.close();
        for (ConexionServidor conexion : listaConexiones)
            conexion.cerrarCliente();
    }
}