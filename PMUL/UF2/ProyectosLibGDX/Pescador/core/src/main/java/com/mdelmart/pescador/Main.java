package com.mdelmart.pescador;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */

public class Main extends ApplicationAdapter {

    OrthographicCamera camara=new OrthographicCamera();
    private float stateTime,stateTimeProximoPez;
    SpriteBatch sb;
    ShapeRenderer sr;
    @Override public void create() {
        Assets.cargarRecursos();
        stateTime = 0;
        stateTimeProximoPez=Mundo.TIEMPO_ENTRE_PECES;
        sb=new SpriteBatch();
        sr=new ShapeRenderer();
        Gdx.input.setInputProcessor(new ProcesadorDeEntrada());
    }

    @Override public void resize(int width, int height) {
        camara.setToOrtho(false,Mundo.ANCHO,Mundo.ALTO);
        sb.setProjectionMatrix(camara.combined);
        sr.setProjectionMatrix(camara.combined);
    }

    @Override public void render() {
        ScreenUtils.clear(1,1,1,1);
        float delta=Gdx.graphics.getDeltaTime();
        stateTime += delta;
        if(stateTime>stateTimeProximoPez) {
            Mundo.creaPez();
            stateTimeProximoPez=stateTime+Mundo.TIEMPO_ENTRE_PECES;
        }
        Mundo.pescador.actualiza(delta);
        Mundo.anzuelo.actualiza(delta);
        Mundo.actualizaPeces(delta);
        Mundo.comprobarColisiones();
        sr.begin(ShapeRenderer.ShapeType.Line);
        sb.begin();
        sb.draw(Assets.fondo,0,0,Mundo.ANCHO,Mundo.ALTO);
        Assets.fuente.draw(sb,""+(int)stateTime,10,Mundo.ALTO-10);
        Assets.fuente.draw(sb,Mundo.toStringCapturas(),30,Mundo.ALTO-10);
        if(Mundo.MODO_DEBUG) Assets.fuente.draw(sb,Mundo.toStringPecesActuales(),Mundo.ANCHO-50,Mundo.ALTO-10);
        Mundo.pescador.dibuja(sb,sr);
        Mundo.anzuelo.dibuja(sb,sr);
        Mundo.dibujaPeces(sb,sr,stateTime);
        sb.end();
        sr.end();
    }

    @Override public void dispose() {
        Assets.liberarRecursos();
    }

}
