package com.mdelmart.insectos.pantallas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mdelmart.insectos.Assets;
import com.mdelmart.insectos.Mundo;

public class PantallaJuego extends Pantalla {
    int tiempoRecord = (int) main.records.getFloat(String.valueOf(Mundo.NUM_INSECTOS),0);

    public PantallaJuego() {
        // Reseteamos el mundo al iniciar la pantalla
        Mundo.reiniciar();
        main.stateTime = 0;
    }

    @Override
    public boolean teclaAbajo(int keycode) {
        return false;
    }

    @Override
    public boolean teclaArriba(int keycode) {
        return false;
    }

    @Override
    public boolean clicPantalla(int screenX, int screenY, int pointer, int button) {
        Mundo.comprobarClic(screenX, screenY, pointer, button);
        return true;
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(Color.WHITE);

        if (Mundo.insecto.estaMuerto()) {
            // Si murió el insecto, volvemos a la pantalla de inicio
            main.iniciarPantallaInicio();
            guardarRecord();
        }

        Mundo.insecto.actualiza(delta);

        main.sr.begin(ShapeRenderer.ShapeType.Filled);
        main.srHitbox.begin(ShapeRenderer.ShapeType.Line);
        main.sb.begin();

        Mundo.dibujarInsectosMuertos(main.sb, main.sr, main.srHitbox);
        Mundo.insecto.dibujar(main.sb, main.sr, main.srHitbox);

        Assets.fuente.draw(main.sb, "Record: " + tiempoRecord, 10, Mundo.ALTO - 10);

        for (int i = 0; i < Mundo.insectosMuertos.size; i++) {
            Assets.fuente.draw(main.sb, "I", 150 + i * 10, Mundo.ALTO - 10);
        }

        main.stateTime += delta;

        main.sb.end();
        main.srHitbox.end();
        main.sr.end();
    }

    public void guardarRecord() {
        System.out.println(tiempoRecord);
        System.out.println(main.stateTime);
        if (tiempoRecord > (int) main.stateTime || tiempoRecord == 0) {
            main.records.putFloat(String.valueOf(Mundo.NUM_INSECTOS), main.stateTime);
            main.records.flush();
        }
    }

}
