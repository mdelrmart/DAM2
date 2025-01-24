package com.mdelmart.juegocolor;

import com.badlogic.gdx.InputAdapter;

public class ProcesadorEntrada extends InputAdapter {
    JuegoColor juego;

    public ProcesadorEntrada(JuegoColor juego) {
        this.juego = juego;
    }

    @Override
    public boolean keyTyped(char character) {
        juego.teclaPulsada();
        return true;
    }
}
