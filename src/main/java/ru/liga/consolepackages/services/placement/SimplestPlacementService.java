package ru.liga.consolepackages.services.placement;

import ru.liga.consolepackages.models.Body;
import ru.liga.consolepackages.models.Package;
import ru.liga.consolepackages.models.Place;
import ru.liga.consolepackages.services.placement.PlacementService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SimplestPlacementService extends PlacementService {
    private final int LENGTH_BODY;
    private final int WIDTH_BODY;

    public SimplestPlacementService(int WIDTH_BODY, int LENGTH_BODY) {
        this.LENGTH_BODY = LENGTH_BODY;
        this.WIDTH_BODY = WIDTH_BODY;
    }

    @Override
    public List<Body> placementPackage(List<Package> packages) {
        sortPackage(packages);

        List<Body> bodies = new ArrayList<>();
        for (Package pack : packages) {
            Body body = new Body(LENGTH_BODY, WIDTH_BODY);
            Place startPlace = new Place(LENGTH_BODY - 1, 0);
            body.insertPackage(pack, startPlace);
            bodies.add(body);
        }

        if (bodies.isEmpty()) {
            return Collections.singletonList(new Body(6, 6));
        }

        return bodies;
    }
}
