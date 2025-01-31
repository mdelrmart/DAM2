package com.mdelmart.pescador;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.util.Random;

public class Pez extends Personaje {
    static final int VELOCIDAD_MIN=30,VELOCIDAD_MAX=120;
    static final int ANCHO=30,ALTO =20;
    private static Random random=new Random();
    public Pez() {
        super(0,random.nextInt(Mundo.ALTO_LINEA_MAR- ALTO),
            ANCHO,ALTO,
            VELOCIDAD_MIN+random.nextInt(VELOCIDAD_MAX-VELOCIDAD_MIN),
            random.nextBoolean()?Estado.ADELANTE:Estado.ATRAS);
        x=estado==Estado.ADELANTE?-ancho:Mundo.ANCHO;
        hitBox.y=y;
    }
    public void actualiza(float delta) {
        float incrementoEspacio=velocidad*delta;
        switch (estado) {
            case ADELANTE:
                x+=incrementoEspacio;
                if (x>Mundo.ANCHO) Mundo.pezFueraDelMundo(this);
                break;
            case ATRAS:
                x-=incrementoEspacio;
                if(x<-ancho) Mundo.pezFueraDelMundo(this);
                break;
        }
        hitBox.x=x;
    }
    public void dibuja(SpriteBatch sb, ShapeRenderer sr) {
        if(Mundo.MODO_DEBUG) sr.rect(hitBox.x,hitBox.y,hitBox.width,hitBox.height);
        if(estado==Estado.ADELANTE)
            sb.draw(Assets.pez,x+ancho,y,-ancho,alto);
        else
            sb.draw(Assets.pez,x,y,ancho,alto);
    }
}
