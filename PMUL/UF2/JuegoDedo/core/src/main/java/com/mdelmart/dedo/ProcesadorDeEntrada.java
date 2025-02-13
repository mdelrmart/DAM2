package com.mdelmart.dedo;

import com.badlogic.gdx.InputAdapter;
import com.mdelmart.dedo.pantallas.Pantalla;

public class ProcesadorDeEntrada extends InputAdapter {

    Main main;

    public ProcesadorDeEntrada(Main main) {
        this.main = main;
    }


    @Override
    public boolean keyDown(int keycode) {
        return ((Pantalla) main.getScreen()).teclaAbajo(keycode);
    }

    @Override
    public boolean keyUp(int keycode) {
        return ((Pantalla) main.getScreen()).teclaArriba(keycode);
    }
}
