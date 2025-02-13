package com.mdelmart.dedo;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;

import static com.mdelmart.dedo.Mundo.dedo;

public class ProcesadorDeEntrada extends InputAdapter {

    @Override
    public boolean keyDown(int keycode) {
        switch (keycode) {
            case Input.Keys.S:
                Mundo.dedo.bajar();
                break;
            case Input.Keys.W:
                Mundo.dedo.subir();
                break;
            case Input.Keys.SPACE:
                Mundo.crearBala();
                break;
        }
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        if (keycode == Input.Keys.S) {
            if (!Gdx.input.isKeyPressed(Input.Keys.W)) {
                Mundo.dedo.parar();
            }
            if (Gdx.input.isKeyPressed(Input.Keys.W)) {
                Mundo.dedo.subir();
            }
        } else if (keycode == Input.Keys.W)
            if (!Gdx.input.isKeyPressed(Input.Keys.S)) {
                Mundo.dedo.parar();
            }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            Mundo.dedo.bajar();
        }
        return true;
    }
}
