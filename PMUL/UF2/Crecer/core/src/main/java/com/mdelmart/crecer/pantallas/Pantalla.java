package com.mdelmart.crecer.pantallas;

import com.badlogic.gdx.Screen;
import com.mdelmart.crecer.Main;
import com.mdelmart.crecer.Mundo;

public abstract class Pantalla implements Screen {
    public static Main main;

    static public void setMain(Main main) {
        Pantalla.main = main;
    }

    public abstract boolean teclaAbajo(int keycode);
    public abstract boolean teclaArriba(int keycode);

    @Override
    public void resize(int width, int height) {
        main.camara.setToOrtho(false, Mundo.ANCHO,Mundo.ALTO);
        main.camara.update();
        main.sb.setProjectionMatrix(main.camara.combined);
        main.sr.setProjectionMatrix(main.camara.combined);
    }

    @Override public void show() {}
    @Override public void render(float delta) {}
    @Override public void pause() {}
    @Override public void resume() {}
    @Override public void hide() {}
    @Override public void dispose() {}
}
