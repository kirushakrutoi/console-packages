package ru.liga.services;

import ru.liga.models.Body;
import ru.liga.utils.PlacementUtil;

import java.util.ArrayList;
import java.util.List;

public class SimplestPlacementService implements PlacementService {
    private final int LENGTH_BODY;
    private final int  WIDTH_BODY;

    public SimplestPlacementService(int LENGTH_BODY, int WIDTH_BODY) {
        this.LENGTH_BODY = LENGTH_BODY;
        this.WIDTH_BODY = WIDTH_BODY;
    }
    @Override
    public List<Body> placementPackage(List<char[][]> packages) {
        List<Body> bodies = new ArrayList<>();
        PlacementUtil.sortPackage(packages);

        for (char[][] pack : packages) {
            Body body = new Body(LENGTH_BODY, WIDTH_BODY);

            body.insertPackage(pack, LENGTH_BODY - 1, 0);

            bodies.add(body);
        }

        return bodies;
    }
}
