import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {
    public static void main(String[] args) {

        String servidor = "localhost", FIN = "fin", mensaje = "";
        int puerto = 7; // puerto ECHO
        Scanner sc = new Scanner(System.in);

        /* comentado para trabajar con localhost
        System.out.println("Servidor? ");
        servidor=sc.nextLine(); */

        Socket socket;
        DataInputStream in;
        DataOutputStream out;

        try {
            socket = new Socket(servidor, puerto);
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
        } catch (IOException ex) {
            System.out.println("Problemas conectando con servidor " + servidor + ":" + puerto);
            return;
        }

        System.out.println("Conectado con el servidor");

        while (!mensaje.equalsIgnoreCase(FIN)) {
            mensaje = sc.nextLine();
            String strRecibido;

            try {
                //Enviamos
                out.writeUTF(mensaje);
                System.out.println("Cliente envía: " + mensaje);

                //Recibimos
                strRecibido = in.readUTF();

                if (strRecibido.equals("close")) {
                    System.out.println("El servidor ha cerrado la conexión, ya que hay un usuario con el mismo nombre logueado");
                    System.out.println("***************************");
                    break;
                }
                else {
                    System.out.println("Cliente recibe: " + strRecibido);
                }
            } catch (IOException ex) {
                System.out.println("Problemas en la transmisión");
                break;
            }
            /* if (!mensaje.equals(strRecibido))
                System.out.println("Ha ocurrido un problema. Las cadenas son distintas"); */
            System.out.println("**********************************************");
        }

        try {
            socket.close();
        } catch (IOException ex) {
            System.out.println("Error cerrando conexión");
        }

    }
}