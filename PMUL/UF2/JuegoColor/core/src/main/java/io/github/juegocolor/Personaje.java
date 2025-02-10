package io.github.juegocolor;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public abstract class Personaje {
    private float x;
    private float y;

    public abstract void update(float deltaTime);
    public abstract void render(SpriteBatch batch, ShapeRenderer shapeRenderer);

    protected float getX() {
        return x;
    }

    protected void setX(float x) {
        this.x = x;
    }

    protected float getY() {
        return y;
    }

    protected void setY(float y) {
        this.y = y;
    }
}
