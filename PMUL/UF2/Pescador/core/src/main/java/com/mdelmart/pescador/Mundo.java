package com.mdelmart.pescador;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Mundo {
    public static float ANCHO = 1024;
    public static float ALTO = 768;
    public static boolean DEBUG = true;

    public static final float LIMITE_MAR = Mundo.ALTO / 1.54f;

    public static Pescador pescador = new Pescador();
    public static Anzuelo anzuelo = new Anzuelo();
}
