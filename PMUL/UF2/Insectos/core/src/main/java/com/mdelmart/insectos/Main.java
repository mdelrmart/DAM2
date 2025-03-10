package com.mdelmart.insectos;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.mdelmart.insectos.pantallas.Pantalla;
import com.mdelmart.insectos.pantallas.PantallaInicio;
import com.mdelmart.insectos.pantallas.PantallaJuego;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends Game {
    public SpriteBatch sb;
    public ShapeRenderer sr;
    public ShapeRenderer srHitbox;

    public float stateTime;

    public Preferences records;

    public OrthographicCamera camara = new OrthographicCamera();

    @Override
    public void create() {
        Assets.cargarTexturas();

        sb = new SpriteBatch();
        sr = new ShapeRenderer();
        srHitbox = new ShapeRenderer();

        sr.setColor(Color.RED);
        srHitbox.setColor(Color.BLACK);

        Gdx.input.setInputProcessor(new ProcesadorDeEntrada(this));

        stateTime = 0;

        records = Gdx.app.getPreferences("record");

        Pantalla.setMain(this);
        setScreen(new PantallaInicio());
    }

    @Override
    public void dispose() {
        sr.dispose();
        srHitbox.dispose();
        sb.dispose();

        Assets.liberarTexturas();
    }

    public void iniciarPantallaJuego() {
        getScreen().dispose();
        setScreen(new PantallaJuego());
    }

    public void iniciarPantallaInicio() {
        getScreen().dispose();
        setScreen(new PantallaInicio());
    }

    public void borrarRecords() {
        records.clear();
        records.flush();
    }

}
