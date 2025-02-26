package POJOS;

import java.util.*;

public class Empregado implements java.io.Serializable {

    private String nss;
    private String nome;
    private String apelido1;
    private String apelido2;
    private Date dataNacemento;
    private Character sexo;
    private String rua;
    private Integer numeroCalle;
    private String piso;
    private String cp;
    private String localidade;
    private String provincia;

    // D.1
    // Mapeo del departamento al que pertenece un empleado
    // (Mapeo de los empleados de un departamento)
    private Departamento departamento;

    // D.1 Mapeo del supervisor de un empleado
    private Empregado supervisor;

    // D.1 Mapeo de los supervisados que supervisa un empleado supervisor
    private Set<Empregado> supervisados = new HashSet<>();

    // D.3
    // Mapeo de los proyectos en los que trabaja un empleado
    private Set<EmpregadoProxecto> proxectos = new HashSet<>();

    // D.4
    // Mapeo del vehiculo del empleado
    private Vehiculo vehiculo;

    // D.6
    // Mapeo de los cursos en los que participa un empleado
    private Set<Edicion> ediciones = new HashSet<>();

    // Ej2
    private List<Familiar> familiares = new ArrayList<>();

    public Empregado() {
    }

    public Empregado(String nss) {
        this.nss = nss;
    }

    public Empregado(String nss, String nome, String apelido1) {
        this.nss = nss;
        this.nome = nome;
        this.apelido1 = apelido1;
    }

    public String getNss() {
        return this.nss;
    }

    public void setNss(String nss) {
        this.nss = nss;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getApelido1() {
        return this.apelido1;
    }

    public void setApelido1(String apelido1) {
        this.apelido1 = apelido1;
    }

    public String getApelido2() {
        return this.apelido2;
    }

    public void setApelido2(String apelido2) {
        this.apelido2 = apelido2;
    }

    public Date getDataNacemento() {
        return this.dataNacemento;
    }

    public void setDataNacemento(Date dataNacemento) {
        this.dataNacemento = dataNacemento;
    }

    public Character getSexo() {
        return this.sexo;
    }

    public void setSexo(Character sexo) {
        this.sexo = sexo;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public Integer getNumeroCalle() {
        return numeroCalle;
    }

    public void setNumeroCalle(Integer numeroCalle) {
        this.numeroCalle = numeroCalle;
    }

    public String getPiso() {
        return piso;
    }

    public void setPiso(String piso) {
        this.piso = piso;
    }

    public String getCp() {
        return cp;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    public String getLocalidade() {
        return localidade;
    }

    public void setLocalidade(String localidade) {
        this.localidade = localidade;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public Departamento getDepartamento() {
        return this.departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public Set<EmpregadoProxecto> getProxectos() {
        return proxectos;
    }

    public void setProxectos(Set<EmpregadoProxecto> proxectos) {
        this.proxectos = proxectos;
    }

    public Empregado getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(Empregado supervisor) {
        this.supervisor = supervisor;
    }

    public Set<Empregado> getSupervisados() {
        return supervisados;
    }

    public void setSupervisados(Set<Empregado> supervisados) {
        this.supervisados = supervisados;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }

    public Set<Edicion> getEdiciones() {
        return ediciones;
    }

    public void setEdiciones(Set<Edicion> ediciones) {
        this.ediciones = ediciones;
    }

    public List<Familiar> getFamiliares() {
        return familiares;
    }

    public void setFamiliares(List<Familiar> familiares) {
        this.familiares = familiares;
    }
}
