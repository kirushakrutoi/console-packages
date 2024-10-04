package ru.liga.consolepackages.services.placements;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.liga.consolepackages.coordinators.PlacePackagesCoordinator;
import ru.liga.consolepackages.models.Body;
import ru.liga.consolepackages.models.Package;
import ru.liga.consolepackages.models.Place;

import java.util.Collections;
import java.util.List;

public abstract class PlacementService {
    private static final Logger logger = LoggerFactory.getLogger(PlacePackagesCoordinator.class);

    public PlacementService() {

    }

    /**
     * Размещает посылки в указанных телах.
     *
     * @param packages  Список посылок.
     * @param emptyBody Список пустых кузовов.
     * @return Список заполненных кузовов.
     */
    public abstract List<Body> placementPackage(List<Package> packages, List<Body> emptyBody);

    protected void sortPackage(List<Package> packages) {
        packages.sort(Collections.reverseOrder(Package::compareTo));
    }

    protected boolean searchPlaceAndInsertPackage(Package pack, Body body) {
        for (int i = body.getWidth() - 1; i >= pack.getWidth() - 1; i--) {
            for (int j = 0; j < body.getLength() - pack.getMaxLength() + 1; j++) {
                Place place = new Place(i, j);

                if (isPlaceExist(pack, body, place)) {
                    logger.debug("Place ({}, {}) found for package type {}", place.getY(), place.getX(), pack.getSymbol());
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
                Place checkPlace = new Place(place.getY() - i, place.getX() + j);

                if (body.getElement(checkPlace) != ' ' && pack.getElement(new Place(i, j)) != ' ') {
                    return false;
                }
            }
            indexPackLine--;
        }

        return true;
    }
}
