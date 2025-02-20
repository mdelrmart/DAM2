package com.mdelmart.crecer.pantallas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mdelmart.crecer.Assets;
import com.mdelmart.crecer.Jugador;
import com.mdelmart.crecer.Mundo;

public class PantallaJuego extends Pantalla {
    private int tiempoRecord;

    public PantallaJuego() {
        main.stateTime = 0;
        main.tiempoEntreEnemigos = Mundo.TIEMPO_ENTRE_ENEMIGOS;
        tiempoRecord = (int) main.record.getFloat(String.valueOf(Mundo.MAX_COLISIONES), 0);
    }

    @Override
    public boolean teclaAbajo(int keycode) {
        if (keycode == Input.Keys.W) {
            Mundo.jugador.sube();
        }
        if (keycode == Input.Keys.S) {
            Mundo.jugador.baja();
        }
        if (keycode == Input.Keys.SPACE) {
            Mundo.jugador.cambiarForma();
        }
        return true;
    }

    @Override
    public boolean teclaArriba(int keycode) {
        if (keycode == Input.Keys.S) {
            if (!Gdx.input.isKeyPressed(Input.Keys.W)) {
                Mundo.jugador.para();
            }
            if (Gdx.input.isKeyPressed(Input.Keys.W)) {
                Mundo.jugador.sube();
            }
        } else if (keycode == Input.Keys.W)
            if (!Gdx.input.isKeyPressed(Input.Keys.S)) {
                Mundo.jugador.para();
            }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            Mundo.jugador.baja();
        }
        return true;
    }


    @Override
    public void render(float delta) {
        ScreenUtils.clear(Color.WHITE);

        if (Mundo.jugador.colisiones >= Mundo.MAX_COLISIONES) {
            guardarRecord();
            main.ponerPantallaInicio();
        }

        main.stateTime += delta;

        Mundo.actualizarEnemigos(delta);
        Mundo.jugador.actualiza(delta);

        Mundo.comprobarColisiones();

        if (main.stateTime >= main.tiempoEntreEnemigos) {
            Mundo.agregarEnemigo();
            main.tiempoEntreEnemigos = main.stateTime + Mundo.TIEMPO_ENTRE_ENEMIGOS;
        }

        main.sr.begin(ShapeRenderer.ShapeType.Line);
        main.sb.begin();

        Mundo.dibujarEnemigos(main.sb, main.sr);
        Mundo.jugador.dibuja(main.sb, main.sr);

        main.sr.line(0, Mundo.ALTURA_SEPARADOR, Mundo.ANCHO, Mundo.ALTURA_SEPARADOR);

        Assets.fuente.draw(main.sb, "Tiempo de juego: " + (int) main.stateTime, 10, Mundo.ALTURA_SEPARADOR - 30);
        Assets.fuente.draw(main.sb, "Colisiones: " + Mundo.jugador.colisiones , 10, Mundo.ALTURA_SEPARADOR - 50);
        Assets.fuente.draw(main.sb, "Record: " + tiempoRecord, 10, Mundo.ALTURA_SEPARADOR - 10);

        if (tiempoRecord < main.stateTime) {
            Assets.fuente.draw(main.sb, "NUEVO RECORD!", 150, Mundo.ALTURA_SEPARADOR - 10);
        }

        main.sb.end();
        main.sr.end();
    }

    public void guardarRecord(){
        //if (tiempoRecord < main.stateTime || tiempoRecord == 0) {
        //    main.record.putInteger("record", (int) main.stateTime);
        //    main.record.flush();
        //}

        if (tiempoRecord < (int) main.stateTime || tiempoRecord == 0) {
            main.record.putFloat(String.valueOf(Mundo.MAX_COLISIONES), main.stateTime);
            main.record.flush();
        }

    }
}

