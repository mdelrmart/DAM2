public class Usuario {
    private String nombre;
    private int conexiones = 0;
    private boolean online;

    public Usuario(String nombre) {
        this.nombre = nombre;
        login();
    }

    public String getNombre() {
        return nombre;
    }

    public boolean estaONLINE() {
        return online;
    }

    ;

    public void login() {
        conexiones++;
        online = true;
    }

    public void logout() {
        online = false;
    }

    @Override
    public String toString() {
        return (online ? "ONLINE " : "OFFLINE ") + nombre + " " + conexiones + " conexiones";
    }
}