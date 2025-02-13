package com.mdelmart.dedo;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;

public class PantallaJuego extends Pantalla {

    public PantallaJuego() {
        main.stateTime = 0;
        main.tiempoProximoEnemigo = Mundo.TIEMPO_ENTRE_ENEMIGOS;
    }

    @Override
    public boolean teclaAbajo(int keycode) {
        switch (keycode) {
            case Input.Keys.S:
                Mundo.dedo.bajar();
                break;
            case Input.Keys.W:
                Mundo.dedo.subir();
                break;
            case Input.Keys.SPACE:
                Mundo.crearBala();
                break;
        }
        return true;
    }

    @Override
    public boolean teclaArriba(int keycode) {
        if (keycode == Input.Keys.S) {
            if (!Gdx.input.isKeyPressed(Input.Keys.W)) {
                Mundo.dedo.parar();
            }
            if (Gdx.input.isKeyPressed(Input.Keys.W)) {
                Mundo.dedo.subir();
            }
        } else if (keycode == Input.Keys.W)
            if (!Gdx.input.isKeyPressed(Input.Keys.S)) {
                Mundo.dedo.parar();
            }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            Mundo.dedo.bajar();
        }
        return true;
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(Color.WHITE);

        main.stateTime += delta;

        if (main.stateTime >= main.tiempoProximoEnemigo) {
            Mundo.crearEnemigo();
            main.tiempoProximoEnemigo = main.stateTime + Mundo.TIEMPO_ENTRE_ENEMIGOS;
        }

        Mundo.dedo.actualiza(delta);
        Mundo.actualizarEnemigos(delta);
        Mundo.actualizarBalas(delta);
        Mundo.comprobarColisiones();

        main.sr.begin(ShapeRenderer.ShapeType.Line);
        main.sb.begin();
        Mundo.dedo.dibuja(main.sb, main.sr);
        Mundo.dibujarEnemigos(main.sb, main.sr);
        Mundo.dibujarBalas(main.sr);
        main.sr.line(0, Mundo.ALTURA_SEPARADOR, Mundo.ANCHO, Mundo.ALTURA_SEPARADOR);

        Assets.fuente.draw(main.sb, "Vidas: " + Mundo.dedo.vidas, 10, Mundo.ALTURA_SEPARADOR - 10);
        Assets.fuente.draw(main.sb, "Tiempo de juego (segs.): " + (int) main.stateTime, 10, Mundo.ALTURA_SEPARADOR - 30);

        main.sb.end();
        main.sr.end();
    }

}
