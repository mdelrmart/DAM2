package com.mdelmart.pescador;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;

public class ProcesadorDeEntrada extends InputAdapter {
    @Override
    public boolean keyDown(int keycode) {
        switch (keycode) {
            case Input.Keys.A:
                // Mueve el pescador a la izquierda
                Mundo.pescador.izquierda();
                break;
            case Input.Keys.D:
                // Mueve el pescador a la derecha
                Mundo.pescador.derecha();
                break;
            case Input.Keys.S:
                // Mueve el anzuelo hacia abajo
                Mundo.anzuelo.abajo();
                break;
        }
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        if (keycode == Input.Keys.A) {
            // Si la tecla A se suelta y la tecla D no está presionada, el pescador se detiene
            if (!Gdx.input.isKeyPressed(Input.Keys.D)) {
                Mundo.pescador.parar();
            }
            // Si la tecla D está presionada, el pescador se mueve a la derecha
            if (Gdx.input.isKeyPressed(Input.Keys.D)) {
                Mundo.pescador.derecha();
            }
        } else if (keycode == Input.Keys.D) {
            // Si la tecla D se suelta y la tecla A no está presionada, el pescador se detiene
            if (!Gdx.input.isKeyPressed(Input.Keys.A)) {
                Mundo.pescador.parar();
            }
            // Si la tecla A está presionada, el pescador se mueve a la izquierda
            if (Gdx.input.isKeyPressed(Input.Keys.A)) {
                Mundo.pescador.izquierda();
            }
        }
        if (keycode == Input.Keys.S) {
            // Si la tecla S se suelta, el anzuelo se mueve hacia arriba
            Mundo.anzuelo.arriba();
        }
        return true;
    }
}
