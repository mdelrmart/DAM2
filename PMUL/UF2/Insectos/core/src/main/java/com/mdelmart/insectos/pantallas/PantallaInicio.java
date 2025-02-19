package com.mdelmart.insectos.pantallas;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mdelmart.insectos.Assets;
import com.mdelmart.insectos.Mundo;

public class PantallaInicio extends Pantalla {

    public PantallaInicio() {
    }

    @Override
    public boolean teclaAbajo(int keycode) {
        if (keycode == Input.Keys.SPACE) {
            main.iniciarPantallaJuego();
        }
        return true;
    }

    @Override
    public boolean teclaArriba(int keycode) {
        return false;
    }

    @Override
    public boolean clicPantalla(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public void render(float v) {
        ScreenUtils.clear(Color.WHITE);
        main.sb.begin();
        Assets.fuente.draw(main.sb, "Presiona espacio para iniciar", Mundo.ANCHO / 2 - Assets.fuente.getRegion().getRegionWidth() / 2, Mundo.ALTO / 4 * 3);
        main.sb.draw(Assets.getTexture(5), Mundo.ANCHO / 2 - Assets.getTexture(5).getWidth() / 2, Mundo.ALTO / 2 - Assets.getTexture(5).getHeight() / 2,200,200);
        main.sb.end();
    }
}
