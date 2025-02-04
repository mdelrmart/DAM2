package pojos;

import java.util.Date;

public class Familiar {

    private String nss;
    private String nome;
    private String apelido1;
    private String apelido2;
    private Date datanacemento;
    private String parentesco;
    private Character sexo;
    private FamiliarId id;
    private Empregado empregado;

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

    public Date getDatanacemento() {
        return this.datanacemento;
    }

    public void setDatanacemento(Date datanacemento) {
        this.datanacemento = datanacemento;
    }

    public String getParentesco() {
        return this.parentesco;
    }

    public void setParentesco(String parentesco) {
        this.parentesco = parentesco;
    }

    public Character getSexo() {
        return this.sexo;
    }

    public void setSexo(Character sexo) {
        this.sexo = sexo;
    }

    public FamiliarId getId() {
        return this.id;
    }

    public void setId(FamiliarId id) {
        this.id = id;
    }

    public Empregado getEmpregado() {
        return this.empregado;
    }

    public void setEmpregado(Empregado empregado) {
        this.empregado = empregado;
    }

}
