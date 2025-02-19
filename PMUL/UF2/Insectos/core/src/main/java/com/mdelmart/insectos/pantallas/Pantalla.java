package com.mdelmart.insectos.pantallas;

import com.badlogic.gdx.Screen;
import com.mdelmart.insectos.Main;
import com.mdelmart.insectos.Mundo;

public abstract class Pantalla implements Screen {
    static Main main;

    public abstract boolean teclaAbajo(int keycode);
    public abstract boolean teclaArriba(int keycode);
    public abstract boolean clicPantalla(int screenX, int screenY, int pointer, int button);

    static public void setMain(Main main) {
        Pantalla.main = main;
    }

    @Override
    public void resize(int width, int height) {
        main.camara.setToOrtho(false, Mundo.ANCHO, Mundo.ALTO);
        main.camara.update();
        main.sb.setProjectionMatrix(main.camara.combined);
        main.sr.setProjectionMatrix(main.camara.combined);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float v) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
