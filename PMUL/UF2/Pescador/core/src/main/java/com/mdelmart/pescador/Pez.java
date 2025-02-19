package com.mdelmart.pescador;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

import static com.mdelmart.pescador.Mundo.*;

public class Pez extends Personaje {
    Rectangle hitbox;
    TextureRegion pezTexture;
    boolean pescado;

    boolean rotado = false; // Nueva variable para controlar la rotación

    public Pez() {
        ancho = 100f;
        alto = 50f;
        velocidad = 160;
        pescado = false;

        x = random.nextInt(2);

        if (random.nextInt(2) == 0) {
            estado = Estado.IZQUIERDA;
            x = 0 - ancho;
        } else {
            estado = Estado.DERECHA;
            x = Mundo.ANCHO;
        }

        y = Mundo.random.nextInt((int) (Mundo.LIMITE_MAR - alto * 2));

        hitbox = new Rectangle(x, y, ancho, alto);

        // Asigna la textura del pez y la voltea si va a la izquierda
        pezTexture = new TextureRegion(Assets.pezAzul);
        if (estado == Estado.IZQUIERDA) {
            pezTexture.flip(true, false);
        }
    }

    public void dibuja(SpriteBatch sb, ShapeRenderer sr) {
        if (rotado) {
            if (estado == Estado.IZQUIERDA) {
                sb.draw(pezTexture,
                    x - 50, y - 30,            // Posición en pantalla
                    ancho / 2, alto / 2,     // Punto de origen para la rotación (centro del pez)
                    ancho, alto,                    // Tamaño
                    1, 1,                    // Escala
                    90);                            // Rotación en grados (90° sentido horario)
            } else if (estado == Estado.DERECHA) {
                sb.draw(pezTexture,
                    x - 30, y - 30,            // Posición en pantalla
                    ancho / 2, alto / 2,     // Punto de origen para la rotación (centro del pez)
                    ancho, alto,                    // Tamaño
                    1, 1,                    // Escala
                    -90);                           // Rotación en grados (90° sentido horario)
            }
        } else {
            sb.draw(pezTexture, x, y, ancho, alto);
        }

        if (Mundo.DEBUG) {
            if (!pescado) {
                sr.rect(hitbox.x, hitbox.y, hitbox.width, hitbox.height);
            }
        }
    }

    public void seguirAnzuelo(float anzueloX, float anzueloY) {
        this.x = anzueloX;
        this.y = anzueloY;
    }

    public void pescado() {
        pescado = true;
        rotado = true;  // Activar rotación cuando el pez es pescado
    }

    public void actualiza(float delta) {
        if (pescado) {
            seguirAnzuelo(Mundo.anzuelo.x, Mundo.anzuelo.y);
            if (anzuelo.estado == Estado.PARADO) {
                Mundo.peces.removeValue(this, true);
                pescador.pecesPescados++;
            }
        } else {
            switch (estado) {
                case DERECHA:
                    x -= velocidad * delta;
                    break;
                case IZQUIERDA:
                    x += velocidad * delta;
                    break;
            }
        }

        hitbox.set(x + (estado == Estado.IZQUIERDA ? 80 : 0), y, ancho - 80, alto - 30);

        if (x < 0 - Mundo.ANCHO) {
            Mundo.peces.removeValue(this, true);
        }
    }
}
