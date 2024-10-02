package ru.liga.consolepackages.services.placements;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.liga.consolepackages.exceptions.SmallNumberBodiesException;
import ru.liga.consolepackages.models.Body;
import ru.liga.consolepackages.models.Package;

import java.util.Collections;
import java.util.List;


public class UniformPlacementService extends PlacementService {
    private static final Logger logger = LoggerFactory.getLogger(UniformPlacementService.class);

    public UniformPlacementService() {
    }

    /**
     * Метод для равномерного размещения посылок в кузова грузовиков.
     *
     * @param packages     список посылок
     * @param emptyBodies пустые кузова грузовиков
     * @return список заполненных кузовов грузовиков
     * @throws SmallNumberBodiesException если количество кузовов грузовиков меньше, чем необходимо для размещения всех посылок
     */
    @Override
    public List<Body> placementPackage(List<Package> packages, List<Body> emptyBodies) {
        logger.debug("Start sorting package");
        sortPackage(packages);
        logger.debug("End sorting package");
        int numberBodies = emptyBodies.size();
        logger.debug("Creating empty bodies");
        //List<Body> bodies = createEmptyBodies(numberBodies);
        boolean chet = false;

        for (int i = 0; i < packages.size(); i++) {
            logger.debug("Package type " + packages.get(i).getSymbol() + " the beginning of the placement");

            if (!chet) {
                if (!searchPlaceAndInsertPackage(packages.get(i), emptyBodies.get(i % numberBodies))) {
                    logger.warn("small number bodies");
                    throw new SmallNumberBodiesException("Small number bodies");
                }
                logger.debug("Package type " + packages.get(i).getSymbol() + " the ending of the placement");

                if (i > 0 && (i + 1) % numberBodies == 0) {
                    chet = true;
                }
            } else {
                if (!searchPlaceAndInsertPackage(packages.get(i), emptyBodies.get(numberBodies - i % numberBodies - 1))) {
                    logger.warn("small number bodies");
                    throw new SmallNumberBodiesException("Small number bodies");
                }
                logger.debug("Package type " + packages.get(i).getSymbol() + " the ending of the placement");

                if ((i + 1) % numberBodies == 0) {
                    chet = false;
                }
            }
        }

/*        if (emptyBodies.isEmpty()) {
            return Collections.singletonList(new Body(6, 6));
        }*/

        return emptyBodies;
    }
}
