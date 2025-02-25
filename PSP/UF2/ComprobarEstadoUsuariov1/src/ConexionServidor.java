import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ConexionServidor extends Thread {
    Socket socket;
    DataInputStream in;
    DataOutputStream out;
    GestorConexiones gestorConexiones;

    public ConexionServidor(Socket socket, GestorConexiones gestorConexiones) {
        this.socket = socket;
        this.gestorConexiones = gestorConexiones;

        try {
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());

        } catch (IOException e) {
            System.out.println("Error al crear el hilo de la conexión");
        }

        gestorConexiones.guardarNuevaConexion(this);
    }

    @Override
    public void run() {
        try {
            String usuario = in.readUTF();
            String contrasenha = in.readUTF();

            // TODO VALIDAR USUARIO Y CONTRASEÑA

            System.out.println("Se ha conectado: " + usuario);

            boolean salir = false;

            while (!salir) {
                String mensaje = in.readUTF();

                out.writeUTF(mensaje);

                System.out.println("Mensaje de " + usuario + ": " + mensaje);

                if (mensaje.equals("salir")) {
                    salir = true;
                    gestorConexiones.cerrarCliente(this);
                } else if (mensaje.equals("apagar")) {
                    salir = true;
                    gestorConexiones.apagarServidor();
                }
            }

        } catch (IOException e) {
            System.out.println("Error al recibir el mensaje");
        }
    }

    public void cerrarCliente() {
        try {
            socket.close();
        } catch (IOException e) {
            System.out.println("Error al cerrar el socket del cliente");
        }
    }

}