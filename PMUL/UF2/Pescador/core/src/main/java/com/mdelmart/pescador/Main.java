package com.mdelmart.pescador;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends ApplicationAdapter {
    private SpriteBatch sb;
    private ShapeRenderer sr;

    private float delta;
    private float stateTime;

    OrthographicCamera camara = new OrthographicCamera();

    @Override
    public void create() {
        sb = new SpriteBatch();
        sr = new ShapeRenderer();
        Assets.cargar();
        Gdx.input.setInputProcessor(new ProcesadorDeEntrada());
        stateTime = 0;

        sr.setColor(Color.WHITE);
    }

    @Override
    public void resize(int width, int height) {
        camara.setToOrtho(false, Mundo.ANCHO, Mundo.ALTO);
        camara.update();
        sb.setProjectionMatrix(camara.combined);
        sr.setProjectionMatrix(camara.combined);
    }

    @Override
    public void render() {
        ScreenUtils.clear(Color.WHITE);
        delta = Gdx.graphics.getDeltaTime();
        stateTime += delta;

        Mundo.pescador.actualiza(delta);
        Mundo.anzuelo.actualiza(delta);

        sr.begin(ShapeRenderer.ShapeType.Line);
        sb.begin();

        sb.draw(Assets.fondo, 0, 0, Mundo.ANCHO, Mundo.ALTO);
        Mundo.pescador.dibuja(sb,sr);
        Mundo.anzuelo.dibuja(sb,sr);
        //sr.line(0, Mundo.LIMITE_MAR, Mundo.ANCHO, Mundo.LIMITE_MAR);

        sb.end();
        sr.end();
    }

    @Override
    public void dispose() {
        Assets.liberar();
    }



}
