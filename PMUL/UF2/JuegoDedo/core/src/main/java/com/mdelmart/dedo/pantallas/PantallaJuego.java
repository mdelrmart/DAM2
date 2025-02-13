package com.mdelmart.dedo.pantallas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mdelmart.dedo.Assets;
import com.mdelmart.dedo.Mundo;

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
                if (!Mundo.dedo.hiperespacioActivo) {
                    Mundo.crearBala();
                }
                break;
            case Input.Keys.H:
                Mundo.dedo.activarHiperespacio();
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

        if (Mundo.dedo.vidas <= 0) {
            main.ponerPantallaFin();
        }

        Mundo.dedo.actualiza(delta);
        Mundo.actualizarEnemigos(delta);
        Mundo.actualizarBalas(delta);
        Mundo.comprobarColisiones();

        Mundo.dedo.comprobarHiperespacio(delta);

        main.sr.begin(ShapeRenderer.ShapeType.Line);
        main.sb.begin();
        Mundo.dibujarEnemigos(main.sb, main.sr);
        main.sr.setColor(Color.BLACK);
        Mundo.dedo.dibuja(main.sb, main.sr);
        Mundo.dibujarBalas(main.sr);
        main.sr.line(0, Mundo.ALTURA_SEPARADOR, Mundo.ANCHO, Mundo.ALTURA_SEPARADOR);

        Assets.fuente.draw(main.sb, "Vidas: " + Mundo.dedo.vidas, 10, Mundo.ALTURA_SEPARADOR - 10);
        Assets.fuente.draw(main.sb, "Tiempo de juego (segs.): " + (int) main.stateTime, 10, Mundo.ALTURA_SEPARADOR - 30);
        Assets.fuente.draw(main.sb, "Puntos: " + (int) Mundo.puntos, 10, Mundo.ALTURA_SEPARADOR - 50);
        Assets.fuente.draw(main.sb, "Hiperespacios: " + Mundo.HIPERESPACIOS, 10, Mundo.ALTURA_SEPARADOR - 70);

        main.sb.end();
        main.sr.end();
    }
}
