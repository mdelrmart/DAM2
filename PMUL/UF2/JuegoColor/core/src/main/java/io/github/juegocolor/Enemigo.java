package io.github.juegocolor;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;

public class Enemigo extends Personaje {
    private int direccion = -1;
    private final int velocidad = 30;
    private float radius = 10;

    private final Circle hitboxCirculo;
    private final Dedo dedo;

    private boolean eliminado = false;

    public Enemigo(float x, float y, Dedo dedo) {
        setX(x);
        setY(y);

        this.dedo = dedo;

        hitboxCirculo = new Circle(
            getX(),
            getY(),
            radius
        );
    }

    public boolean isEliminado() {
        return eliminado;
    }

    @Override
    public void update(float deltaTime) {
        setX(getX() + direccion * velocidad * deltaTime);
        hitboxCirculo.setPosition(
            getX(),
            getY()
        );

        if (Intersector.overlaps(hitboxCirculo, dedo.getHitbox())) {
            System.out.println("Colisión detectada");
            dedo.restarVida();
            eliminado = true;
        }
    }

    @Override
    public void render(SpriteBatch batch, ShapeRenderer shapeRenderer) {

        shapeRenderer.circle(
            getX(),
            getY(),
            radius
        );

        if (ConfMundo.DEBUG) {
            shapeRenderer.circle(
                hitboxCirculo.x,
                hitboxCirculo.y,
                radius - 2
            );
        }
    }
}
