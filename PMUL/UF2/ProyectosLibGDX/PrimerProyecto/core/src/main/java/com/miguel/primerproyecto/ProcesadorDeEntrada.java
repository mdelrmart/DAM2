package com.miguel.primerproyecto;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;

public class ProcesadorDeEntrada extends InputAdapter {
    Personaje personaje;

    public ProcesadorDeEntrada(Personaje personaje) {
        this.personaje = personaje;
    }

    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.A) {
            this.personaje.izquierda();
        } else if (keycode == Input.Keys.D) {
            this.personaje.derecha();
        } else if (keycode == Input.Keys.W) {
            this.personaje.arriba();
        } else if (keycode == Input.Keys.S) {
            this.personaje.abajo();
        }

        return true;
    }

    public boolean keyUp(int keycode) {
        if (keycode == Input.Keys.A) {
            this.personaje.parado();
        } else if (keycode == Input.Keys.D) {
            this.personaje.parado();
        } else if (keycode == Input.Keys.W) {
            this.personaje.parado();
        } else if (keycode == Input.Keys.S) {
            this.personaje.parado();
        }

        return true;
    }

}
