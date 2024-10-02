package ru.liga.consolepackages.services.placements;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.liga.consolepackages.exceptions.SmallNumberBodiesException;
import ru.liga.consolepackages.models.Body;
import ru.liga.consolepackages.models.Package;

import java.util.Collections;
import java.util.List;

public class UniformPlacementService extends PlacementService {
    private static final Logger logger = LoggerFactory.getLogger(UniformPlacementService.class);

    /**
     * Метод для равномерного размещения посылок в кузова грузовиков.
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
        boolean chet = false;

        for (int i = 0; i < packages.size(); i++) {
            logger.debug("Package type {} the beginning of the placement", packages.get(i).getSymbol());

            if (!chet) {
                if (!searchPlaceAndInsertPackage(packages.get(i), bodies.get(i % numberBodies))) {
                    logger.warn("small number bodies");
                    throw new SmallNumberBodiesException("Small number bodies");
                }
                logger.debug("Package type {} the ending of the placement", packages.get(i).getSymbol());

                if (i > 0 && (i + 1) % numberBodies == 0) {
                    chet = true;
                }
            } else {
                if (!searchPlaceAndInsertPackage(packages.get(i), bodies.get(numberBodies - i % numberBodies - 1))) {
                    logger.warn("small number bodies");
                    throw new SmallNumberBodiesException("Small number bodies");
                }
                logger.debug("Package type {} the ending of the placement", packages.get(i).getSymbol());

                if ((i + 1) % numberBodies == 0) {
                    chet = false;
                }
            }
        }

        if (bodies.isEmpty()) {
            return Collections.singletonList(new Body(6, 6));
        }

        return bodies;
    }
}
