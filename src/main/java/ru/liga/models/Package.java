package ru.liga.models;

public class Package {
    private final char[][] pack;

    public Package(char[][] pack) {
        this.pack = pack;
    }

    public char getSymbol() {
        return pack[0][0];
    }

    public int getWidth() {
        return pack.length;
    }

    public int getLength(int line) {
        return pack[line].length;
    }

    public int getSquare() {
        return pack[0][0];
    }
}
