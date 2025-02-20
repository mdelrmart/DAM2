package com.mdelmart.crecer;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class Assets {
    public static BitmapFont fuente;

    public static void cargarTexturas() {
        fuente = new BitmapFont(Gdx.files.internal("fuentes/fuente.fnt"));
        fuente.getData().setScale(1.5f);
        fuente.setColor(Color.BLACK);
    }

    public static void liberarTexturas() {
        fuente.dispose();
    }
}
