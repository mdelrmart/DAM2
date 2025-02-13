package com.mdelmart.dedo;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;

import static com.mdelmart.dedo.Mundo.dedo;

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
