package com.miguel.primerproyecto;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Main extends ApplicationAdapter {
    private float stateTime;
    SpriteBatch sb;
    BitmapFont fuente;
    Texture textureCorredor;
    Personaje personaje;

    public Main() {
    }

    public void create() {
        this.stateTime = 0.0F;
        this.sb = new SpriteBatch();

        this.fuente = new BitmapFont();
        this.fuente.getData().setScale(5.0F);

        this.textureCorredor = new Texture("corredor.png");
        this.personaje = new Personaje();

        Gdx.input.setInputProcessor(new ProcesadorDeEntrada(this.personaje));
    }

    public void render() {
        //ScreenUtils.clear(255, 255, 255, 1);
        ScreenUtils.clear(Color.FOREST);
        float delta = Gdx.graphics.getDeltaTime();
        this.stateTime += delta;
        this.personaje.actualiza();
        this.sb.begin();
        this.personaje.dibuja(this.sb, this.textureCorredor);
        this.sb.end();
    }
}
