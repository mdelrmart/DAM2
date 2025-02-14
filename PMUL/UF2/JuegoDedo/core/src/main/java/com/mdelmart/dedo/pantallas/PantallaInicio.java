package com.mdelmart.dedo.pantallas;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mdelmart.dedo.Assets;
import com.mdelmart.dedo.Mundo;

public class PantallaInicio extends Pantalla {

    @Override
    public void render(float delta) {
        ScreenUtils.clear(Color.WHITE);

        main.sb.begin();
        Assets.fuente.draw(main.sb, "JUEGO DEL DEDO", Mundo.ANCHO / 2 - 75, Mundo.ALTO / 2);
        Assets.fuente.draw(main.sb, "Pulsa ENTER para empezar", Mundo.ANCHO / 2 - 110, Mundo.ALTO / 2 - 20);
        main.sb.end();
    }

    @Override
    public boolean teclaAbajo(int keycode) {
        if (keycode == Input.Keys.ENTER) {
            // main.setScreen(new PantallaJuego());
            main.ponerPantallaJuego();
        }
        return true;
    }

    @Override
    public boolean teclaArriba(int keycode) {
        return false;
    }
}
