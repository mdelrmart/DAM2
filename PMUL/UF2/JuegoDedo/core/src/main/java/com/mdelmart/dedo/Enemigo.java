package com.mdelmart.dedo;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;

public class Enemigo extends Personaje {

    Circle hitbox;
    Boolean haColisionado = false;

    public Enemigo() {
        ancho = 10f;
        alto = 5f;

        x = Mundo.ANCHO + ancho;
        y = Mundo.random.nextInt((int) (Mundo.ALTO - alto * 2) - (int) Mundo.ALTURA_SEPARADOR) + Mundo.ALTURA_SEPARADOR;
        velocidad = 160;

        estado = Estado.ATRAS;

        // +2 para que se vea
        hitbox = new Circle(x, y, ancho + 2);

        vidas = Mundo.random.nextInt(Mundo.MAX_VIDAS_ENEMIGO - Mundo.MIN_VIDAS_ENEMIGO) + Mundo.MIN_VIDAS_ENEMIGO;
    }

    public void actualiza(float delta) {
        if (estado == Estado.ATRAS) {
            x -= velocidad * delta;
        }
        if (x < 0 - ancho) {
            Mundo.enemigos.removeValue(this, true);
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
            Mundo.enemigos.removeValue(this, true);
        }
    }

    public void colision() {
        haColisionado = true;
    }

}
