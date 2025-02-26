package POJOS;
// Generated 09-feb-2023 8:57:23 by Hibernate Tools 3.6.0


import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class Departamento implements java.io.Serializable {
    private int numDepartamento;
    private String nomeDepartamento;

    public Departamento() {
    }

    //D.1
    // empregados que pertenecen a un departamento
    private Set<Empregado> empregados = new HashSet<>();

    //D.2
    // mapeo de los proyectos que controla un departamento
    private Collection<Proxecto> proxectos = new ArrayList<>();

    public int getNumDepartamento() {
        return numDepartamento;
    }

    public void setNumDepartamento(int numDepartamento) {
        this.numDepartamento = numDepartamento;
    }

    public String getNomeDepartamento() {
        return nomeDepartamento;
    }

    public void setNomeDepartamento(String nomeDepartamento) {
        this.nomeDepartamento = nomeDepartamento;
    }

    public Set<Empregado> getEmpregados() {
        return empregados;
    }

    public void setEmpregados(Set<Empregado> empregados) {
        this.empregados = empregados;
    }

    public Collection<Proxecto> getProxectos() {
        return proxectos;
    }

    public void setProxectos(Collection<Proxecto> proxectos) {
        this.proxectos = proxectos;
    }
}