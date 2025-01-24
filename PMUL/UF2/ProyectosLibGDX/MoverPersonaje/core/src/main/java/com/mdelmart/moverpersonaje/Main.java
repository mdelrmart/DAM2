package com.mdelmart.moverpersonaje;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

/**
 * {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms.
 */
public class Main extends ApplicationAdapter {
    SpriteBatch batch;
    Texture img;
    private float delta;
    private Personaje p;

    @Override
    public void create() {
        batch = new SpriteBatch();
        img = new Texture("corredor.png");
        p = new Personaje(0, 0, 100, 50, 50, img);
        Gdx.input.setInputProcessor(new ProcesadorEntrada(p));
    }

    @Override
    public void render() {
        //ScreenUtils.clear(255, 255, 255, 1);
        ScreenUtils.clear(Color.FOREST);
        batch.begin();

        delta = Gdx.graphics.getDeltaTime();
        p.actualizar(delta);
        p.dibujar(batch);

        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        img.dispose();
    }
}
