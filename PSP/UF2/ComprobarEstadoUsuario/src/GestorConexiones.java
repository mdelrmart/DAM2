import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class GestorConexiones {
    ServerSocket serverSocket;
    ArrayList<ConexionServidor> listaConexiones;
    Map<String, Usuario> usuarios;

    public GestorConexiones(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
        listaConexiones = new ArrayList<>();
        usuarios = new HashMap<>();
    }

    public void apagarServidor() {
        try {
            serverSocket.close();

            for (ConexionServidor conexionServidor : listaConexiones) {
                //conexionServidor.socket.close();
                conexionServidor.cerrarCliente();

            }
        } catch (Exception e) {
            System.out.println("Error al cerrar el servidor");
        }
    }

    public void guardarNuevaConexion(ConexionServidor conexionServidor) {
        listaConexiones.add(conexionServidor);
    }

    public void cerrarCliente(ConexionServidor conexionServidor) {
        listaConexiones.remove(conexionServidor);
    }

    public boolean validarUsuario(String usuario, String contrasenha) {
        return contrasenha.equals(usuario + usuario.length());
    }

    public void anhadirUsuarioOnline(String nombreUsuario) {
        if (usuarios.containsKey(nombreUsuario)) {
            usuarios.get(nombreUsuario).conectar();
        } else {
            usuarios.put(nombreUsuario, new Usuario(nombreUsuario, 1, true));
        }
    }

    public Usuario getUsuario(String usuario) {
        return usuarios.get(usuario);
    }

    public void ponerUsuarioOffline(String usuario) {
        usuarios.get(usuario).desconectar();
    }

    Config.AutorizarConexion autorizarNuevaConexion(String nombreUsuario, String password) {
        if (!validarUsuario(nombreUsuario, password)) {
            return Config.AutorizarConexion.CREDENCIALES_INCORRECTAS;
        }

        Usuario usuario = usuarios.get(nombreUsuario);
        if (usuario == null) {
            usuarios.put(nombreUsuario, new Usuario(nombreUsuario, 1, true));
        } else {
            if (!usuario.estaOffline()) {
                return Config.AutorizarConexion.USUARIO_YA_CONECTADO;
            }

            usuario.conectar();
        }
        return Config.AutorizarConexion.OK;
    }
}
