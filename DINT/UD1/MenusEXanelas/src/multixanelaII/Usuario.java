/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package multixanelaII;

/**
 *
 * @author usuario
 */
public class Usuario {

    private int idUsuario;
    private String nome;
    private String apelidos;
    private String provincia;

    @Override
    public String toString() {
        return "Usuario ID " + idUsuario + "- " + nome + " " + apelidos + " (" + provincia + ")";
    }

    public Usuario(int idUsuario, String nome, String apelidos, String provincia) {
        this.idUsuario = idUsuario;
        this.nome = nome;
        this.apelidos = apelidos;
        this.provincia = provincia;
    }

}
