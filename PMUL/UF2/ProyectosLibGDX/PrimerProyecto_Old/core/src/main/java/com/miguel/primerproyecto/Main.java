package com.miguel.primerproyecto;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

import java.util.Random;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends ApplicationAdapter {
    private SpriteBatch batch;
    private float stateTime;
    private Sound sound;
    private float intervalo = 1.0f;
    private int r,v,a;
    private Random random;

    @Override
    public void create() {
        stateTime = 0;
        random = new Random();

        batch = new SpriteBatch();
        sound = Gdx.audio.newSound(Gdx.files.internal("beep.mp3"));
    }

    enum color {
        RED, GREEN, BLUE
    }

    @Override
    public void render() {
        float delta = Gdx.graphics.getDeltaTime();
        float intervalo = 1.0f;

        ScreenUtils.clear(0, 225, 0, 1);
        batch.begin();

        // Aquí puedes reproducir el sonido
        if (Gdx.input.justTouched() || stateTime > intervalo) {
            sound.play();
        }

        if (Gdx.input.isKeyJustPressed(Input.Keys.ANY_KEY)) {
            sound.play();
        }

        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        sound.dispose();
    }
}
