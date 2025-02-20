package com.mdelmart.crecer;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mdelmart.crecer.pantallas.Pantalla;
import com.mdelmart.crecer.pantallas.PantallaInicio;
import com.mdelmart.crecer.pantallas.PantallaJuego;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends Game {
    public SpriteBatch sb;
    public ShapeRenderer sr;
    public OrthographicCamera camara = new OrthographicCamera();
    public float stateTime;
    public float tiempoEntreEnemigos;
    public Preferences record;

    @Override
    public void create() {
        Assets.cargarTexturas();
        sb = new SpriteBatch();
        sr = new ShapeRenderer();

        sr.setColor(Color.BLACK);

        Gdx.input.setInputProcessor(new ProcesadorDeEntrada(this));

        record = Gdx.app.getPreferences("record");
        Pantalla.setMain(this);
        setScreen(new PantallaInicio());
    }


    @Override
    public void dispose() {
        Assets.liberarTexturas();
        sb.dispose();
        sr.dispose();
    }

    public void ponerPantallaInicio() {
        getScreen().dispose();
        setScreen(new PantallaInicio());
    }

    public void ponerPantallaJuego() {
        reset();
        getScreen().dispose();
        setScreen(new PantallaJuego());
    }

    public void reset() {
        // Reset de variables y estados
        stateTime = 0;
        tiempoEntreEnemigos = Mundo.TIEMPO_ENTRE_ENEMIGOS;
        Mundo.jugador = new Jugador();
        Mundo.enemigos.clear();
    }
}
