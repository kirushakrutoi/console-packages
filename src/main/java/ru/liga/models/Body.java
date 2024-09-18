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

    public void insertPackage(char[][] pack, int i, int j) {
        int k = 0;

        for (int l = 0; l < pack.length; l++) {
            for (int m = 0; m < pack[pack.length - k - 1].length; m++) {
                body[i - l][j + m] = pack[0][0];
            }
            k++;
        }
    }

    public char getElement(int i, int j) {
        return body[i][j];
    }
}
