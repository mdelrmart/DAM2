import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {
    public static void main(String[] args) {
        Socket socket;
        DataInputStream in;
        DataOutputStream out;

        Scanner scanner = new Scanner(System.in);

        try {
            socket = new Socket(Config.HOST, Config.PORT);
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
        } catch (Exception e) {
            System.out.println("Error al conectar con el servidor");
            return;
        }

        try {
            System.out.println("Introduce tu usuario:");
            String usuario = scanner.nextLine();
            out.writeUTF(usuario);

            System.out.println("Introduce tu contraseña:");
            String contrasenha = scanner.nextLine();
            out.writeUTF(contrasenha);

            boolean salir = false;
            boolean loginCorrecto = in.readBoolean();

            if (!loginCorrecto) {
                //System.out.println("Credenciales no válidas");
                int posicionEnumerado = in.readInt();
                String error = "Error desconocido";

                switch (posicionEnumerado) {
                    case 1:
                        error = "Credenciales no válidas";
                        break;
                    case 2:
                        error = "Usuario ya conectado";
                        break;
                }

                //String mensajeError = in.readUTF();
                //System.out.println(mensajeError);
                System.out.println(error);
                return;
            }

            while (!salir) {
                System.out.print(">");
                String mensaje = scanner.nextLine();
                out.writeUTF(mensaje);

                switch (mensaje) {
                    case Config.EXIT:
                    case Config.SHUTDOWN:
                        salir = true;
                        break;
                    default:
                        String respuesta = in.readUTF();
                        System.out.println("Respuesta del servidor: " + respuesta);
                }

            }

        } catch (Exception e) {
            System.out.println("Error al enviar el usuario");
        }

    }
}
