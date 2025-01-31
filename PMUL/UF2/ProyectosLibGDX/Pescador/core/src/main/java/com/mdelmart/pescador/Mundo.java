package com.mdelmart.pescador;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.utils.Array;

public class Mundo {
    public static boolean MODO_DEBUG=true;
    public static int ANCHO=332;
    public static int ALTO=200;
    public static int ALTO_LINEA_MAR=128;
    public static float TIEMPO_ENTRE_PECES=0.9f;
    public static Pescador pescador=new Pescador();
    public static Anzuelo anzuelo=new Anzuelo();
    public static Array<Pez> peces = new Array();
    public static int numCapturas=0;
    public static void creaPez() {
        peces.add(new Pez());
    }
    public static void eliminaPez(Pez pez) { peces.removeValue(pez,true); }
    public static void dibujaPeces(SpriteBatch sb, ShapeRenderer sr, float stateTime) {
        for(Pez pez:peces) pez.dibuja(sb,sr);
    }
    public static void actualizaPeces(float delta) {
        for(Pez pez:peces) pez.actualiza(delta);
    }
    public static void comprobarColisiones() {
        for(Pez pez:peces)
            if(Intersector.overlaps(Mundo.anzuelo.getHitBox(),pez.getHitBox()))
                Mundo.nuevaCaptura(pez);
    }
    public static void nuevaCaptura(Pez pez) {
        numCapturas++;
        eliminaPez(pez);
    }
    public static void pezFueraDelMundo(Pez pez) {
        eliminaPez(pez);
    }
    public static String toStringPecesActuales() {
        return "Peces:"+Mundo.peces.size;
    }
    public static String toStringCapturas() {
        return "Capturas:"+numCapturas;
    }
}
