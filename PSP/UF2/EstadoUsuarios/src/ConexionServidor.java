import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ConexionServidor extends Thread {
    private GestorConexiones gestorConexiones;
    private Socket socket;
    private DataInputStream input;
    private DataOutputStream output;

    ConexionServidor(GestorConexiones gestorConexiones, Socket socket) {
        this.gestorConexiones = gestorConexiones;
        this.socket = socket;
        try {
            input = new DataInputStream(this.socket.getInputStream());
            output = new DataOutputStream(this.socket.getOutputStream());
        } catch (IOException ex) {
            System.out.println("Error al establecer la nueva conexion");
        }
        gestorConexiones.nuevaConexionSinAutorizar(this);
    }

    @Override
    public void run() {
        String nombreUsuario = "";
        try {
            nombreUsuario = input.readUTF();
            String password = input.readUTF();
            Config.AutorizarConexion autorizacion = gestorConexiones.autorizarNuevaConexion(nombreUsuario, password);
            boolean loginOk = (autorizacion == Config.AutorizarConexion.OK);
            output.writeBoolean(loginOk);
            if (loginOk) {
                System.out.println("Conectado " + nombreUsuario);
                boolean salir = false;
                do {
                    String comando = input.readUTF();
                    switch (comando) {
                        case Config.CMD_SALIR:
                            gestorConexiones.cortarConexion(nombreUsuario, this);
                            salir = true;
                            break;
                        case Config.CMD_APAGAR:
                            gestorConexiones.shutdown();
                            salir = true;
                            break;
                        default:
                            output.writeUTF(gestorConexiones.getInfoUsuario(comando));
                            break;
                    }
                }
                while (!salir);
            } else
                output.writeInt(autorizacion.ordinal());
        } catch (IOException ex) {
            System.out.printf("Conexion con %s finalizada por el servidor\n", nombreUsuario);
        }
    }

    void cerrarCliente() {
        try {
            socket.close();
        } catch (IOException ex) {
            System.out.println("Problemas cerrando cliente");
        }
    }
}