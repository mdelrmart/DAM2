package com.mdelmart.dedo;

import com.badlogic.gdx.math.Rectangle;

public class Personaje {
    protected float x, y;
    protected float velocidad;
    protected float ancho, alto;
    protected int vidas;

    protected Rectangle rectangulo;

    enum Estado {
        ADELANTE, ATRAS, PARADO
    }

    protected Estado estado;
}
