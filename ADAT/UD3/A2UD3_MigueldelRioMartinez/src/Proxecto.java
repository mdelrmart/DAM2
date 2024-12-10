public class Proxecto {
    int Num_proxecto;
    String Nome_Proxecto;
    String Lugar;
    int Num_departamento_controla;

    public Proxecto(int num_proxecto, String nome_Proxecto, String lugar, int num_departamento_controla) {
        Num_proxecto = num_proxecto;
        Nome_Proxecto = nome_Proxecto;
        Lugar = lugar;
        Num_departamento_controla = num_departamento_controla;
    }

    public int getNum_proxecto() {
        return Num_proxecto;
    }

    public String getNome_Proxecto() {
        return Nome_Proxecto;
    }

    public String getLugar() {
        return Lugar;
    }

    public int getNum_departamento_controla() {
        return Num_departamento_controla;
    }
}
