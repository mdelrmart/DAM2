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
            boolean loginCorrecto;
            Usuario usuarioObjecto = gestorConexiones.getUsuario(usuario);

            if (usuarioObjecto == null || usuarioObjecto.estaOffline()) {
                if (!gestorConexiones.validarUsuario(usuario, contrasenha)) {
                    loginCorrecto = false;
                    out.writeBoolean(loginCorrecto);
                    return;
                }

                loginCorrecto = true;
                out.writeBoolean(loginCorrecto);

                System.out.println("Se ha conectado: " + usuario);
                gestorConexiones.anhadirUsuarioOnline(usuario);

                boolean salir = false;

                while (!salir) {
                    String mensaje = in.readUTF();

                    //out.writeUTF(mensaje);
                    Usuario usuario1 = gestorConexiones.getUsuario(mensaje);

                    if (usuario1 != null) {
                        out.writeUTF(usuario1.toString());
                    } else {
                        out.writeUTF("El usuario " + mensaje + " no está conectado");
                    }

                    System.out.println("Mensaje de " + usuario + ": " + mensaje);

                    if (mensaje.equals("salir")) {
                        salir = true;
                        gestorConexiones.ponerUsuarioOffline(usuario);
                        gestorConexiones.cerrarCliente(this);
                    } else if (mensaje.equals("apagar")) {
                        salir = true;
                        gestorConexiones.apagarServidor();
                    }
                }
            } else {
                loginCorrecto = false;
                out.writeBoolean(loginCorrecto);
                out.writeUTF("El usuario " + usuario + " ya está conectado");
                System.out.println("El usuario " + usuario + " ya está conectado");
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