package io.github.juegocolor;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;

public class Main extends Game {
    private static Main instance;

    public static Main getInstance() {
        return instance;
    }

    public Main() {
        if (instance != null) throw new RuntimeException("Ya está instanciada");

        instance = this;
    }

    @Override
    public void create() {
        Gdx.input.setInputProcessor(InputManager.getInstance());

        setScreen(new GameScreen());
    }

    @Override
    public void dispose() {
        getScreen().dispose();
    }
}
