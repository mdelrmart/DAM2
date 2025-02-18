package com.mdelmart.pescador;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.utils.Array;

import java.util.Random;

public class Mundo {
    public static float ANCHO = 1024;
    public static float ALTO = 768;
    public static boolean DEBUG = true;
    public static final float LIMITE_MAR = Mundo.ALTO / 1.54f;
    public static final float TIEMPO_ENTRE_PECES = 1.5f;
    public static final int TIEMPO_INICIAL_JUEGO = 60;

    public static Pescador pescador = new Pescador();
    public static Anzuelo anzuelo = new Anzuelo();

    public static Array<Pez> peces = new Array<>();

    public static Random random = new Random();

    public static void crearPez() {
        peces.add(new Pez());
    }

    public static void dibujarPeces(SpriteBatch sb, ShapeRenderer sr) {
        for (Pez pez : peces) {
            pez.dibuja(sb, sr);
        }
    }

    public static void actualizarPeces(float delta) {
        for (Pez pez : peces) {
            pez.actualiza(delta);
        }
    }

    public static void comprobarColisiones() {
        if (anzuelo.sube()) {
            for (Pez pez : peces) {
                if (Intersector.overlaps(pez.hitbox, anzuelo.hitbox)) {
                    pez.pescar();
                    peces.removeValue(pez, true);
                }
            }
        }
    }
}
