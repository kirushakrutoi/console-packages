package ru.liga.consolepackages.models;


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
        int indexPackLine = pack.getWidth() - 1;

        for (int i = 0; i < pack.getWidth(); i++) {
            for (int j = 0; j < pack.getLength(indexPackLine); j++) {
                body[place.getI() - i][place.getJ() + j] = pack.getSymbol();
            }
            indexPackLine--;
        }
    }

    public char getElement(Place place) {
        return body[place.getI()][place.getJ()];
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < body.length; i++) {
            stringBuilder.append("+");
            for (int j = 0; j < body[0].length; j++) {
                Place place = new Place(i, j);
                stringBuilder.append(getElement(place));
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
