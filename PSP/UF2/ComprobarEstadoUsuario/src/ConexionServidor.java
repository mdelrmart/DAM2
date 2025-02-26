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
            Usuario usuarioObjecto = gestorConexiones.getUsuario(usuario);

            Config.AutorizarConexion autorizacion = gestorConexiones.autorizarNuevaConexion(usuario, contrasenha);
            boolean loginCorrecto = (autorizacion == Config.AutorizarConexion.OK);

            out.writeBoolean(loginCorrecto);

            if (loginCorrecto)  {
                System.out.println("Se ha conectado: " + usuario);
                //gestorConexiones.anhadirUsuarioOnline(usuario);

                boolean salir = false;

                while (!salir) {
                    String mensaje = in.readUTF();

                    //out.writeUTF(mensaje);
                    System.out.println("Mensaje de " + usuario + ": " + mensaje);

                    switch (mensaje) {
                        case Config.EXIT:
                            salir = true;
                            gestorConexiones.ponerUsuarioOffline(usuario);
                            gestorConexiones.cerrarCliente(this);
                            break;
                        case Config.SHUTDOWN:
                            salir = true;
                            gestorConexiones.apagarServidor();
                            break;
                        default:
                            Usuario usuario1 = gestorConexiones.getUsuario(mensaje);
                            if (usuario1 != null) {
                                out.writeUTF(usuario1.toString());
                            } else {
                                out.writeUTF("El usuario " + mensaje + " no está conectado");
                            }

                            //String respuesta = in.readUTF();
                            //System.out.println("Respuesta del servidor: " + respuesta);
                    }
                }
                /*
                if (mensaje.equals("salir")) {
                    salir = true;
                    gestorConexiones.ponerUsuarioOffline(usuario);
                    gestorConexiones.cerrarCliente(this);
                } else if (mensaje.equals("apagar")) {
                    salir = true;
                    gestorConexiones.apagarServidor();
                }
                */

            } else {
                out.writeInt(autorizacion.ordinal());
            }

        /*
            else {
                loginCorrecto = false;
                out.writeBoolean(loginCorrecto);
                out.writeUTF("El usuario " + usuario + " ya está conectado");
                System.out.println("El usuario " + usuario + " ya está conectado");
            }
        */
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