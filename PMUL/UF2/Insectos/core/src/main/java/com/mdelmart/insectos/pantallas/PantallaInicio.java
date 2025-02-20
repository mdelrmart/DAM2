package com.mdelmart.insectos.pantallas;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mdelmart.insectos.Assets;
import com.mdelmart.insectos.Mundo;

public class PantallaInicio extends Pantalla {

    int aleatorio = Mundo.random.nextInt(Mundo.NUM_TEXTURAS_INSECTOS);

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
        Assets.fuente.draw(main.sb, "Presiona espacio para iniciar", Mundo.ANCHO / 2 - 170, Mundo.ALTO / 2);
        main.sb.draw(Assets.getTexture(aleatorio), Mundo.ANCHO / 2 - 50, Mundo.ALTO / 2,100,100);
        main.sb.end();
    }
}
