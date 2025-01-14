package Clases;

import java.util.Date;

public class VehiculoPropio extends Vehiculo {
    public Date fechaCompra;
    public Double precio;

    public VehiculoPropio(int codVehiculo, String matricula, String marca, String modelo, char combustible, Date fechaCompra, Double precio) {
        super(codVehiculo, matricula, marca, modelo, combustible);
        this.fechaCompra = fechaCompra;
        this.precio = precio;
    }

    public VehiculoPropio(String matricula, String marca, String modelo, char combustible, Date fechaCompra, Double precio) {
        this(-1, matricula, marca, modelo, combustible, fechaCompra, precio);
    }

    public Date getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(Date fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }
}
