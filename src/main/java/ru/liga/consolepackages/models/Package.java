package ru.liga.consolepackages.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Arrays;
import java.util.List;

@Data
@Entity
@Table(name = "packages")
@NoArgsConstructor
@AllArgsConstructor
public class Package {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(unique = true)
    private String name;
    @Column(unique = true)
    private char symbol;
    @Column
    private char[][] pack;

    public Package(char[][] pack) {
        this.symbol = pack[0][0];
        this.pack = pack;
    }

    @JsonCreator
    public Package(@JsonProperty("name") String name, @JsonProperty("symbol") char symbol, @JsonProperty("pack") char[][] pack) {
        this.name = name;
        this.symbol = symbol;
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
        this.name = String.valueOf(this.pack[0][0]);
        this.symbol = this.pack[0][0];
    }

    public char getElement(Place place) {
        char c = pack[place.getY()][place.getX()];
        if (c == ' ')
            return ' ';
        else return symbol;
    }

    @JsonIgnore
    public int getWidth() {
        return pack.length;
    }

    @JsonIgnore
    public int getLength(int line) {
        return pack[line].length;
    }

    @JsonIgnore
    public int getMaxLength() {
        return pack[pack.length - 1].length;
    }

    @JsonIgnore
    public int getSquare() {
        int square = 0;
        for (char[] chars : pack) {
            for (char aChar : chars) {
                if (aChar != ' ') {
                    square++;
                }
            }
        }
        return square;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Package aPackage = (Package) o;
        if (aPackage.getSymbol() != getSymbol()
                || !aPackage.getName().equals(getName())
                || aPackage.getWidth() != getWidth()) {
            return false;
        }

        for (int i = 0; i < getWidth(); i++) {
            if (aPackage.getLength(i) != getLength(i)) {
                return false;
            }

            for (int j = 0; j < getLength(i); j++) {
                Place place = new Place(i, j);
                if (getElement(place) == ' ') {
                    if (aPackage.getElement(place) != ' ')
                        return false;
                }
            }
        }

        return true;
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
        return "Package{" +
                "name='" + name + '\'' +
                ", symbol=" + symbol +
                ", pack=" + Arrays.deepToString(pack) +
                '}';
    }
}
