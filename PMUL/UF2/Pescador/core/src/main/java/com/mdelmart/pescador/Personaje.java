package com.mdelmart.pescador;

import com.badlogic.gdx.math.Rectangle;

public class Personaje {
    protected float x, y;
    protected float velocidad;
    protected float ancho, alto;

    protected Rectangle rectangulo;

    enum Estado {
        DERECHA, IZQUIERDA, PARADO, ABAJO, ARRIBA
    }

    protected Estado estado;
}
