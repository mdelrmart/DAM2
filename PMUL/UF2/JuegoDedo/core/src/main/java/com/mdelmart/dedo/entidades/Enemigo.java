package com.mdelmart.dedo.entidades;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.mdelmart.dedo.Assets;
import com.mdelmart.dedo.Mundo;

public class Enemigo extends Personaje {
    public Boolean haColisionado = false;

    Color color;

    public Enemigo() {
        ancho = 10f;
        alto = 5f;

        x = Mundo.ANCHO + ancho;
        y = Mundo.random.nextInt((int) (Mundo.ALTO - alto * 2) - (int) Mundo.ALTURA_SEPARADOR) + (Mundo.ALTURA_SEPARADOR + 2);
        velocidad = 130;

        estado = Estado.ATRAS;

        vidas = Mundo.random.nextInt(Mundo.MAX_VIDAS_ENEMIGO - Mundo.MIN_VIDAS_ENEMIGO) + Mundo.MIN_VIDAS_ENEMIGO;

        switch (Mundo.random.nextInt(3)) {
            case 0:
                color = Color.RED;
                break;
            case 1:
                color = Color.YELLOW;
                break;
            case 2:
                color = Color.BLUE;
                break;
        }
    }

    public void actualiza(float delta) {
        if (estado == Estado.ATRAS) {
            x -= velocidad * delta;
        }
        if (x < 0 - ancho) {
            Mundo.enemigos.removeValue(this, true);
            Mundo.dedo.quitarVida();
        }
    }

    public void dibuja(SpriteBatch sb, ShapeRenderer sr) {
        sr.setColor(color);
    }

    public void quitarVida() {
        vidas--;
        if (vidas <= 0) {
            eliminar();
        }
    }

    public void eliminar() {
        Mundo.enemigos.removeValue(this, true);
        Mundo.puntos++;
    }

    public void colision() {
        haColisionado = true;
    }

    public Boolean getHaColisionado() {
        return haColisionado;
    }

}
