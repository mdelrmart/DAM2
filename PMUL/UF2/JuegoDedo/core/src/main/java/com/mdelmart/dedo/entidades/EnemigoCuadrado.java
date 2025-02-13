package com.mdelmart.dedo.entidades;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.mdelmart.dedo.Assets;
import com.mdelmart.dedo.Mundo;

public class EnemigoCuadrado extends Enemigo {
    Rectangle hitbox;

    public EnemigoCuadrado() {
        super();
        ancho = 20;
        alto = 20;

        // +2 para que se vea un poco más grande que el círculo de colisión
        hitbox = new Rectangle(x, y, ancho + 2, alto +2);
    }

    @Override
    public void actualiza(float delta) {
        super.actualiza(delta);
        hitbox.x = x;
    }

    @Override
    public void dibuja(SpriteBatch sb, ShapeRenderer sr) {
        super.dibuja(sb, sr);
        sr.rect(x, y, ancho, alto);
        if (Mundo.DEBUG) {
            sr.rect(hitbox.x, hitbox.y, hitbox.width, hitbox.height);
        }
        Assets.fuente.draw(sb, "" + vidas, x + 10, y + 15);
    }

    public Rectangle getHitbox() {
        return hitbox;
    }
}
