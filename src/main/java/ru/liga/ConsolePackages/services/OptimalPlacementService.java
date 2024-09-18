package ru.liga.ConsolePackages.services;

import ru.liga.ConsolePackages.models.Body;
import ru.liga.ConsolePackages.models.Place;
import ru.liga.ConsolePackages.models.Package;
import ru.liga.ConsolePackages.services.interfaces.PlacementService;
import ru.liga.ConsolePackages.utils.PlacementUtil;

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
            boolean successInserted = true;

            for(Body body : bodies) {
                successInserted = searchPlaceAndInsertPackage(pack, body);
                if(successInserted) break;
            }

            if(!successInserted) {
                Body newBody = new Body(LENGTH_BODY, WIDTH_BODY);
                bodies.add(newBody);
                searchPlaceAndInsertPackage(pack, newBody);
            }
        }

        return bodies;
    }

    private boolean searchPlaceAndInsertPackage(Package pack, Body body) {
        for (int i = WIDTH_BODY - 1; i >= pack.getWidth() - 1; i--) {
            for (int j = 0; j < WIDTH_BODY - pack.getMaxLength() + 1; j++) {
                Place place = new Place(i, j);

                if (checkPlaceForPackage(pack, body, place)) {
                    body.insertPackage(pack, place);
                    return true;
                }
            }
        }

        return false;
    }

    private boolean checkPlaceForPackage(Package pack, Body body, Place place) {
        int indexPackLine = pack.getWidth() - 1;

        for (int i = 0; i < pack.getWidth(); i++) {
            for (int j = 0; j < pack.getLength(indexPackLine); j++) {
                Place checkPlace = new Place(place.getI() - i, place.getJ() + j);

                if (body.getElement(checkPlace) != ' ') {
                    return false;
                }
            }
            indexPackLine--;
        }

        return true;
    }
}
