package com.mdelmart.insectos.pantallas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
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
        if (keycode == Input.Keys.NUM_1) {
            main.iniciarPantallaJuego();
            Mundo.NUM_INSECTOS = 1;
        } else if (keycode == Input.Keys.NUM_2) {
            main.iniciarPantallaJuego();
            Mundo.NUM_INSECTOS = 2;
        } else if (keycode == Input.Keys.NUM_3) {
            main.iniciarPantallaJuego();
            Mundo.NUM_INSECTOS = 3;
        } else if (keycode == Input.Keys.NUM_4) {
            main.iniciarPantallaJuego();
            Mundo.NUM_INSECTOS = 4;
        } else if (keycode == Input.Keys.NUM_5) {
            main.iniciarPantallaJuego();
            Mundo.NUM_INSECTOS = 5;
        } else if (keycode == Input.Keys.NUM_6) {
            main.iniciarPantallaJuego();
            Mundo.NUM_INSECTOS = 6;
        } else if (keycode == Input.Keys.NUM_7) {
            main.iniciarPantallaJuego();
            Mundo.NUM_INSECTOS = 7;
        } else if (keycode == Input.Keys.NUM_8) {
            main.iniciarPantallaJuego();
            Mundo.NUM_INSECTOS = 8;
        } else if (keycode == Input.Keys.NUM_9) {
            main.iniciarPantallaJuego();
            Mundo.NUM_INSECTOS = 9;
        }

        if (keycode == Input.Keys.F) {
            Gdx.app.exit();
        }

        if (keycode == Input.Keys.R) {
            main.borrarRecords();
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
    public void render(float delta) {
        ScreenUtils.clear(Color.WHITE);
        main.sb.begin();
        Assets.fuente.draw(main.sb, "INSECTOS", Mundo.ANCHO / 2 - 60, Mundo.ALTO / 2 - 20);
        main.sb.draw(Assets.getTexture(aleatorio), Mundo.ANCHO / 2 - 50, Mundo.ALTO / 2,100,100);
        main.sb.end();
    }
}
