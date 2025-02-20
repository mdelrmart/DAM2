package com.mdelmart.insectos;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;

public class Insecto {
    enum Estado {
        ARRIBA, ABAJO, IZQUIERDA, DERECHA, MUERTO
    }

    protected float x, y, velocidad, ancho, alto;
    protected boolean muerto;
    protected Estado estado;
    protected Rectangle hitbox;
    protected int numSprite;
    protected float tiempoHastaCambio;

    public Insecto(float x, float y) {
        this.x = x;
        this.y = y;
        velocidad = 20f;
        ancho = 30f;
        alto = 30f;
        estado = Estado.DERECHA;
        hitbox = new Rectangle(x, y, ancho, alto);
        numSprite = 0;
        tiempoHastaCambio = 0;
    }

    public void actualiza(float delta) {
        // Si el insecto está muerto, no se mueve
        if (estado == Estado.MUERTO) {
            return;
        }

        tiempoHastaCambio += delta;
        if (tiempoHastaCambio >= Mundo.TIEMPO_ENTRE_CAMBIO_DIRECCION_INSECTO) {
            switch (Mundo.random.nextInt(4)) {
                case 0:
                    estado = Estado.ARRIBA;
                    break;
                case 1:
                    estado = Estado.ABAJO;
                    break;
                case 2:
                    estado = Estado.IZQUIERDA;
                    break;
                case 3:
                    estado = Estado.DERECHA;
                    break;
            }
            tiempoHastaCambio = 0;
        }

        if(estado != Estado.MUERTO) {
            switch (estado) {
                case ARRIBA:
                    if(x <= Mundo.ANCHO - ancho) {
                        x += velocidad * delta;
                    } else {
                        estado = Estado.ABAJO;
                    }
                    break;
                case ABAJO:
                    if(x >= 0) {
                        x -= velocidad * delta;
                    } else {
                        estado = Estado.ARRIBA;
                    }
                    break;
                case IZQUIERDA:
                    if(y <= Mundo.ALTO- alto) {
                        y += velocidad * delta;
                    } else {
                        estado = Estado.DERECHA;
                    }
                    break;
                case DERECHA:
                    if (y >= 0) {
                        y -= velocidad * delta;
                    } else {
                        estado = Estado.IZQUIERDA;
                    }
            }
            hitbox.x = x;
            hitbox.y = y;
        }
    }

    public void dibujar(SpriteBatch sb, ShapeRenderer sr, ShapeRenderer srHitboxes){
        if(numSprite < Mundo.NUM_TEXTURAS_INSECTOS) {
            sb.draw(Assets.getTexture(numSprite), x, y, ancho, alto);

            if (Mundo.DEBUG){
                srHitboxes.rect(hitbox.x, hitbox.y, hitbox.width, hitbox.height);
            }
        } else {
            sr.rect(x, y, ancho, alto);
        }
    }

    public void tocar() {
        numSprite = (numSprite == Mundo.NUM_TEXTURAS_INSECTOS ? 0 : numSprite + 1);

        if(numSprite >= Mundo.NUM_TEXTURAS_INSECTOS) {
            estado = Estado.MUERTO;
            muerto = true;
            Mundo.agregarInsectoMuerto();
        }

    }

    public boolean estaMuerto() {
        return muerto;
    }
}
