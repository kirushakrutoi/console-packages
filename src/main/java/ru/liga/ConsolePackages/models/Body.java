package ru.liga.ConsolePackages.models;


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

    public void insertPackage(Package pack, Place place) {
        int k = 0;

        for (int i = 0; i < pack.getWidth(); i++) {
            for (int j = 0; j < pack.getLength(pack.getWidth() - k - 1); j++) {
                body[place.getI() - i][place.getJ() + j] = pack.getSymbol();
            }
            k++;
        }
    }

    public char getElement(int i, int j) {
        return body[i][j];
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < body.length; i++) {
            stringBuilder.append("+");
            for (int j = 0; j < body[0].length; j++) {
                stringBuilder.append(getElement(i, j));
            }
            stringBuilder.append('+').append("\n");
        }

        for (int j = 0; j < body[0].length + 2; j++) {
            stringBuilder.append('+');
        }
        stringBuilder.append("\n");

        return stringBuilder.toString();
    }
}
