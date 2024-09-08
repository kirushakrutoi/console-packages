package ru.liga.services;

import java.util.ArrayList;
import java.util.List;

public class PlacementService {
    private final int LENGTH_BODY;
    private final int  WIDTH_BODY;

    public PlacementService(int LENGTH_BODY, int WIDTH_BODY) {
        this.LENGTH_BODY = LENGTH_BODY;
        this.WIDTH_BODY = WIDTH_BODY;
    }

    public List<char[][]> placementPackage(List<char[][]> packages) {
        sortPackage(packages);
        List<char[][]> bodies = new ArrayList<>();
        char[][] startBody = initBody();
        bodies.add(startBody);

        for(char[][] pack : packages) {
            boolean f = true;

            for(char[][] body : bodies) {
                f = searchPlaceAndInsertPackage(pack, body);
                if(f) break;
            }

            if(!f) {
                char[][] newBody = initBody();
                bodies.add(newBody);
                searchPlaceAndInsertPackage(pack, newBody);
            }
        }

        return bodies;
    }

    public boolean searchPlaceAndInsertPackage(char[][] pack, char[][] body) {
        boolean f = true;
        for (int i = WIDTH_BODY - 1; i >= pack.length - 1; i--) {
            for (int j = 0; j < WIDTH_BODY - pack[pack.length - 1].length + 1; j++) {
                f = true;
                int k = 0;

                for (int l = 0; l < pack.length; l++) {
                    for (int m = 0; m < pack[pack.length - k - 1].length; m++) {
                        if (body[i - l][j + m] != ' ') {
                            f = false;
                            break;
                        }
                    }
                    k++;
                }

                if (f) {
                    insertPackage(pack, body, i, j);
                    break;
                }
            }
            if (f) {
                break;
            }
        }

        return f;
    }

    public List<char[][]> simplestPlacementPackage(List<char[][]> packages) {
        List<char[][]> bodies = new ArrayList<>();
        sortPackage(packages);

        for (char[][] pack : packages) {
            char[][] body = initBody();

            insertPackage(pack, body, LENGTH_BODY - 1, 0);

            bodies.add(body);
        }

        return bodies;
    }

    public void sortPackage(List<char[][]> packages) {
        for (int i = 0; i < packages.size() - 1; i++) {
            for (int j = i + 1; j < packages.size(); j++) {
                if(packages.get(i)[0][0] < packages.get(j)[0][0]) {
                    char[][] temp = packages.get(i);
                    packages.set(i, packages.get(j));
                    packages.set(j, temp);
                }
            }
        }
    }

    public static void insertPackage(char[][] pack, char[][] body, int i, int j) {
        int k = 0;

        for (int l = 0; l < pack.length; l++) {
            for (int m = 0; m < pack[pack.length - k - 1].length; m++) {
                body[i - l][j + m] = pack[0][0];
            }
            k++;
        }
    }

    public char[][] initBody() {
        char[][] body = new char[WIDTH_BODY][LENGTH_BODY];

        for (int i = 0; i < WIDTH_BODY; i++) {
            for (int j = 0; j < LENGTH_BODY; j++) {
                body[i][j] = ' ';
            }
        }

        return body;
    }
}
