package com.mdelmart.pescador;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

import static com.mdelmart.pescador.Mundo.pescador;
import static com.mdelmart.pescador.Mundo.random;

public class Pez extends Personaje {
    Rectangle hitbox;
    TextureRegion pezTexture;
    boolean pescado;

    public Pez() {
        ancho = 100f;
        alto = 50f;

        x = random.nextInt(2);

        if (random.nextInt(2) == 0) {
            estado = Estado.IZQUIERDA;
            x = 0 - ancho;
        } else {
            estado = Estado.DERECHA;
            x = Mundo.ANCHO;
        }

        y = Mundo.random.nextInt((int) (Mundo.LIMITE_MAR - alto * 2));
        velocidad = 160;

        pescado = false;

        hitbox = new Rectangle(x, y, ancho, alto);

        // Asigna la textura del pez y la voltea si va a la izquierda
        pezTexture = new TextureRegion(Assets.pezAzul);
        if (estado == Estado.IZQUIERDA) {
            pezTexture.flip(true, false);
        }
    }

    public void dibuja(SpriteBatch sb, ShapeRenderer sr) {
        sb.draw(pezTexture, x, y, ancho, alto);

        if (Mundo.DEBUG) {
            sr.rect(hitbox.x, hitbox.y, hitbox.width, hitbox.height);
        }
    }

    public void actualiza(float delta) {
        switch (estado) {
            case DERECHA:
                x -= velocidad * delta;
                break;
            case IZQUIERDA:
                x += velocidad * delta;
                break;
        }

        if (x < 0 - Mundo.ANCHO) {
            Mundo.peces.removeValue(this, true);
        }

        hitbox.x = x;
    }

    public void pescar() {
        pescado = true;
        pescador.pecesPescados++;
    }
}
