import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {
    public static void main(String[] args) {
        Socket socket;
        DataInputStream input = null;
        DataOutputStream output = null;
        Scanner sc = new Scanner(System.in);
        try {
            socket = new Socket("localhost", Config.PUERTO);
            input = new DataInputStream(socket.getInputStream());
            output = new DataOutputStream(socket.getOutputStream());
        } catch (IOException ex) {
            System.out.println("Error al conectar con el servidor");
            return;
        }
        try {
            System.out.print("Introduzca su usuario: ");
            String usuario = sc.nextLine();
            output.writeUTF(usuario);
            System.out.print("Introduzca su contraseña: ");
            output.writeUTF(sc.nextLine());
            boolean loginOk = input.readBoolean();
            if (loginOk) {
                boolean salir = false;
                do {
                    System.out.printf("[%s]> ", usuario);
                    String comando = sc.nextLine();
                    output.writeUTF(comando);
                    switch (comando) {
                        case Config.CMD_SALIR:
                        case Config.CMD_APAGAR:
                            salir = true;
                            break;
                        default:
                            System.out.println(input.readUTF());
                    }
                }
                while (!salir);
            } else {
                int codRespuestaAutorizacion = input.readInt();
                String error = "Error no especificado";
                switch (Config.AutorizarConexion.values()[codRespuestaAutorizacion]) {
                    case CredencialesIncorrectas:
                        error = "Usuario y/o contraseña incorrecta";
                        break;
                    case UsuarioYaConectado:
                        error = "Usuario conectado en otro terminal";
                        break;
                }
                System.out.println(error);
            }
        } catch (IOException ex) {
            System.out.println("La conexion fue finalizada.");
        }
    }
}