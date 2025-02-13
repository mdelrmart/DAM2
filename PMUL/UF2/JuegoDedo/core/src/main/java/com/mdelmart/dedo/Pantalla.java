package com.mdelmart.dedo;

import com.badlogic.gdx.Screen;

public abstract class Pantalla implements Screen {
    public static Main main;

    public abstract boolean teclaAbajo(int keycode);
    public abstract boolean teclaArriba(int keycode);

    public static void setMain(Main main) {
        Pantalla.main = main;
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
    }

    @Override
    public void resize(int width, int height) {
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
