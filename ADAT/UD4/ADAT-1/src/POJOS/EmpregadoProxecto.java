package POJOS;

public class EmpregadoProxecto implements java.io.Serializable {

    //definicion de la clave compuesta
    private EmpregadoProxectoId id;

    private Integer horas;

    // D.3
    // empregado de un proxecto
    private Empregado empregado;
    // D.3
    // proyecto en el que trabaja un empleado
    private Proxecto proxecto;

    public EmpregadoProxecto() {
    }

    public EmpregadoProxecto(EmpregadoProxectoId id, Integer horas, Empregado empregado, Proxecto proxecto) {
        this.id = id;
        this.horas = horas;
        this.empregado = empregado;
        this.proxecto = proxecto;
    }

    public EmpregadoProxecto(EmpregadoProxectoId id, Empregado empregado, Proxecto proxecto) {
        this.id = id;
        this.empregado = empregado;
        this.proxecto = proxecto;
    }

    public EmpregadoProxectoId getId() {
        return this.id;
    }

    public void setId(EmpregadoProxectoId id) {
        this.id = id;
    }

    public Integer getHoras() {
        return this.horas;
    }

    public void setHoras(Integer horas) {
        this.horas = horas;
    }

    public Empregado getEmpregado() {
        return empregado;
    }

    public void setEmpregado(Empregado empregado) {
        this.empregado = empregado;
    }

    public Proxecto getProxecto() {
        return proxecto;
    }

    public void setProxecto(Proxecto proxecto) {
        this.proxecto = proxecto;
    }
}
