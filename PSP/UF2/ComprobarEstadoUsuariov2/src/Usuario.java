public class Usuario {
    private String usuario;
    private int numConexiones;
    private boolean online;

    public Usuario(String usuario, int numConexiones, boolean online) {
        this.usuario = usuario;
        this.numConexiones = numConexiones;
        this.online = online;
    }

    @Override
    public String toString() {
        return "Usuario " + usuario + " con " + numConexiones + " conexion(es)." + (online ? " Online" : " Offline");
    }

    public void conectar() {
        numConexiones++;
        online = true;
    }

    public boolean estaOffline() {
        //return online == false;
        return !online;
    }

    public void desconectar() {
        online = false;
    }
}
