package ru.liga.consolepackages.services.placements;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.liga.consolepackages.exceptions.SmallNumberBodiesException;
import ru.liga.consolepackages.models.Body;
import ru.liga.consolepackages.models.Package;
import ru.liga.consolepackages.models.Place;
import ru.liga.consolepackages.services.PlacePackagesCoordinator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class PlacementService {
    protected final int LENGTH_BODY;
    protected final int WIDTH_BODY;
    private static final Logger logger = LoggerFactory.getLogger(PlacePackagesCoordinator.class);

    public PlacementService(int WIDTH_BODY, int LENGTH_BODY) {
        this.LENGTH_BODY = LENGTH_BODY;
        this.WIDTH_BODY = WIDTH_BODY;
    }

    /**
     * Метод для размещения посылок в кузова грузовиков.
     *
     * @param packages список посылок
     * @param numberBodies количество кузовов грузовиков
     * @return список заполненных кузовов грузовиков
     */
    public abstract List<Body> placementPackage(List<Package> packages, int numberBodies);

    protected List<Body> createEmptyBodies(int numberBodies) {
        List<Body> bodies = new ArrayList<>();
        for (int i = 0; i < numberBodies; i++) {
            bodies.add(new Body(LENGTH_BODY, WIDTH_BODY));
        }
        return bodies;
    }

    protected void sortPackage(List<Package> packages) {
        packages.sort(Collections.reverseOrder(Package::compareTo));
    }

    protected boolean searchPlaceAndInsertPackage(Package pack, Body body) {
        for (int i = WIDTH_BODY - 1; i >= pack.getWidth() - 1; i--) {
            for (int j = 0; j < WIDTH_BODY - pack.getMaxLength() + 1; j++) {
                Place place = new Place(i, j);

                if (isPlaceExist(pack, body, place)) {
                    logger.debug("Place ({}, {}) found for package type {}", place.getX(), place.getY(), pack.getSymbol());
                    body.insertPackage(pack, place);
                    return true;
                }
            }
        }

        logger.debug("Place not found");
        return false;
    }

    private boolean isPlaceExist(Package pack, Body body, Place place) {
        int indexPackLine = pack.getWidth() - 1;

        for (int i = 0; i < pack.getWidth(); i++) {
            for (int j = 0; j < pack.getLength(indexPackLine); j++) {
                Place checkPlace = new Place(place.getX() - i, place.getY() + j);

                if (body.getElement(checkPlace) != ' ') {
                    return false;
                }
            }
            indexPackLine--;
        }

        return true;
    }
}
