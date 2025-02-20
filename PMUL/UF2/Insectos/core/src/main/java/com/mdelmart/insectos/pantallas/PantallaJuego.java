package com.mdelmart.insectos.pantallas;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mdelmart.insectos.Mundo;

public class PantallaJuego extends Pantalla {
    public PantallaJuego() {
        // Reseteamos el mundo al iniciar la pantalla
        Mundo.reiniciar();
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
            main.iniciarPantallaInicio();
        }

        Mundo.insecto.actualiza(delta);

        main.sr.begin(ShapeRenderer.ShapeType.Filled);
        main.srHitbox.begin(ShapeRenderer.ShapeType.Line);

        main.sb.begin();

        Mundo.dibujarInsectosMuertos(main.sb, main.sr, main.srHitbox);
        Mundo.insecto.dibujar(main.sb, main.sr, main.srHitbox);

        main.sb.end();
        main.srHitbox.end();
        main.sr.end();
    }
}
