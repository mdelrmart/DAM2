package com.mdelmart.insectos;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import java.util.HashMap;
import java.util.Map;

public class Assets {
    public static final String[] NOMBRES_TEXTURAS_BICHOS = {
        "ant.png", "bed-bug.png", "bee.png",
        "fly.png", "green-bug.png" , "ladybird.png",
        "ladybug.png", "mite.png", "mosquito.png"
    };

    public static Map<String, Texture> texturasInsectos = new HashMap<>();
    public static BitmapFont fuente;

    public static void cargarTexturas() {
        for (String nombreFichero : NOMBRES_TEXTURAS_BICHOS) {
            texturasInsectos.put(nombreFichero, new Texture(Gdx.files.internal("insectos/" + nombreFichero)));
        }

        fuente = new BitmapFont(Gdx.files.internal("fuentes/fuente.fnt"));
        fuente.getData().setScale(1.5f);
        fuente.setColor(Color.BLACK);
    }

    public static void liberarTexturas() {
        for (Texture textura : texturasInsectos.values()) {
            textura.dispose();
        }
        fuente.dispose();
    }

    public static Texture getTexture(int numTextura) {
        return texturasInsectos.get(NOMBRES_TEXTURAS_BICHOS[numTextura]);
    }
}
