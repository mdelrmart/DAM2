import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    public static boolean salir = false;
    public static ServerSocket serverSocket;

    public static void main(String[] args) {
        try {
            serverSocket = new ServerSocket(5000);
        } catch (IOException e) {
            System.out.println("Error al abrir el socket del servidor");
        }

        System.out.println("Servidor iniciado");
        GestorConexiones gestorConexiones = new GestorConexiones(serverSocket);

        while (!salir) {
            try {
                Socket socket = serverSocket.accept();
                ConexionServidor conexionServidor = new ConexionServidor(socket, gestorConexiones);
                conexionServidor.start();
            } catch (IOException e) {
                System.out.println("El servidor ha caido");
                return;
            }
        }


    }
}
