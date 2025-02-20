package com.mdelmart.crecer;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

public class Jugador extends Personaje {
    private float tiempoTranscurrido;
    public int colisiones;

    public Jugador() {
        ancho = 50;
        alto = 50;
        x = Mundo.ANCHO / 2 - ancho / 2;
        y = Mundo.ALTO / 2 - alto / 2;
        velocidad = 100f;
        estado = Estado.PARADO;
        colisiones = 0;
        hitbox = new Rectangle(x, y, ancho, alto);
        tipo = Tipo.TRIANGULO;
        tiempoTranscurrido = 0;
    }

    public void sube() {
        estado = Estado.ADELANTE;
    }

    public void baja() {
        estado = Estado.ATRAS;
    }

    public void para() {
        estado = Estado.PARADO;
    }

    public void crecer() {
        if (alto < Mundo.TAMANHO_MAXIMO_JUGADOR) {
            ancho += Mundo.AUMENTO_TAMANHO_JUGADOR;
            alto += Mundo.AUMENTO_TAMANHO_JUGADOR;
            hitbox.width += Mundo.AUMENTO_TAMANHO_JUGADOR;
            hitbox.height += Mundo.AUMENTO_TAMANHO_JUGADOR;
        }

        // Corrige la posición
        x = Mundo.ANCHO/2 - ancho/2;
        hitbox.x = x;

        // Corrige la posición si se sale de la pantalla
        if (y + alto >= Mundo.ALTO) {
            y = Mundo.ALTO - alto;
        }
    }

    @Override
    public void actualiza(float delta) {
        tiempoTranscurrido += delta;
        if (tiempoTranscurrido >= Mundo.INTERVALO_CRECIMIENTO_JUGADOR) {
            crecer();
            tiempoTranscurrido = 0;
        }

        switch (estado) {
            case ATRAS:
                if (y >= Mundo.ALTURA_SEPARADOR) {
                    y -= velocidad * delta;
                }
                break;
            case ADELANTE:
                if (y <= Mundo.ALTO - alto) {
                    y += velocidad * delta;
                }
                break;
        }

        hitbox.y = y;

        //ancho++;
        //alto++;
    }

    @Override
    public void dibuja(SpriteBatch sb, ShapeRenderer sr) {
        switch (tipo) {
            case TRIANGULO:
                sr.triangle(x, y, x + ancho, y, x + ancho / 2, y + alto);
                break;
            case CIRCULO:
                sr.circle(x, y, ancho / 2);
                break;
            case RECTANGULO:
                sr.rect(x, y, ancho, alto);
                break;
            case CRUZ:
                sr.line(x, y, x + ancho, y + alto);
                sr.line(x + ancho, y, x, y + alto);
                break;
        }
        if (Mundo.DEBUG) {
            sr.rect(hitbox.x, hitbox.y, hitbox.width, hitbox.height);
        }
    }

    public void colisionar() {
        colisiones++;
    }

    public void cambiarForma() {
        Tipo[] tipos = Tipo.values();
        int indice = Mundo.random.nextInt(tipos.length);
        tipo = tipos[indice];
    }
}
