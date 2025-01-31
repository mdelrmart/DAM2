package com.mdelmart.pescador;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Pescador extends Personaje {
    private static final int velocidad=80;
    public Pescador() { super(50,128,50,50,velocidad,Estado.PARADO); }
    @Override public void actualiza(float delta) {
        if(estado==Estado.PARADO) return;
        switch (estado) {
            case ADELANTE:
                x+=velocidad*delta;
                float xMax= Mundo.ANCHO-ancho;
                if (x>xMax) x=xMax;
                break;
            case ATRAS:
                x-=velocidad*delta;
                if(x<0) x=0;
                break;
        }
    }
    @Override public void dibuja(SpriteBatch sb, ShapeRenderer sr) {
        sb.draw(Assets.pescador,x,y,ancho,alto);
    }
}
