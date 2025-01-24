package com.mdelmart.juegocolor;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;

import java.util.Random;

public class JuegoColor extends ApplicationAdapter {
    float intervalo = 1f; // segundos entre cada cambio de color
    Sound bip;
    Random random;
    int r, v, a;
    private float stateTime, stateTimeCambioColor;
    private boolean teclaPulsada;

    @Override
    public void create() {
        bip = Gdx.audio.newSound(Gdx.files.internal("beep.mp3"));
        stateTime = 0;
        stateTimeCambioColor = intervalo;
        r = 1;
        v = 0;
        a = 0;
        random = new Random();
        teclaPulsada = false;
        Gdx.input.setInputProcessor(new ProcesadorEntrada(this));
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(r, v, a, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stateTime += Gdx.graphics.getDeltaTime();

        if (stateTime >= stateTimeCambioColor) {
            if (r == 1 && !teclaPulsada) {
                fin();
            } else {
                teclaPulsada = false;
                r = v = a = 0;
                switch (random.nextInt(3)) {
                    case 0:
                        r = 1;
                        break;
                    case 1:
                        v = 1;
                        break;
                    case 2:
                        a = 1;
                        break;
                }
                stateTimeCambioColor = stateTime + intervalo;
                intervalo *= .95f;
                bip.play();
            }
        }
    }

    private void fin() {
        String error = (r == 1) ? "No has pulsado en rojo." : "Has pulsado pero no en rojo.";
        System.out.println(error + " Tiempo: " + stateTime + "s.");
        Gdx.app.exit();
    }

    public void teclaPulsada() {
        if (r != 1) fin();
        teclaPulsada = true;
    }
}
