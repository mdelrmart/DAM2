package com.mdelmart.crecer;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

public class Enemigo extends Personaje {

    enum PosicionInicial {
        IZQUIERDA, DERECHA
    }

    private PosicionInicial posicionInicial;

    public Enemigo() {
        posicionInicial = Mundo.random.nextInt(2) == 0 ? PosicionInicial.IZQUIERDA : PosicionInicial.DERECHA;

        ancho = 50;
        alto = 50;

        switch (posicionInicial) {
            case IZQUIERDA:
                x = 0 - ancho;
                estado = Estado.ADELANTE;
                break;
            case DERECHA:
                x = Mundo.ANCHO;
                estado = Estado.ATRAS;
                break;
        }

        //int randomNumber = random.nextInt(max + 1 - min) + min;
        y = Mundo.random.nextInt((int) (Mundo.ALTO - alto - Mundo.ALTURA_SEPARADOR)) + Mundo.ALTURA_SEPARADOR;

        velocidad = 160f;

        switch (Mundo.random.nextInt(3)) {
            case 0:
                tipo = Tipo.CRUZ;
                hitbox = new Rectangle(x, y, ancho, alto);
                break;
            case 1:
                tipo = Tipo.CIRCULO;
                hitbox = new Rectangle(x - ancho / 2, y - ancho / 2, ancho, alto);
                break;
            case 2:
                tipo = Tipo.RECTANGULO;
                hitbox = new Rectangle(x, y, ancho, alto);
                break;
        }
    }

    @Override
    public void actualiza(float delta) {
        switch (estado) {
            case ATRAS:
                if (x >= 0 - ancho) {
                    x -= velocidad * delta;
                } else {
                    Mundo.eliminarEnemigo(this);
                }
                break;
            case ADELANTE:
                if (x <= Mundo.ANCHO + ancho) {
                    x += velocidad * delta;
                } else {
                    Mundo.eliminarEnemigo(this);
                }
                break;
        }

        if (tipo == Tipo.CIRCULO) {
            // Para el círculo, la hitbox debe seguir centrada
            hitbox.x = x - ancho / 2;
            hitbox.y = y - ancho / 2;
        } else {
            hitbox.x = x;
            hitbox.y = y;
        }
    }

    @Override
    public void dibuja(SpriteBatch sb, ShapeRenderer sr) {
        super.dibuja(sb,sr);
    }
}
