package pojos;

import java.util.Date;

public class Vehiculo {
    private String nss;
    private String matricula;
    private String marca;
    private String modelo;
    private Date dataCompra;
    private Empregado empregado;

    public Vehiculo() {
    }

    public Vehiculo(String nss, String matricula, String marca, String modelo, Date dataCompra, Empregado empregado) {
        this.nss = nss;
        this.matricula = matricula;
        this.marca = marca;
        this.modelo = modelo;
        this.dataCompra = dataCompra;
        this.empregado = empregado;
    }

    public Vehiculo(String nss, String matricula, String marca, String modelo, Date dataCompra) {
        this.nss = nss;
        this.matricula = matricula;
        this.marca = marca;
        this.modelo = modelo;
        this.dataCompra = dataCompra;
    }

    public Vehiculo(String matricula, String marca, String modelo, Date dataCompra) {
        this.matricula = matricula;
        this.marca = marca;
        this.modelo = modelo;
        this.dataCompra = dataCompra;
    }

    public String getNss() {
        return this.nss;
    }

    public void setNss(String nss) {
        this.nss = nss;
    }

    public String getMatricula() {
        return this.matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getMarca() {
        return this.marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return this.modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Date getDataCompra() {
        return this.dataCompra;
    }

    public void setDataCompra(Date dataCompra) {
        this.dataCompra = dataCompra;
    }

    public Empregado getEmpregado() {
        return this.empregado;
    }

    public void setEmpregado(Empregado empregado) {
        this.empregado = empregado;
    }

}