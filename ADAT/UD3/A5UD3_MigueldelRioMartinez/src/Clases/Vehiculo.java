package Clases;

public class Vehiculo {
    public int codVehiculo;
    public String matricula;
    public String marca;
    public String modelo;
    public char combustible;

    public Vehiculo(int codVehiculo, String matricula, String marca, String modelo, char combustible) {
        this.codVehiculo = codVehiculo;
        this.matricula = matricula;
        this.marca = marca;
        this.modelo = modelo;
        this.combustible = combustible;
    }

    public Vehiculo(String matricula, String marca, String modelo, char combustible) {
        // Si es -1 sabemos que aun no se insertó en la base de datos
        this.codVehiculo = -1;
        this.matricula = matricula;
        this.marca = marca;
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

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }
    /*   public void insertar()
    {

    }*/
}
