package com.mdelmart.dedo;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.utils.Array;

import java.util.Random;

public class Mundo {
    public static float ANCHO = 640;
    public static float ALTO = 480;
    public static final float TIEMPO_ENTRE_ENEMIGOS = 1.5f;
    public static final float LIMITE_BALAS = 5;
    public static final int MIN_VIDAS_ENEMIGO = 1;
    public static final int MAX_VIDAS_ENEMIGO = 10;
    public static final int VIDAS_DEDO = 3;
    public static final float ALTURA_SEPARADOR = Mundo.ALTO / 7;

    public static Random random = new Random();

    public static Dedo dedo = new Dedo();
    public static Array<Enemigo> enemigos = new Array<>();
    public static Array<Bala> balas = new Array<>();

    public static void crearEnemigo() {
        enemigos.add(new Enemigo());
    }

    public static void crearBala() {
        if(balas.size < LIMITE_BALAS) {
            balas.add(new Bala());
        }
    }

    public static void actualizarEnemigos(float delta) {
        for (Enemigo enemigo : enemigos) {
            enemigo.actualiza(delta);
        }
    }

    public static void dibujarEnemigos(SpriteBatch sb, ShapeRenderer sr) {
        for (Enemigo enemigo : enemigos) {
            enemigo.dibuja(sb, sr);
        }
    }

    public static void actualizarBalas(float delta) {
        for (Bala bala : balas) {
            bala.actualiza(delta);
        }
    }

    public static void dibujarBalas(ShapeRenderer sr) {
        for (Bala bala : balas) {
            bala.dibuja(sr);
        }
    }

    public static void comprobarColisiones() {
        for (Enemigo enemigo : enemigos) {
            for (Bala bala : balas) {
                if (Intersector.overlaps(enemigo.hitbox, bala.hitbox)) {
                    //enemigos.removeValue(enemigo, true);
                    enemigo.quitarVida();
                    balas.removeValue(bala, true);
                }
            }

            if (Intersector.overlaps(enemigo.hitbox, dedo.hitbox) && !enemigo.haColisionado) {
                dedo.quitarVida();
                enemigo.colision();
            }
        }
    }

}
