import java.util.Date;

public class VehiculoPropio extends Vehiculo{
    Date fechaCompra;
    Double precio;

    public VehiculoPropio(int codVehiculo, String matricula, String modelo, char combustible, Date fechaCompra, Double precio) {
        super(codVehiculo, matricula, modelo, combustible);
        this.fechaCompra = fechaCompra;
        this.precio = precio;
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
