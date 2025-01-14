package Clases;

public class Proyecto {
    public int Num_proxecto;
    public String Nome_proxecto;
    public String Lugar;
    public int Num_departamento_controla;

    public Proyecto(int num_proxecto, String nome_proxecto, String lugar, int num_departamento_controla) {
        Num_proxecto = num_proxecto;
        Nome_proxecto = nome_proxecto;
        Lugar = lugar;
        Num_departamento_controla = num_departamento_controla;
    }

    // Constructor para el JSON
    public Proyecto(int num_proxecto, String nome_proxecto, int num_departamento_controla) {
        Num_proxecto = num_proxecto;
        Nome_proxecto = nome_proxecto;
        Num_departamento_controla = num_departamento_controla;
    }

    public int getNum_proxecto() {
        return Num_proxecto;
    }

    public void setNum_proxecto(int num_proxecto) {
        Num_proxecto = num_proxecto;
    }

    public String getNome_proxecto() {
        return Nome_proxecto;
    }

    public void setNome_proxecto(String nome_proxecto) {
        Nome_proxecto = nome_proxecto;
    }

    public String getLugar() {
        return Lugar;
    }

    public void setLugar(String lugar) {
        Lugar = lugar;
    }

    public int getNum_departamento_controla() {
        return Num_departamento_controla;
    }

    public void setNum_departamento_controla(int num_departamento_controla) {
        Num_departamento_controla = num_departamento_controla;
    }
}
