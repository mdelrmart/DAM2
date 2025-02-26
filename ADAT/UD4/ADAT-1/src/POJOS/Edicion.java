package POJOS;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 *
 */
public class Edicion implements java.io.Serializable {

    private EdicionId id;
    private Date data;
    private String lugar;

    // D.6
    // Mapeo de los empleados que participan en un curso
    private Collection<Empregado> empregados = new ArrayList();

    public Edicion() {
    }


    public EdicionId getId() {
        return this.id;
    }

    public void setId(EdicionId id) {
        this.id = id;
    }


    public Date getData() {
        return this.data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getLugar() {
        return this.lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public Collection<Empregado> getEmpregados() {
        return empregados;
    }

    public void setEmpregados(Collection<Empregado> empregados) {
        this.empregados = empregados;
    }
}
