package io.github.dedo;

import java.util.Random;

public class Aleatorio {

    public static int aleatorio() {
        Random random = new Random();

        // Rango
        int min = 1;
        int max = 100;

        // Generar un numero aleatorio en el rango
        int randomNumber = (random.nextInt((max - min) / 10) * 30) + min;

        return randomNumber;
    }
}
