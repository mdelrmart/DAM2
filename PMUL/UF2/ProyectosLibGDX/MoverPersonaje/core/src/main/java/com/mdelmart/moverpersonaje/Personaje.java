package com.mdelmart.moverpersonaje;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Personaje {
    float x, y;
    int velocidad, ancho, alto;
    boolean haciaDelante;
    boolean haciaArriba;
    int sentidoH;
    int sentidoV;
    Texture textura;

    public Personaje() {
    }

    public Personaje(float x, float y, int velocidad, int ancho, int alto, Texture textura) {
        this.x = x;
        this.y = y;
        this.velocidad = velocidad;
        this.ancho = ancho;
        this.alto = alto;
        this.textura = textura;
    }

    public void actualizar(float delta) {
        if (x > 0 && x < Gdx.graphics.getWidth() - ancho) {
            x += velocidad * delta * sentidoH;
        }

        if (y > 0 && x < Gdx.graphics.getHeight() - alto) {
            y += velocidad * delta * sentidoV;
        }
    }

    public void setSentidoH(int sentido) {
        this.sentidoH = sentido;
    }

    public void setSentidoV(int sentido) {
        this.sentidoV = sentido;
    }

    public void cambiaDireccion() {
        ancho = -ancho;
        x -= ancho;
        haciaDelante = !haciaDelante;
    }

    public boolean vaHaciaDelante() {
        return haciaDelante;
    }

    public boolean vaHaciaArriba() {
        return haciaArriba;
    }

    public void dibujar(SpriteBatch sp) {
        sp.draw(textura, x, y, ancho, alto);
    }


}
