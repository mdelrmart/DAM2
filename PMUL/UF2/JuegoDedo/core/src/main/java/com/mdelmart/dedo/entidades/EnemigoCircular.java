package com.mdelmart.dedo.entidades;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.mdelmart.dedo.Assets;
import com.mdelmart.dedo.Mundo;

public class EnemigoCircular extends Enemigo {
    Circle hitbox;

    public EnemigoCircular() {
        super();

        // +2 para que se vea un poco más grande que el círculo de colisión
        hitbox = new Circle(x, y, ancho + 2);
    }

    @Override
    public void actualiza(float delta) {
        super.actualiza(delta);
        hitbox.x = x;
    }

    @Override
    public void dibuja(SpriteBatch sb, ShapeRenderer sr) {
        super.dibuja(sb, sr);
        sr.circle(x, y, ancho);
        if (Mundo.DEBUG) {
            sr.circle(hitbox.x, hitbox.y, hitbox.radius);
        }
        Assets.fuente.draw(sb, "" + vidas, x - 5, y + 5);
    }

    public Circle getHitbox() {
        return hitbox;
    }
}
