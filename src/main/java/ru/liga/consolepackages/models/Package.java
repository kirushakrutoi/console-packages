package ru.liga.consolepackages.models;

import java.util.Arrays;
import java.util.List;

public class Package {
    private final char[][] pack;

    public Package(char[][] pack) {
        this.pack = pack;
    }

    public Package(List<String> lines) {
        this.pack = new char[lines.size()][];
        for (int i = 0; i < lines.size(); i++) {
            this.pack[i] = new char[lines.get(i).length()];
            for (int j = 0; j < lines.get(i).length(); j++) {
                this.pack[i][j] = lines.get(i).charAt(j);
            }
        }
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
        int square = 0;
        for (int i = 0; i < getWidth(); i++) {
            square += getLength(i);
        }
        return square;
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

    public int compareTo(Package anotherPackage) {
        return getSquare() - anotherPackage.getSquare();
    }

    @Override
    public String toString() {
        return Arrays.toString(pack);
    }
}
