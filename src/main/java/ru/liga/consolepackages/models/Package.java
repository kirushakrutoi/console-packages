package ru.liga.consolepackages.models;

import java.util.Arrays;

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

    public int getMaxLength() {
        return pack[pack.length - 1].length;
    }

    public int getSquare() {
        return pack[0][0] - '0';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Package aPackage = (Package) o;
        return aPackage.getSymbol() == getSymbol();
    }

    @Override
    public int hashCode() {
        return Character.hashCode(getSymbol());
    }

    @Override
    public String toString() {
        return Arrays.toString(pack);
    }
}
