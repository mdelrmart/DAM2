package com.mdelmart.insectos;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Array;

import java.util.Random;

public class Mundo {
    public static float ANCHO = 1024;
    public static float ALTO = 768;

    public static int NUM_TEXTURAS_INSECTOS = Assets.NOMBRES_TEXTURAS_BICHOS.length;
    public static final float TIEMPO_ENTRE_CAMBIO_DIRECCION_INSECTO = 0.25f;

    public static Random random = new Random();

    public static Insecto insecto = new Insecto(Mundo.ANCHO / 2, Mundo.ALTO / 2);

    public static Array<Insecto> insectosMuertos = new Array<>();

    public static void comprobarClic(int screenX, int screenY, int pointer, int button) {
        float realY = Mundo.ALTO - screenY;

        if (button == Input.Buttons.LEFT && insecto.hitbox.contains(screenX, realY) && insecto.estado != Insecto.Estado.MUERTO) {
            insecto.tocar();
        }
    }

    public static void reiniciar() {
        insecto = new Insecto(Mundo.ANCHO / 2, Mundo.ALTO / 2);
    }

    public static void agregarInsectoMuerto() {
        insectosMuertos.add(insecto);
    }

    public static void dibujarInsectosMuertos(SpriteBatch sb, ShapeRenderer sr, ShapeRenderer srHitbox) {
        for (Insecto insecto : insectosMuertos) {
            insecto.dibujar(sb, sr, srHitbox);
        }
    }
}
