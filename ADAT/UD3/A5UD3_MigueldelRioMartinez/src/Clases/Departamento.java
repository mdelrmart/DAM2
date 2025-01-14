package Clases;

import java.util.Date;

public class Departamento {
    public int numDepartamento;
    public String nomeDepartamento;
    public String nssDirige;
    public Date dataDireccion;

    public Departamento(int numDepartamento, String nomeDepartamento, String nssDirige, Date dataDireccion) {
        this.numDepartamento = numDepartamento;
        this.nomeDepartamento = nomeDepartamento;
        this.nssDirige = nssDirige;
        this.dataDireccion = dataDireccion;
    }

    public Departamento(int numDepartamento, String nomeDepartamento) {
        this.numDepartamento = numDepartamento;
        this.nomeDepartamento = nomeDepartamento;
    }

}
