package ru.liga.consolepackages.services.placements;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.liga.consolepackages.exceptions.SmallNumberBodiesException;
import ru.liga.consolepackages.models.Body;
import ru.liga.consolepackages.models.Package;

import java.util.List;

public class OptimalPlacementService extends PlacementService {
    private static final Logger logger = LoggerFactory.getLogger(OptimalPlacementService.class);

    public OptimalPlacementService(int WIDTH_BODY, int LENGTH_BODY) {
        super(WIDTH_BODY, LENGTH_BODY);
    }

    /**
     * Метод для оптимального размещения посылок в кузова грузовиков.
     *
     * @param packages список посылок
     * @param numberBodies количество кузовов грузовиков
     * @return список заполненных кузовов грузовиков
     * @throws SmallNumberBodiesException если количество кузовов грузовиков меньше, чем необходимо для размещения всех посылок
     */
    @Override
    public List<Body> placementPackage(List<Package> packages, int numberBodies) {
        logger.debug("Start sorting package");
        sortPackage(packages);
        logger.debug("End sorting package");

        logger.debug("Creating empty bodies");
        List<Body> bodies = createEmptyBodies(numberBodies);

        for (Package pack : packages) {
            boolean successInserted = true;

            logger.debug("Package type " + pack.getSymbol() + " the beginning of the placement");

            for (Body body : bodies) {
                successInserted = searchPlaceAndInsertPackage(pack, body);
                if (successInserted) {
                    break;
                }
            }

            logger.debug("Package type " + pack.getSymbol() + " the ending of the placement");

            if (!successInserted) {
                logger.warn("small number bodies");
                throw new SmallNumberBodiesException("small number bodies");
            }
        }

        return bodies;
    }
}
