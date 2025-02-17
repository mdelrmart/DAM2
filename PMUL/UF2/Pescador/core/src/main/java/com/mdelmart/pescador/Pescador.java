package com.mdelmart.pescador;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

public class Pescador extends Personaje {
    Rectangle hitbox;

    public Pescador() {
        x = Mundo.ANCHO / 2 - 50;
        y = Mundo.LIMITE_MAR;
        velocidad = 160;
        estado = Estado.PARADO;
        alto = 150;
        ancho = 170;
        hitbox = new Rectangle(x, y, ancho, alto);
    }

    public void actualiza(float delta) {
        if(estado == Estado.DERECHA) {
            if (x <= Mundo.ANCHO - ancho) {
                x += velocidad * delta;
            }
        }
        if (estado == Estado.IZQUIERDA) {
            if (x >= 0) {
                x -= velocidad * delta;
            }
        }

    }

    public void dibuja(SpriteBatch sb, ShapeRenderer sr) {
        sb.draw(Assets.pescador, x, y, ancho, alto);
    }

    public void derecha() {
        estado = Estado.DERECHA;
    }

    public void izquierda() {
        estado = Estado.IZQUIERDA;
    }

    public void parar() {
        estado = Estado.PARADO;
    }

}
