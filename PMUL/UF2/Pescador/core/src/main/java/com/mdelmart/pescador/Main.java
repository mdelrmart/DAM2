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
    private float tiempoProximoPez = Mundo.TIEMPO_ENTRE_PECES;

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

        if (stateTime >= tiempoProximoPez) {
            Mundo.crearPez();
            tiempoProximoPez = stateTime + Mundo.TIEMPO_ENTRE_PECES;
        }

        Mundo.pescador.actualiza(delta);
        Mundo.anzuelo.actualiza(delta);
        Mundo.actualizarPeces(delta);
        Mundo.comprobarColisiones();

        sr.begin(ShapeRenderer.ShapeType.Line);
        sb.begin();

        sb.draw(Assets.fondo, 0, 0, Mundo.ANCHO, Mundo.ALTO);

        Mundo.pescador.dibuja(sb,sr);
        Mundo.anzuelo.dibuja(sb,sr);
        Mundo.dibujarPeces(sb, sr);

        // Línea de límite del mar
        //sr.line(0, Mundo.LIMITE_MAR, Mundo.ANCHO, Mundo.LIMITE_MAR);

        Assets.fuente.draw(sb, "Tiempo de juego (segs.): " + (int) stateTime, 10, Mundo.ALTO - 10);
        Assets.fuente.draw(sb, "Peces pescados: " + Mundo.pescador.pecesPescados, 400, Mundo.ALTO - 10);

        if (Mundo.DEBUG) {
            Assets.fuente.draw(sb, "Peces mem.: " + Mundo.peces.size, 800, Mundo.ALTO - 10);
        }

        sb.end();
        sr.end();
    }

    @Override
    public void dispose() {
        Assets.liberar();
    }

}
