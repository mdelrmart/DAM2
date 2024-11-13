/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package multixanelaII;

import java.util.Vector;

/**
 *
 * @author usuario
 */
public class AlmacenDeUsuarios {

    private static Vector usuarios = new Vector();

    public static boolean isBaleiroAlmacenDeDatos() {
        if (usuarios.size() == 0) {
            return true;
        } else {
            return false;
        }
    }

    public static void anhadirUsuario(Usuario usuario) {
        usuarios.add(usuario);
    }

    public static Vector recuperarUsuarios() {
        return usuarios;
    }

}
