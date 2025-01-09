public class Vehiculo {
    int codVehiculo;
    String matricula;
    String modelo;
    char combustible;

    public Vehiculo(int codVehiculo, String matricula, String modelo, char combustible) {
        this.codVehiculo = codVehiculo;
        this.matricula = matricula;
        this.modelo = modelo;
        this.combustible = combustible;
    }

    public int getCodVehiculo() {
        return codVehiculo;
    }

    public void setCodVehiculo(int codVehiculo) {
        this.codVehiculo = codVehiculo;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public char getCombustible() {
        return combustible;
    }

    public void setCombustible(char combustible) {
        this.combustible = combustible;
    }
}
