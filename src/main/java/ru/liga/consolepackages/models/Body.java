package ru.liga.consolepackages.models;


import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
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

    public Body(@JsonProperty("body") char[][] body) {
        this.body = body;
    }

    @JsonIgnore
    public int getWidth() {
        return body.length;
    }

    @JsonIgnore
    public int getLength() {
        return body[0].length;
    }

    public void insertPackage(Package pack, Place place) {
        int indexPackLine = pack.getWidth() - 1;

        for (int i = 0; i < pack.getWidth(); i++) {
            for (int j = 0; j < pack.getLength(indexPackLine); j++) {
                body[place.getX() - i][place.getY() + j] = pack.getSymbol();
            }
            indexPackLine--;
        }
    }

    public char getElement(Place place) {
        return body[place.getX()][place.getY()];
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
