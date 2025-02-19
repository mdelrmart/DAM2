import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    private static ServerSocket serverSocket;
    public static boolean salir = false;
    private static GestorConexiones gestorConexiones;

    public static void cerrar() {
        try {
            serverSocket.close();
        } catch (IOException ex) {
            System.out.println("Servidor abajo");
        }
    }

    public static void main(String[] args) {
        try {
            serverSocket = new ServerSocket(Config.PUERTO);
        } catch (IOException ex) {
            System.out.println("Error al iniciar el servidor");
            return;
        }
        System.out.println("Servidor arriba, esperando conexiones");
        gestorConexiones = new GestorConexiones(serverSocket);
        while (!salir) {
            try {
                Socket socket = serverSocket.accept();
                new ConexionServidor(gestorConexiones, socket).start();
            } catch (IOException ex) {
                System.out.println("El servidor ha caído");
                return;
            }
        }
    }
}