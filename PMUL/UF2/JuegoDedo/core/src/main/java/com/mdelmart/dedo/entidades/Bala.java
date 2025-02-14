package com.mdelmart.dedo.entidades;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.mdelmart.dedo.Mundo;

public class Bala extends Personaje{

    Circle hitbox;

    public Bala() {
        ancho = 5f;
        alto = 5f;
        x = Mundo.dedo.x + Mundo.dedo.ancho;
        y = Mundo.dedo.y + 30;
        velocidad = 300;
        estado = Estado.ADELANTE;
        hitbox = new Circle(x, y, ancho + 2);
    }

    public void actualiza(float delta) {
        x += velocidad * delta;
        if (x > Mundo.ANCHO) {
            Mundo.balas.removeValue(this, true);
        }

        hitbox.x = x;
    }

    public void dibuja(ShapeRenderer sr) {
        sr.circle(x, y, ancho);
        
        if (Mundo.DEBUG) {
            sr.circle(hitbox.x, hitbox.y, hitbox.radius);
        }
    }

    public Circle getHitbox() {
        return hitbox;
    }
}
