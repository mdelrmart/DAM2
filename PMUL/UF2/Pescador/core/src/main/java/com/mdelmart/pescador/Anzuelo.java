package com.mdelmart.pescador;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

import static com.mdelmart.pescador.Mundo.anzuelo;
import static com.mdelmart.pescador.Mundo.pescador;

public class Anzuelo extends Personaje {
    Rectangle hitbox;

    public Anzuelo() {
        x = Mundo.ANCHO / 2 - 333;
        y = (Mundo.ALTO / 2) + 200;
        velocidad = 160;
        estado = Estado.PARADO;
        alto = 30;
        ancho = 20;
        hitbox = new Rectangle(x, y, ancho, alto);
    }

    public void actualiza(float delta) {
        if (pescador.estado == Estado.DERECHA) {
            x = pescador.x + 145;
        }

        if (pescador.estado == Estado.IZQUIERDA) {
            x = pescador.x + 145;
        }

        if (estado == Estado.ABAJO) {
            // Si el anzuelo no ha llegado al fondo de la pantalla (y mayor que cero), sigue bajando
            if (y > 0) {
                y -= velocidad * delta;
            } else {
                // En caso contrario, lo detenemos
                y = 0; // Asegura que el anzuelo no se salga de la pantalla
            }
        }

        if (estado == Estado.ARRIBA) {
            // Verifica que el anzuelo no suba más allá de su posición inicial
            if (y < (Mundo.ALTO / 2) + 200) {
                y += velocidad * delta;
            } else {
                y = (Mundo.ALTO / 2) + 200; // Mantiene el anzuelo en su posición máxima
            }
        }

        hitbox.x = x;
        hitbox.y = y;
    }

    public void abajo() {
        estado = Estado.ABAJO;
    }

    public void arriba() {
        estado = Estado.ARRIBA;
    }

    public void dibuja(SpriteBatch sb, ShapeRenderer sr) {
        sb.draw(Assets.anzuelo, x, y, ancho, alto);

        if (Mundo.DEBUG) {
            sr.rect(hitbox.x, hitbox.y, hitbox.width, hitbox.height);
        }
    }

}
