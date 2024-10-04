package ru.liga.consolepackages.services.placements;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.liga.consolepackages.exceptions.pacakgesexceptions.PlacementException;
import ru.liga.consolepackages.models.Body;
import ru.liga.consolepackages.models.Package;

import java.util.List;

public class OptimalPlacementService extends PlacementService {
    private static final Logger logger = LoggerFactory.getLogger(OptimalPlacementService.class);

    public OptimalPlacementService() {
    }

    /**
     * Оптимально размещает посылки в указанных телах.
     *
     * @param packages  Список посылок.
     * @param emptyBody Список пустых кузовов.
     * @return Список заполненных кузовов.
     */
    @Override
    public List<Body> placementPackage(List<Package> packages, List<Body> emptyBody) {
        logger.debug("Start sorting package");
        sortPackage(packages);
        logger.debug("End sorting package");

        logger.debug("Creating empty bodies");
        //List<Body> bodies = createEmptyBodies(numberBodies);

        //boolean successPlacement = true;
        for (Package pack : packages) {
            boolean successInserted = true;

            logger.debug("Package type " + pack.getSymbol() + " the beginning of the placement");

            for (Body body : emptyBody) {
                successInserted = searchPlaceAndInsertPackage(pack, body);
                if (successInserted) {
                    break;
                }
            }

            logger.debug("Package type " + pack.getSymbol() + " the ending of the placement");

            if (!successInserted) {
                logger.warn("Package " + pack.getId() + " failed to post");
                throw new PlacementException("Package " + pack.getId() + " failed to post");
            }
        }

        return emptyBody;
    }
}
