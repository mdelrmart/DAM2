package com.mdelmart.dedo;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

import javax.net.ssl.ExtendedSSLSession;

public class Dedo extends Personaje {

    Rectangle hitbox;

    public Dedo() {
        x = 0;
        y = Mundo.ALTO / 2;
        velocidad = 160;
        estado = Estado.PARADO;
        alto = 65;
        ancho = 85;
        hitbox = new Rectangle(x, y, ancho, alto);

        vidas = Mundo.VIDAS_DEDO;
    }

    public void actualiza(float delta) {
        if(estado == Estado.ADELANTE) {
            if (y <= Mundo.ALTO - alto) {
                y += velocidad * delta;
            }
        }
        if (estado == Estado.ATRAS) {
            if (y >= Mundo.ALTURA_SEPARADOR) {
                y -= velocidad * delta;
            }
        }

        hitbox.y = y;
    }

    public void dibuja(SpriteBatch sb, ShapeRenderer sr) {
        sb.draw(Assets.dedo, x, y, ancho, alto);
        sr.rect(hitbox.x, hitbox.y, hitbox.width, hitbox.height);
    }

    public void subir() {
        estado = Estado.ADELANTE;
    }

    public void bajar() {
        estado = Estado.ATRAS;
    }

    public void parar() {
        estado = Estado.PARADO;
    }

    public void quitarVida() {
        vidas--;
        if (vidas <= 0) {
            Gdx.app.exit();
        }
    }
}
