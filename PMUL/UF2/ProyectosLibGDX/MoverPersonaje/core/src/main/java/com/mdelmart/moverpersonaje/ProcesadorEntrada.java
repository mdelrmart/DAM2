package com.mdelmart.moverpersonaje;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputAdapter;

public class ProcesadorEntrada extends InputAdapter {
    Personaje personaje;

    public ProcesadorEntrada() {
    }

    public ProcesadorEntrada(Personaje personaje) {
        this.personaje = personaje;
    }

    @Override
    public boolean keyDown(int keycode) {
        switch (keycode) {
            case Keys.RIGHT:
                if (!personaje.vaHaciaDelante()) {
                    personaje.cambiaDireccion();
                }
                personaje.setSentidoH(1);
                break;

            case Keys.LEFT:
                if (!personaje.vaHaciaDelante()) {
                    personaje.cambiaDireccion();
                }
                personaje.setSentidoH(-1);
                break;

            case Keys.DOWN:
                personaje.setSentidoV(-1);
                break;

            case Keys.UP:
                personaje.setSentidoV(1);
                break;
        }
        return true;
    }

    @Override
    public boolean keyUp(int keycode) {
        switch (keycode) {
            case (Keys.RIGHT):
                if (Gdx.input.isKeyPressed(Keys.LEFT)) {
                    personaje.setSentidoH(-1);
                } else {
                    personaje.setSentidoH(0);
                }
            case (Keys.LEFT):
                if (Gdx.input.isKeyPressed(Keys.RIGHT)) {
                    personaje.setSentidoH(1);
                } else {
                    personaje.setSentidoH(0);
                }
                break;
        }
        return true;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return super.touchDown(screenX, screenY, pointer, button);
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return super.touchUp(screenX, screenY, pointer, button);
    }
}

