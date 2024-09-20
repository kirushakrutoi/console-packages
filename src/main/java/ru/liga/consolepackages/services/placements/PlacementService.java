package ru.liga.consolepackages.services.placements;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.liga.consolepackages.models.Body;
import ru.liga.consolepackages.models.Package;
import ru.liga.consolepackages.models.Place;
import ru.liga.consolepackages.services.PlacePackagesCoordinator;

import java.util.ArrayList;
import java.util.List;

public abstract class PlacementService {
    public abstract List<Body> placementPackage(List<Package> packages, int numberBodies);

    private static final Logger logger = LoggerFactory.getLogger(PlacePackagesCoordinator.class);

    protected final int LENGTH_BODY;
    protected final int WIDTH_BODY;

    public PlacementService(int WIDTH_BODY, int LENGTH_BODY) {
        this.LENGTH_BODY = LENGTH_BODY;
        this.WIDTH_BODY = WIDTH_BODY;
    }

    protected List<Body> createEmptyBodies(int numberBodies) {
        List<Body> bodies = new ArrayList<>();
        for (int i = 0; i < numberBodies; i++) {
            bodies.add(new Body(LENGTH_BODY, WIDTH_BODY));
        }
        return bodies;
    }

    protected void sortPackage(List<Package> packages) {
        for (int i = 0; i < packages.size() - 1; i++) {
            for (int j = i + 1; j < packages.size(); j++) {
                if (packages.get(i).getSquare() < packages.get(j).getSquare()) {
                    Package temp = packages.get(i);
                    packages.set(i, packages.get(j));
                    packages.set(j, temp);
                }
            }
        }
    }

    protected boolean searchPlaceAndInsertPackage(Package pack, Body body) {
        for (int i = WIDTH_BODY - 1; i >= pack.getWidth() - 1; i--) {
            for (int j = 0; j < WIDTH_BODY - pack.getMaxLength() + 1; j++) {
                Place place = new Place(i, j);

                if (checkPlaceForPackage(pack, body, place)) {
                    logger.debug("Place " + "(" + place.getI() + ", " + place.getJ() + ") found for package type " + pack.getSymbol());
                    body.insertPackage(pack, place);
                    return true;
                }
            }
        }

        logger.debug("Place not found");
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
