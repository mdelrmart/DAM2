import java.util.Date;

public class VehiculoRenting extends Vehiculo {
    Date fechaInicio;
    int mesesAlquilado;
    Double precio;

    public VehiculoRenting(int codVehiculo, String matricula, String marca, String modelo, char combustible, Date fechaInicio, int mesesAlquilado, Double precio) {
        super(codVehiculo, matricula, marca,modelo, combustible);
        this.fechaInicio = fechaInicio;
        this.mesesAlquilado = mesesAlquilado;
        this.precio = precio;
    }

    public VehiculoRenting(String matricula, String marca, String modelo, char combustible, Date fechaInicio, int mesesAlquilado, Double precio) {
        this(-1, matricula, marca, modelo, combustible, fechaInicio, mesesAlquilado, precio);
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public int getMesesAlquilado() {
        return mesesAlquilado;
    }

    public void setMesesAlquilado(int mesesAlquilado) {
        this.mesesAlquilado = mesesAlquilado;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }
}