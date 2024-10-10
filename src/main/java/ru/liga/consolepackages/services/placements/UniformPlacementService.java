package ru.liga.consolepackages.services.placements;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.liga.consolepackages.exceptions.pacakgesexceptions.PlacementException;
import ru.liga.consolepackages.models.Body;
import ru.liga.consolepackages.models.Package;

import java.util.List;

@Slf4j
@Component("u")
public class UniformPlacementService extends PlacementService {

    /**
     * Равномерно размещает посылки в указанных телах.
     *
     * @param packages    Список посылок.
     * @param emptyBodies Список пустых кузовов.
     * @return Список заполненных кузовов.
     */
    @Override
    public List<Body> placementPackage(List<Package> packages, List<Body> emptyBodies) {
        log.debug("Start sorting package");
        sortPackage(packages);
        log.debug("End sorting package");
        int numberBodies = emptyBodies.size();
        log.debug("Creating empty bodies");
        boolean chet = false;

        for (int i = 0; i < packages.size(); i++) {
            log.debug("Package type " + packages.get(i).getSymbol() + " the beginning of the placement");

            if (!chet) {
                if (!searchPlaceAndInsertPackage(packages.get(i), emptyBodies.get(i % numberBodies))) {
                    log.warn("Package " + packages.get(i).getName() + " failed to post");
                    throw new PlacementException("Package " + packages.get(i).getName() + " failed to post");
                }
                log.debug("Package type " + packages.get(i).getSymbol() + " the ending of the placement");

                if (i > 0 && (i + 1) % numberBodies == 0) {
                    chet = true;
                }
            } else {
                if (!searchPlaceAndInsertPackage(packages.get(i), emptyBodies.get(numberBodies - i % numberBodies - 1))) {
                    log.warn("Package " + packages.get(i).getName() + " failed to post");
                    throw new PlacementException("Package " + packages.get(i).getName() + " failed to post");
                }
                log.debug("Package type " + packages.get(i).getSymbol() + " the ending of the placement");

                if ((i + 1) % numberBodies == 0) {
                    chet = false;
                }
            }
        }

        return emptyBodies;
    }
}
