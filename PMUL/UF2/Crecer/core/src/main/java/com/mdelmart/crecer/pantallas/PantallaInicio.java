package com.mdelmart.crecer.pantallas;


import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mdelmart.crecer.Assets;
import com.mdelmart.crecer.Mundo;

public class PantallaInicio extends Pantalla {
    @Override
    public boolean teclaAbajo(int keycode) {
        if (keycode == Input.Keys.ENTER) {
            main.ponerPantallaJuego();
        }
        if (keycode == Input.Keys.R) {
            main.record.clear();
            main.record.flush();
        }
        return false;
    }

    @Override
    public boolean teclaArriba(int keycode) {
        return false;
    }

    @Override
    public void render(float delta) {
        main.sb.begin();

        ScreenUtils.clear(Color.WHITE);
        Assets.fuente.draw(main.sb, "Pulsa ENTER para empezar", Mundo.ANCHO / 2 - 100, Mundo.ALTO / 2);

        main.sb.end();
    }
}
