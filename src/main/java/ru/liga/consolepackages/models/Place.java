package ru.liga.consolepackages.models;

public class Place {
    private final int y;
    private final int x;

    public Place(int y, int x) {
        this.y = y;
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }
}
