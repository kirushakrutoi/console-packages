package ru.liga.ConsolePackages.models;

public class Place {
    private static final int[] cord = new int[2];

    public Place(int i, int j) {
        cord[0] = i;
        cord[1] = j;
    }

    public int getI() {
        return cord[0];
    }

    public int getJ() {
        return cord[1];
    }
}
