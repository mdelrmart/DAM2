package com.mdelmart.dedo;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends ApplicationAdapter {
    private SpriteBatch sb;
    private ShapeRenderer sr;

    private float delta;
    private float stateTime;
    private float tiempoProximoEnemigo = Mundo.TIEMPO_ENTRE_ENEMIGOS;

    OrthographicCamera camara = new OrthographicCamera();

    @Override
    public void create() {
        sb = new SpriteBatch();
        sr = new ShapeRenderer();
        Assets.cargar();
        Gdx.input.setInputProcessor(new ProcesadorDeEntrada());
        stateTime = 0;

        sr.setColor(Color.BLACK);
    }

    @Override public void resize(int width, int height) {
        camara.setToOrtho(false, Mundo.ANCHO,Mundo.ALTO);
        camara.update();
        sb.setProjectionMatrix(camara.combined); // SpriteBatch
        sr.setProjectionMatrix(camara.combined); // ShapeRenderer
    }

    @Override
    public void render() {
        ScreenUtils.clear(Color.WHITE);
        delta = Gdx.graphics.getDeltaTime();
        stateTime += delta;

        if (stateTime >= tiempoProximoEnemigo) {
            Mundo.crearEnemigo();
            tiempoProximoEnemigo = stateTime + Mundo.TIEMPO_ENTRE_ENEMIGOS;
        }

        Mundo.dedo.actualiza(delta);
        Mundo.actualizarEnemigos(delta);
        Mundo.actualizarBalas(delta);
        Mundo.comprobarColisiones();

        sr.begin(ShapeRenderer.ShapeType.Line);
        sb.begin();
        Mundo.dedo.dibuja(sb, sr);
        Mundo.dibujarEnemigos(sb, sr);
        Mundo.dibujarBalas(sr);
        sr.line(0, Mundo.ALTURA_SEPARADOR, Mundo.ANCHO, Mundo.ALTURA_SEPARADOR);

        Assets.fuente.draw(sb, "Vidas: " + Mundo.dedo.vidas, 10, Mundo.ALTURA_SEPARADOR - 10);
        Assets.fuente.draw(sb, "Tiempo de juego (segs.): " + (int) stateTime, 10, Mundo.ALTURA_SEPARADOR - 30);

        sb.end();
        sr.end();
    }

    @Override
    public void dispose() {
        Assets.liberar();
    }
}
