package com.mdelmart.dedo.pantallas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mdelmart.dedo.Assets;
import com.mdelmart.dedo.Mundo;

public class PantallaFin extends Pantalla{
    Preferences record = Gdx.app.getPreferences("record");

    public PantallaFin() {
        record.putFloat("record", Math.max(record.getFloat("record", 0), Mundo.puntos));
        record.flush();
    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(Color.WHITE);

        main.sb.begin();

        Assets.fuente.setColor(Color.RED);
        Assets.fuente.draw(main.sb, "HAS PERDIDO", Mundo.ANCHO / 2 - 50, Mundo.ALTO / 2);

        Assets.fuente.setColor(Color.BLACK);
        Assets.fuente.draw(main.sb, "Pulsa ENTER para volver a empezar.", Mundo.ANCHO / 2 - 140, Mundo.ALTO / 2 - 20);

        Assets.fuente.setColor(Color.GREEN);
        Assets.fuente.draw(main.sb, "Record " + (int) record.getFloat("record", 0) + " puntos", Mundo.ANCHO / 2 - 65, Mundo.ALTO / 2 - 40);

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
