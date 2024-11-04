package EJ2_A2UD1;

public class ExcepcionNoEsDirectorio extends RuntimeException {
    public ExcepcionNoEsDirectorio(String mensaje) {
        super(mensaje);
    }
}
