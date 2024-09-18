package ru.liga.models;

import ru.liga.views.BodyView;

public class Body {
    private final char[][] body;

    public Body(int length, int weight) {
        this.body = new char[length][weight];

        for (int i = 0; i < length; i++) {
            for (int j = 0; j < weight; j++) {
                this.body[i][j] = ' ';
            }
        }
    }

    public void insertPackage(Package pack, int i, int j) {
        int k = 0;

        for (int l = 0; l < pack.getWidth(); l++) {
            for (int m = 0; m < pack.getLength(pack.getWidth() - k - 1); m++) {
                body[i - l][j + m] = pack.getSymbol();
            }
            k++;
        }
    }

    public char getElement(int i, int j) {
        return body[i][j];
    }
}
