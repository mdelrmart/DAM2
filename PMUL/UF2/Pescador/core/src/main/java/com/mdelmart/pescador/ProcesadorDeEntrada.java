package com.mdelmart.pescador;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;

// Clase que extiende InputAdapter para manejar la entrada del teclado
public class ProcesadorDeEntrada extends InputAdapter {
    // Metodo que se llama cuando se presiona una tecla
    @Override
    public boolean keyDown(int keycode) {
        // Si el anzuelo no está subiendo o bajando
        if (!Mundo.anzuelo.bajaOSube()) {
            // Dependiendo de la tecla presionada, se realiza una acción
            switch (keycode) {
                case Input.Keys.A:
                    // Mueve el pescador a la izquierda
                    Mundo.pescador.izquierda();
                    break;
                case Input.Keys.D:
                    // Mueve el pescador a la derecha
                    Mundo.pescador.derecha();
                    break;
                case Input.Keys.S:
                    // Baja el anzuelo
                    Mundo.anzuelo.abajo();
                    // Si el anzuelo está bajando, se detiene el pescador
                    Mundo.pescador.parar();
                    break;
            }
        }
        return true; // Indica que el evento fue manejado
    }

    // Metodo que se llama cuando se suelta una tecla
    @Override
    public boolean keyUp(int keycode) {
        // Si el anzuelo no está subiendo o bajando
        if (!Mundo.anzuelo.bajaOSube()) {
            // Dependiendo de la tecla soltada, se realiza una acción
            if (keycode == Input.Keys.A) {
                // Si la tecla 'D' no está presionada, el pescador se detiene
                if (!Gdx.input.isKeyPressed(Input.Keys.D)) {
                    Mundo.pescador.parar();
                }
                // Si la tecla 'D' está presionada, el pescador se mueve a la derecha
                if (Gdx.input.isKeyPressed(Input.Keys.D)) {
                    Mundo.pescador.derecha();
                }
            } else if (keycode == Input.Keys.D) {
                // Si la tecla 'A' no está presionada, el pescador se detiene
                if (!Gdx.input.isKeyPressed(Input.Keys.A)) {
                    Mundo.pescador.parar();
                }
                // Si la tecla 'A' está presionada, el pescador se mueve a la izquierda
                if (Gdx.input.isKeyPressed(Input.Keys.A)) {
                    Mundo.pescador.izquierda();
                }
            }
            // Si se suelta la tecla 'S', el anzuelo sube
            if (keycode == Input.Keys.S) {
                Mundo.anzuelo.arriba();
            }
        } else {
            // Si el anzuelo está subiendo o bajando y se suelta la tecla 'S', el anzuelo sube
            if (keycode == Input.Keys.S) {
                Mundo.anzuelo.arriba();
            }
        }
        return true; // Indica que el evento fue manejado
    }
}
