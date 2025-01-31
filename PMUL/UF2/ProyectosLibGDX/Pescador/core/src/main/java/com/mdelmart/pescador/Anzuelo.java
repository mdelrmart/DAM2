package com.mdelmart.pescador;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Anzuelo extends Personaje {
    private final static float desplazamientoAnzuelo = 42;
    private final static float posY = 158;
    private static final int velocidad=80;
    public Anzuelo() { super(0,posY,10,10,velocidad, Estado.PARADO); }
    public void actualiza(float delta) {
        switch (estado) {
            case ADELANTE:
                y += velocidad * delta;
                if(y>posY) para();
                break;
            case ATRAS:
                y -= velocidad * delta;
                if(y<0) adelante();
                break;
        }
        x=hitBox.x= Mundo.pescador.x+desplazamientoAnzuelo;
        hitBox.y=y;
    }
    public void dibuja(SpriteBatch sb, ShapeRenderer sr) {
        if(Mundo.MODO_DEBUG) sr.rect(x,y,ancho,alto);
        sb.draw(Assets.anzuelo,x,y,ancho,alto);
    }
}
