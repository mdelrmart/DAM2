package POJOS;


import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class Proxecto implements java.io.Serializable {

    private int numProxecto; 
    private String nomeProxecto;
    private String lugar;

    //D.2 Mapeo de los proyectos que controla un departamento
    Departamento departamentoControla;

    //D.3 Mapeo de los empleados que trabajan en un proyecto
    private Collection<EmpregadoProxecto> empregados = new ArrayList<>();

    public Proxecto(int numProxecto, String nomeProxecto, String lugar) {
        this.numProxecto = numProxecto;
        this.nomeProxecto = nomeProxecto;
        this.lugar = lugar;
    }

    public Proxecto() {
    }
 
    public int getNumProxecto() {
        return this.numProxecto;
    }

    public void setNumProxecto(int numProxecto) {
        this.numProxecto = numProxecto;
    }
 

    public String getNomeProxecto() {
        return this.nomeProxecto;
    }

    public void setNomeProxecto(String nomeProxecto) {
        this.nomeProxecto = nomeProxecto;
    }

    public String getLugar() {
        return this.lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public Departamento getDepartamentoControla() {
        return departamentoControla;
    }

    public void setDepartamentoControla(Departamento departamentoControla) {
        this.departamentoControla = departamentoControla;
    }

    public Collection<EmpregadoProxecto> getEmpregados() {
        return empregados;
    }

    public void setEmpregados(Collection<EmpregadoProxecto> empregados) {
        this.empregados = empregados;
    }
}
