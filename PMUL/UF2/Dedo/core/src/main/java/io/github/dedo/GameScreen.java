package io.github.dedo;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.Random;

public class GameScreen implements Screen {
    private final Camera camera = new OrthographicCamera();

    private final SpriteBatch batch = new SpriteBatch();
    private final ShapeRenderer shapeRenderer = new ShapeRenderer();

    private final Array<Personaje> personajes = new Array<>(false, 10);
    public Dedo dedo;

    private BitmapFont font;

    private void eliminarEnemigos() {
        for (int i = personajes.size - 1; i >= 0; i--) {
            if (personajes.get(i) instanceof Enemigo && ((Enemigo) personajes.get(i)).isEliminado()) {
                personajes.removeIndex(i);
            }
        }
    }

    Random random = new Random();

    int altura = 30;

    @Override
    public void show() {
        dedo = new Dedo(-ConfMundo.MUNDO_ANCHO / 2f + 0, 0);

        personajes.add(dedo);

        //personajes.add(new Enemigo(ConfMundo.MUNDO_ANCHO / 2f - 20, 0, dedo));

        for (int i = 0; i < Aleatorio.aleatorio(); i++) {
            personajes.add(new Enemigo(Aleatorio.aleatorio(), Aleatorio.aleatorio(), dedo));
        }

        shapeRenderer.setAutoShapeType(true);

        camera.position.set(0, 0, 0);
        camera.update();
    }

    @Override
    public void render(float deltaTime) {
        ScreenUtils.clear(Color.FOREST);

        for (Personaje personaje : personajes) {
            personaje.update(deltaTime);
        }

        eliminarEnemigos();

        batch.begin();
        shapeRenderer.begin();

        for (Personaje personaje : personajes) {
            personaje.render(batch, shapeRenderer);
        }

        shapeRenderer.end();
        batch.end();

        batch.begin();
        font = new BitmapFont();
        font.draw(batch, "Vidas: " + dedo.getVidas(), -ConfMundo.MUNDO_ANCHO / 2f, ConfMundo.MUNDO_ALTO / 2f - 10);
        batch.end();
    }

    @Override
    public void resize(int width, int height) {
        float aspectRatio = (float) height / width;
        camera.viewportHeight = ConfMundo.MUNDO_ALTO;
        camera.viewportWidth = ConfMundo.MUNDO_ANCHO / aspectRatio;
        camera.update();

        batch.setProjectionMatrix(camera.combined);
        shapeRenderer.setProjectionMatrix(camera.combined);

        // float aspectRatio = (float) height / width;
        // camera.viewportHeight = ConfMundo.WORLD_HEIGHT * aspectRatio;
        // camera.viewportWidth = ConfMundo.WORLD_WIDTH;
        // camera.update();

        // float aspectRatio = (float) height / width;
        // float desiredAspectRatio = ConfMundo.WORLD_HEIGHT / ConfMundo.WORLD_WIDTH;
        // if (aspectRatio < desiredAspectRatio)
        // {
        //     camera.viewportWidth = ConfMundo.WORLD_WIDTH / aspectRatio * desiredAspectRatio;
        //     camera.viewportHeight = ConfMundo.WORLD_HEIGHT;
        // }
        // else
        // {
        //     camera.viewportWidth = ConfMundo.WORLD_WIDTH;
        //     camera.viewportHeight = ConfMundo.WORLD_HEIGHT * aspectRatio / desiredAspectRatio;
        // }
        // camera.update();
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
