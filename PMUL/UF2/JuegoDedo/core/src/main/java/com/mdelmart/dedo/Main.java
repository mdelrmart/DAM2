package com.mdelmart.dedo;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mdelmart.dedo.entidades.Dedo;
import com.mdelmart.dedo.pantallas.Pantalla;
import com.mdelmart.dedo.pantallas.PantallaFin;
import com.mdelmart.dedo.pantallas.PantallaInicio;
import com.mdelmart.dedo.pantallas.PantallaJuego;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends Game {
    public SpriteBatch sb;
    public ShapeRenderer sr;

    public float stateTime;
    public float tiempoProximoEnemigo;

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

    public void reset() {
        // Reset de variables y estados
        stateTime = 0;
        tiempoProximoEnemigo = Mundo.TIEMPO_ENTRE_ENEMIGOS;
        Mundo.dedo.vidas = Mundo.VIDAS_DEDO;
        Mundo.dedo = new Dedo();
        Mundo.enemigos.clear();
        Mundo.balas.clear();
    }

    public void ponerPantallaJuego() {
        reset();
        setScreen(new PantallaJuego());
    }

    public void ponerPantallaFin() {
        setScreen(new PantallaFin());
    }
}
