package com.mdelmart.pescador;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

// Clase pasada por David
public class Assets {
    public static Texture fondo;
    public static BitmapFont fuente;
    public static TextureAtlas atlas;
    public static TextureRegion pescador, anzuelo, sedal, pezAzul;
    public static Animation aniPezAmarillo, aniPezLila;

    public static Sound captura, finDelJuego;
    public static Music musicaDeFondo;

    public static void cargar() {
        fondo = new Texture(Gdx.files.internal("fondo.jpg"));

        captura = Gdx.audio.newSound(Gdx.files.internal("captura.mp3"));
        finDelJuego = Gdx.audio.newSound(Gdx.files.internal("finDelJuego.mp3"));
        musicaDeFondo = Gdx.audio.newMusic(Gdx.files.internal("fondo.mp3"));

        //fuente = new BitmapFont(Gdx.files.internal("fuentes/fuente.fnt"));
        fuente = new BitmapFont();
        fuente.getData().setScale(2.0f);
        fuente.setColor(Color.BLACK);

        atlas = new TextureAtlas(Gdx.files.internal("atlas.atlas"));
        pescador = atlas.findRegion("pescador");
        anzuelo = atlas.findRegion("anzuelo");
        sedal = atlas.findRegion("punto");

        pezAzul = atlas.findRegion("pezAzul");

        TextureRegion pezLila = atlas.findRegion("pez_lila");
        TextureRegion[][] framesPezLila = pezLila.split(384 / 4, 96 / 1);
        aniPezLila = new Animation(.15f, framesPezLila[0]);

        TextureRegion pezAmarillo = atlas.findRegion("pez_amarillo");
        TextureRegion[][] framesPezAmarillo = pezAmarillo.split(288 / 3, 192 / 2);
        int filasPezAmarillo = framesPezAmarillo.length;
        int columnasPezAmarillo = framesPezAmarillo[0].length;
        TextureRegion[] framesPA = new TextureRegion[filasPezAmarillo * columnasPezAmarillo];
        int numFrame = 0;
        for (int fila = 0; fila < filasPezAmarillo; fila++)
            for (int columna = 0; columna < columnasPezAmarillo; columna++)
                framesPA[numFrame++] = framesPezAmarillo[fila][columna];
        aniPezAmarillo = new Animation(.15f, framesPA);
    }

    public static void liberar() {
        captura.dispose();
        musicaDeFondo.dispose();
        finDelJuego.dispose();
        fondo.dispose();
        fuente.dispose();
        atlas.dispose();
    }
}
