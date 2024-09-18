package ru.liga.services;

import ru.liga.models.Body;
import ru.liga.models.Package;
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
    public List<Body> placementPackage(List<Package> packages) {
        PlacementUtil.sortPackage(packages);
        List<Body> bodies = new ArrayList<>();
        Body startBody = new Body(LENGTH_BODY, WIDTH_BODY);
        bodies.add(startBody);

        for(Package pack : packages) {
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

    private boolean searchPlaceAndInsertPackage(Package pack, Body body) {
        for (int i = WIDTH_BODY - 1; i >= pack.getWidth() - 1; i--) {
            for (int j = 0; j < WIDTH_BODY - pack.getLength(pack.getWidth() - 1) + 1; j++) {
                if (checkPlaceForPackage(pack, body, i, j)) {
                    body.insertPackage(pack, i, j);
                    return true;
                }
            }
        }

        return false;
    }

    private boolean checkPlaceForPackage(Package pack, Body body, int i, int j) {
        int k = 0;

        for (int l = 0; l < pack.getWidth(); l++) {
            for (int m = 0; m < pack.getLength(pack.getWidth() - k - 1); m++) {
                if (body.getElement(i - l, j + m) != ' ') {
                    return false;
                }
            }
            k++;
        }

        return true;
    }
}
