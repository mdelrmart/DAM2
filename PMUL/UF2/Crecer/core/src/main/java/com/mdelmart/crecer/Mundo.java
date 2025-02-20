package com.mdelmart.crecer;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.utils.Array;

import java.util.Random;

public class Mundo {
    public static final float ANCHO = 1024;
    public static final float ALTO = 768;

    public static final int MAX_COLISIONES = 4;
    public static final float TIEMPO_ENTRE_ENEMIGOS = 2f;

    public final static boolean DEBUG = false;

    public static Jugador jugador = new Jugador();
    public static Array<Enemigo> enemigos = new Array<>();

    public static final float TAMANHO_MAXIMO_JUGADOR = Mundo.ALTO / 2;
    public static float AUMENTO_TAMANHO_JUGADOR = 10;
    public static final float INTERVALO_CRECIMIENTO_JUGADOR = 5;

    public static final float ALTURA_SEPARADOR = Mundo.ALTO / 7;

    public static Random random = new Random();

    public static void agregarEnemigo() {
        enemigos.add(new Enemigo());
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

    public static void eliminarEnemigo(Enemigo enemigo) {
        enemigos.removeValue(enemigo, true);
    }

    public static void comprobarColisiones() {
        for (Enemigo enemigo : enemigos) {
            if (Intersector.overlaps(enemigo.hitbox, jugador.hitbox) && jugador.tipo != enemigo.tipo) {
                jugador.colisionar();
                enemigos.removeValue(enemigo, true);
            }
        }
    }
}
