package com.mdelmart.dedo.pantallas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mdelmart.dedo.Assets;
import com.mdelmart.dedo.Mundo;

public class PantallaFin extends Pantalla{

    @Override
    public void render(float delta) {
        ScreenUtils.clear(Color.WHITE);

        main.sb.begin();
        Assets.fuente.draw(main.sb, "PERDISTE", Mundo.ANCHO / 2, Mundo.ALTO / 2);
        Assets.fuente.draw(main.sb, "Pulsa ENTER para volver a empezar", Mundo.ANCHO / 2, Mundo.ALTO / 2 - 20);
        main.sb.end();
    }

    @Override
    public boolean teclaAbajo(int keycode) {
        if (keycode == Input.Keys.ENTER) {
            //main.setScreen(new PantallaJuego());
            main.ponerPantallaJuego();
        }
        if (keycode == Input.Keys.ESCAPE) {
            Gdx.app.exit();
        }

        return true;

    }

    @Override
    public boolean teclaArriba(int keycode) {
        return false;
    }
}
