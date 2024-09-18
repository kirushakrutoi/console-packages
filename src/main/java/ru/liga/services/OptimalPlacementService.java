package ru.liga.services;

import ru.liga.models.Body;
import ru.liga.utils.PlacementUtil;

import java.util.ArrayList;
import java.util.List;

public class OptimalPlacementService implements PlacementService {
    private final int LENGTH_BODY;
    private final int  WIDTH_BODY;

    public OptimalPlacementService(int LENGTH_BODY, int WIDTH_BODY) {
        this.LENGTH_BODY = LENGTH_BODY;
        this.WIDTH_BODY = WIDTH_BODY;
    }

    @Override
    public List<Body> placementPackage(List<char[][]> packages) {
        PlacementUtil.sortPackage(packages);
        List<Body> bodies = new ArrayList<>();
        Body startBody = new Body(LENGTH_BODY, WIDTH_BODY);
        bodies.add(startBody);

        for(char[][] pack : packages) {
            boolean f = true;

            for(Body body : bodies) {
                f = searchPlaceAndInsertPackage(pack, body);
                if(f) break;
            }

            if(!f) {
                Body newBody = new Body(LENGTH_BODY, WIDTH_BODY);
                bodies.add(newBody);
                searchPlaceAndInsertPackage(pack, newBody);
            }
        }

        return bodies;
    }

    private boolean searchPlaceAndInsertPackage(char[][] pack, Body body) {
        boolean f = true;
        for (int i = WIDTH_BODY - 1; i >= pack.length - 1; i--) {
            for (int j = 0; j < WIDTH_BODY - pack[pack.length - 1].length + 1; j++) {
                f = true;
                int k = 0;

                for (int l = 0; l < pack.length; l++) {
                    for (int m = 0; m < pack[pack.length - k - 1].length; m++) {
                        if (body.getElement(i - l, j + m) != ' ') {
                            f = false;
                            break;
                        }
                    }
                    k++;
                }

                if (f) {
                    body.insertPackage(pack, i, j);
                    break;
                }
            }
            if (f) {
                break;
            }
        }

        return f;
    }
}
