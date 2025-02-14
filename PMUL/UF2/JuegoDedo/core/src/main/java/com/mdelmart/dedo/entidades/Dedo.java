package com.mdelmart.dedo.entidades;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.mdelmart.dedo.Assets;
import com.mdelmart.dedo.Mundo;

public class Dedo extends Personaje {
    Rectangle hitbox;
    public int hiperespacios;
    public boolean hiperespacioActivo;
    float tiempoEnHiperespacio;

    public Dedo() {
        x = 0;
        y = Mundo.ALTO / 2;
        velocidad = 200;
        estado = Estado.PARADO;
        alto = 45;
        ancho = 65;
        hitbox = new Rectangle(x, y, ancho, alto);
        vidas = Mundo.VIDAS_DEDO;
        hiperespacios = Mundo.HIPERESPACIOS;
        hiperespacioActivo = false;
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
        if (Mundo.DEBUG) {
            sr.rect(hitbox.x, hitbox.y, hitbox.width, hitbox.height);
        }

        if (!hiperespacioActivo) {
            sb.draw(Assets.dedo, x, y, ancho, alto);
        }
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
    }

    public Rectangle getHitbox() {
        return hitbox;
    }

    public void activarHiperespacio() {
        if (hiperespacioActivo) {
            return;
        }

        if (hiperespacios > 0) {
            hiperespacios--;
            hiperespacioActivo = true;
        }
    }

    public void comprobarHiperespacio(float delta) {
        if (hiperespacioActivo) {
            tiempoEnHiperespacio += delta;
            if (tiempoEnHiperespacio >= Mundo.TIEMPO_HIPERESPACIO) {
                hiperespacioActivo = false;
                tiempoEnHiperespacio = 0;
            }
        }
    }

}
