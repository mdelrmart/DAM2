package com.mdelmart.dedo;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class Assets {
    public static Texture dedo;
    public static BitmapFont fuente;

    public static void cargar() {
        dedo = new Texture("dedo.png");
        fuente = new BitmapFont(Gdx.files.internal("fuentes/fuente.fnt"));
        fuente.getData().setScale(1f);
        fuente.setColor(Color.BLACK);
    }

    public static void liberar() {
        dedo.dispose();
        fuente.dispose();
    }

}
