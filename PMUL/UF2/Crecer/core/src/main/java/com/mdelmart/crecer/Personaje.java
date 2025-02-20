package com.mdelmart.crecer;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

public abstract class Personaje {

    protected float x, y, velocidad, ancho, alto;
    protected Rectangle hitbox;
    protected Tipo tipo;
    protected Estado estado;

    enum Estado {
        ADELANTE, ATRAS, PARADO
    }

    enum Tipo {
        TRIANGULO, CIRCULO, RECTANGULO, CRUZ
    }

    public abstract void actualiza(float delta);

    public void dibuja(SpriteBatch sb, ShapeRenderer sr) {
        switch (tipo) {
            case CIRCULO:
                sr.circle(x, y, ancho / 2);
                break;
            case RECTANGULO:
                sr.rect(x, y, ancho, alto);
                break;
            case CRUZ:
                sr.line(x, y, x + ancho, y + alto);
                sr.line(x + ancho, y, x, y + alto);
                break;
        }

        if (Mundo.DEBUG) {
            sr.rect(hitbox.x, hitbox.y, hitbox.width, hitbox.height);
        }

    }


}
