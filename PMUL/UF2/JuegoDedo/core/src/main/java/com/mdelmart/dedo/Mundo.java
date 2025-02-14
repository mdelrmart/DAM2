package com.mdelmart.dedo;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.utils.Array;
import com.mdelmart.dedo.entidades.*;

import java.util.Random;

public class Mundo {
    //region --- Constantes del juego ---
    // Resolución
    public static float ANCHO = 1024;
    public static float ALTO = 768;

    public static final float ALTURA_SEPARADOR = Mundo.ALTO / 7;

    public static final int VIDAS_DEDO = 3;

    public static final int MIN_VIDAS_ENEMIGO = 1;
    public static final int MAX_VIDAS_ENEMIGO = 5;

    public static final float TIEMPO_ENTRE_ENEMIGOS = 2f;
    public static final float LIMITE_BALAS_PANTALLA = 500;

    public static final int HIPERESPACIOS = 3;
    public static final float TIEMPO_HIPERESPACIO = 5f;

    public static final boolean DEBUG = false;
    //endregion

    public static Random random = new Random();

    public static Dedo dedo = new Dedo();
    public static Array<Enemigo> enemigos = new Array<>();
    public static Array<Bala> balas = new Array<>();

    public static float puntos = 0;

    public static void crearEnemigo() {
        if (random.nextBoolean()) {
            enemigos.add(new EnemigoCuadrado());
        } else {
            enemigos.add(new EnemigoCircular());
        }
    }

    public static void crearBala() {
        if(balas.size < LIMITE_BALAS_PANTALLA) {
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
                // Check collision between bullet and enemy
                if (enemigo instanceof EnemigoCuadrado) {
                    // Enemy is a square, use Rectangle for hitbox
                    if (Intersector.overlaps(bala.getHitbox(), ((EnemigoCuadrado) enemigo).getHitbox())) {
                        enemigo.quitarVida();
                        balas.removeValue(bala, true);
                    }
                } else if (enemigo instanceof EnemigoCircular) {
                    // Enemy is a circle, use Circle for hitbox
                    if (Intersector.overlaps(bala.getHitbox(), ((EnemigoCircular) enemigo).getHitbox())) {
                        enemigo.quitarVida();
                        balas.removeValue(bala, true);
                    }
                }
            }

            // Comprobar colisión entre dedo y enemigo
            if (enemigo instanceof EnemigoCuadrado) {
                // Si el enemigo es un rectangulo, usamos Rectangle para la hitbox
                if (Intersector.overlaps(((EnemigoCuadrado) enemigo).getHitbox(), dedo.getHitbox()) && !enemigo.haColisionado  && !dedo.hiperespacioActivo) {
                    dedo.quitarVida();
                    enemigo.colision();
                    enemigo.eliminar();
                }
            } else if (enemigo instanceof EnemigoCircular) {
                // Si el enemigo es un circulo, usamos Circle para la hitbox
                if (Intersector.overlaps(((EnemigoCircular) enemigo).getHitbox(), dedo.getHitbox()) && !enemigo.haColisionado && !dedo.hiperespacioActivo) {
                    dedo.quitarVida();
                    enemigo.colision();
                    enemigo.eliminar();
                }
            }
        }
    }

    /*
    public static void comprobarColisiones() {
        for (Enemigo enemigo : enemigos) {
            for (Bala bala : balas) {
                if (Intersector.overlaps(enemigo.getHitbox(), bala.getHitbox())) {
                    //enemigos.removeValue(enemigo, true);
                    enemigo.quitarVida();
                    balas.removeValue(bala, true);
                }
            }

            // Si el enemigo colisiona con el dedo y no ha colisionado antes, le quitamos una vida al dedo,
            // marcamos al enemigo como colisionado ya que si no restaría vidas al dedo en cada Delta
            // y finalmente lo eliminamos
            if (Intersector.overlaps(enemigo.getHitbox(), dedo.getHitbox()) && !enemigo.getHaColisionado()) {
                dedo.quitarVida();
                enemigo.colision();
                enemigo.eliminar();
            }

        }
    }
     */

}
