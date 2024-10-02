package ru.liga.consolepackages.services.placements;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.liga.consolepackages.exceptions.SmallNumberBodiesException;
import ru.liga.consolepackages.models.Body;
import ru.liga.consolepackages.models.Package;

import java.util.List;

public class OptimalPlacementService extends PlacementService {
    private static final Logger logger = LoggerFactory.getLogger(OptimalPlacementService.class);

    /**
     * Метод для оптимального размещения посылок в кузова грузовиков.
     *
     * @param packages     список посылок
     * @param widthBody    ширина кузова машин
     * @param lengthBody   длина кузова машин
     * @param numberBodies количество кузовов грузовиков
     * @return список заполненных кузовов грузовиков
     */
    @Override
    public List<Body> placementPackage(List<Package> packages, int numberBodies, int widthBody, int lengthBody) {
        logger.debug("Start sorting package");
        sortPackage(packages);
        logger.debug("End sorting package");

        logger.debug("Creating empty bodies");
        List<Body> bodies = createEmptyBodies(numberBodies, widthBody, lengthBody);

        for (Package pack : packages) {
            boolean successInserted = true;

            logger.debug("Package type {} the beginning of the placement", pack.getSymbol());

            for (Body body : bodies) {
                successInserted = searchPlaceAndInsertPackage(pack, body);
                if (successInserted) {
                    break;
                }
            }

            logger.debug("Package type {} the ending of the placement", pack.getSymbol());

            if (!successInserted) {
                logger.warn("small number bodies");
                throw new SmallNumberBodiesException("small number bodies");
            }
        }

        return bodies;
    }
}
