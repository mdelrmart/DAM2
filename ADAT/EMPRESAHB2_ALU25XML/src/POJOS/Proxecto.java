package POJOS;


import java.util.Collection;

public class Proxecto implements java.io.Serializable {

    private int numProxecto; 
    private String nomeProxecto;
    private String lugar;

    private Empregado empregado;

    Collection<EmpregadoProxecto> empregadoProxectos;

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

    public Empregado getEmpregado() {
        return empregado;
    }

    public void setEmpregado(Empregado empregado) {
        this.empregado = empregado;
    }
}
