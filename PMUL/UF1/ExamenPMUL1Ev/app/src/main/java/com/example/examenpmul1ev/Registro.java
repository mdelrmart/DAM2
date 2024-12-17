package com.example.examenpmul1ev;

public class Registro {
    private String alerta;
    private int valida;

    public Registro(String alerta, int valida) {
        this.alerta = alerta;
        this.valida = valida;
    }

    public String getAlerta() {
        return alerta;
    }

    public void setAlerta(String alerta) {
        this.alerta = alerta;
    }

    public int isValid() {
        return valida;
    }

    public void setValid(int valida) {
        this.valida = valida;
    }
}
