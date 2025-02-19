package com.mdelmart.insectos;

import com.badlogic.gdx.InputAdapter;
import com.mdelmart.insectos.pantallas.Pantalla;

public class ProcesadorDeEntrada extends InputAdapter {
    Main main;

    public ProcesadorDeEntrada(Main main) {
        this.main = main;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return ((Pantalla) main.getScreen()).clicPantalla(screenX, screenY, pointer, button);
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
