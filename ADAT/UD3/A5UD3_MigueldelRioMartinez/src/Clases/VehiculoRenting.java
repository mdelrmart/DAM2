package Clases;

import java.util.Date;

public class VehiculoRenting extends Vehiculo {
    public Date fechaInicio;
    public int mesesAlquilado;
    public Double precio;

    public VehiculoRenting(int codVehiculo, String matricula, String marca, String modelo, char combustible, Date fechaInicio, Double precio, int mesesAlquilado) {
        super(codVehiculo, matricula, marca, modelo, combustible);
        this.fechaInicio = fechaInicio;
        this.precio = precio;
        this.mesesAlquilado = mesesAlquilado;
    }

    public VehiculoRenting(String matricula, String marca, String modelo, char combustible, Date fechaInicio, Double precio, int mesesAlquilado) {
        this(-1, matricula, marca, modelo, combustible, fechaInicio, precio, mesesAlquilado);
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