package io.github.juegocolor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

public class Dedo extends Personaje {
    private int direccion = 0;
    private final int velocidad = 50;
    private int vidas = 3 ;

    private final Texture textura = new Texture("dedo.png");

    private final Rectangle hitbox;

    public Rectangle getHitbox() {
        return hitbox;
    }

    public Dedo(float x, float y) {
        setX(x);
        setY(y);

        hitbox = new Rectangle(
            getX(),
            getY(),
            textura.getWidth(),
            textura.getHeight()
        );

        InputManager.getInstance().onWUp = () -> direccion = Gdx.input.isKeyPressed(Input.Keys.S) ? -1 : 0;
        InputManager.getInstance().onSUp = () -> direccion = Gdx.input.isKeyPressed(Input.Keys.W) ? 1 : 0;
        InputManager.getInstance().onWDown = () -> direccion = 1;
        InputManager.getInstance().onSDown = () -> direccion = -1;
    }

    public void restarVida(){
        vidas--;
        if (vidas <= 0){
            System.out.println("Perdiste");
            Gdx.app.exit();
        }
    }

    public int getVidas(){
        return vidas;
    }

    @Override
    public void update(float deltaTime) {
        setY(getY() + direccion * velocidad * deltaTime);
        hitbox.setCenter(
            getX(),
            getY()
        );
    }

    @Override
    public void render(SpriteBatch batch, ShapeRenderer shapeRenderer) {
        batch.draw(textura, getX() - textura.getWidth() / 2f, getY() - textura.getHeight() / 2f);

        if (ConfMundo.DEBUG) {
            shapeRenderer.rect(
                hitbox.x,
                hitbox.y,
                hitbox.width,
                hitbox.height
            );
        }
    }
}
