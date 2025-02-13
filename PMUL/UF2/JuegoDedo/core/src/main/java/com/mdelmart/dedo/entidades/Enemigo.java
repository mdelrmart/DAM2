package com.mdelmart.dedo.entidades;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.mdelmart.dedo.Assets;
import com.mdelmart.dedo.Mundo;

public class Enemigo extends Personaje {
    Circle hitbox;
    Boolean haColisionado = false;

    public Enemigo() {
        ancho = 10f;
        alto = 5f;

        x = Mundo.ANCHO + ancho;
        y = Mundo.random.nextInt((int) (Mundo.ALTO - alto * 2) - (int) Mundo.ALTURA_SEPARADOR) + (Mundo.ALTURA_SEPARADOR + 2);
        velocidad = 130;

        estado = Estado.ATRAS;

        // +2 para que se vea un poco más grande que el círculo de colisión
        hitbox = new Circle(x, y, ancho + 2);

        vidas = Mundo.random.nextInt(Mundo.MAX_VIDAS_ENEMIGO - Mundo.MIN_VIDAS_ENEMIGO) + Mundo.MIN_VIDAS_ENEMIGO;
    }

    public void actualiza(float delta) {
        if (estado == Estado.ATRAS) {
            x -= velocidad * delta;
        }
        if (x < 0 - ancho) {
            Mundo.enemigos.removeValue(this, true);
            Mundo.dedo.quitarVida();
        }

        hitbox.x = x;
    }

    public void dibuja(SpriteBatch sb, ShapeRenderer sr) {
        sr.circle(x, y, ancho);
        sr.circle(hitbox.x, hitbox.y, hitbox.radius);
        Assets.fuente.draw(sb, "" + vidas, x - 5, y + 5);
    }

    public void quitarVida() {
        vidas--;
        if (vidas <= 0) {
            eliminar();
        }
    }

    public void eliminar() {
        Mundo.enemigos.removeValue(this, true);
    }

    public void colision() {
        haColisionado = true;
    }

    public Circle getHitbox() {
        return hitbox;
    }

    public Boolean getHaColisionado() {
        return haColisionado;
    }

}
