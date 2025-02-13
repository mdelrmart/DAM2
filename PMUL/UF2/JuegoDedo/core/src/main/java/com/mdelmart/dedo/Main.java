package com.mdelmart.dedo;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends Game {
    protected SpriteBatch sb;
    protected ShapeRenderer sr;

    protected float stateTime;
    protected float tiempoProximoEnemigo;

    OrthographicCamera camara = new OrthographicCamera();

    @Override
    public void create() {
        sb = new SpriteBatch();
        sr = new ShapeRenderer();
        Assets.cargar();
        Gdx.input.setInputProcessor(new ProcesadorDeEntrada(this));
        stateTime = 0;

        sr.setColor(Color.BLACK);

        Pantalla.setMain(this);
        setScreen(new PantallaInicio());
    }

    @Override public void resize(int width, int height) {
        camara.setToOrtho(false, Mundo.ANCHO,Mundo.ALTO);
        camara.update();
        sb.setProjectionMatrix(camara.combined); // SpriteBatch
        sr.setProjectionMatrix(camara.combined); // ShapeRenderer
    }

    @Override
    public void dispose() {
        Assets.liberar();
    }

    public void ponerPantallaJuego() {
        setScreen(new PantallaJuego());
    }
}
