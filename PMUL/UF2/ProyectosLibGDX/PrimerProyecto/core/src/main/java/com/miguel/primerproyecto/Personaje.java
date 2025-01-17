package com.miguel.primerproyecto;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Personaje {
    int x = 100;
    int y = 0;
    Estado estado;

    public Personaje() {
        this.estado = Personaje.Estado.PARADO;
    }

    public void actualiza() {

        switch (this.estado) {
            case IZQUIERDA:
                --this.x;
                break;
            case DERECHA:
                ++this.x;
                break;
            case ARRIBA:
                ++this.y;
                break;
            case ABAJO:
                --this.y;
                break;
        }

    }

    public void dibuja(SpriteBatch sb, Texture textura) {
        sb.draw(textura, (float)this.x, (float)this.y, 50, 50);
    }

    public void izquierda() {
        this.estado = Personaje.Estado.IZQUIERDA;
    }

    public void derecha() {
        this.estado = Personaje.Estado.DERECHA;
    }

    public void arriba() {this.estado = Personaje.Estado.ARRIBA;}

    public void abajo() {this.estado = Personaje.Estado.ABAJO;}

    public void parado() {
        this.estado = Personaje.Estado.PARADO;
    }

    static enum Estado {
        IZQUIERDA,
        DERECHA,
        ARRIBA,
        ABAJO,
        PARADO;

        private Estado() {
        }
    }
}
