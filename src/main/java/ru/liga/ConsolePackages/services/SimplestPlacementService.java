package ru.liga.ConsolePackages.services;

import ru.liga.ConsolePackages.models.Body;
import ru.liga.ConsolePackages.models.Package;
import ru.liga.ConsolePackages.models.Place;
import ru.liga.ConsolePackages.utils.PlacementUtil;

import java.util.ArrayList;
import java.util.List;

public class SimplestPlacementService implements ru.liga.ConsolePackages.services.PlacementService {
    private final int LENGTH_BODY;
    private final int  WIDTH_BODY;

    public SimplestPlacementService(int LENGTH_BODY, int WIDTH_BODY) {
        this.LENGTH_BODY = LENGTH_BODY;
        this.WIDTH_BODY = WIDTH_BODY;
    }
    @Override
    public List<Body> placementPackage(List<Package> packages) {
        List<Body> bodies = new ArrayList<>();
        PlacementUtil.sortPackage(packages);

        for (Package pack : packages) {
            Body body = new Body(LENGTH_BODY, WIDTH_BODY);

            body.insertPackage(pack, new Place(LENGTH_BODY - 1, 0));

            bodies.add(body);
        }

        return bodies;
    }
}
