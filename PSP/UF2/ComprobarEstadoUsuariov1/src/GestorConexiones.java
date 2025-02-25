import java.net.ServerSocket;
import java.util.ArrayList;

public class GestorConexiones {
    ServerSocket serverSocket;
    ArrayList<ConexionServidor> listaConexiones;

    public GestorConexiones(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
        listaConexiones = new ArrayList<>();
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
}
