package io.github.dedo;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;

public class InputManager implements InputProcessor {
    private static InputManager instance;

    public static InputManager getInstance() {
        if (instance == null) {
            instance = new InputManager();
        }

        return instance;
    }

    private InputManager() {
    }

    public Runnable onWDown = () -> {
    };
    public Runnable onSDown = () -> {
    };
    public Runnable onWUp = () -> {
    };
    public Runnable onSUp = () -> {
    };

    @Override
    public boolean keyDown(int i) {
        if (i == Input.Keys.W) {
            onWDown.run();
            return true;
        }

        if (i == Input.Keys.S) {
            onSDown.run();
            return true;
        }

        return false;
    }

    @Override
    public boolean keyUp(int i) {
        if (i == Input.Keys.W) {
            onWUp.run();
            return true;
        }

        if (i == Input.Keys.S) {
            onSUp.run();
            return true;
        }

        return false;
    }

    @Override
    public boolean keyTyped(char c) {
        return false;
    }

    @Override
    public boolean touchDown(int i, int i1, int i2, int i3) {
        return false;
    }

    @Override
    public boolean touchUp(int i, int i1, int i2, int i3) {
        return false;
    }

    @Override
    public boolean touchCancelled(int i, int i1, int i2, int i3) {
        return false;
    }

    @Override
    public boolean touchDragged(int i, int i1, int i2) {
        return false;
    }

    @Override
    public boolean mouseMoved(int i, int i1) {
        return false;
    }

    @Override
    public boolean scrolled(float v, float v1) {
        return false;
    }
}
