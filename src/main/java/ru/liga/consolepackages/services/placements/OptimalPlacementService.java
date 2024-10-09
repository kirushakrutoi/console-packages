package ru.liga.consolepackages.services.placements;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import ru.liga.consolepackages.exceptions.pacakgesexceptions.PlacementException;
import ru.liga.consolepackages.models.Body;
import ru.liga.consolepackages.models.Package;

import java.util.List;

@Slf4j
@Component("o")
public class OptimalPlacementService extends PlacementService {

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
        log.debug("Start sorting package");
        sortPackage(packages);
        log.debug("End sorting package");

        log.debug("Creating empty bodies");
        for (Package pack : packages) {
            boolean successInserted = true;

            log.debug("Package type " + pack.getSymbol() + " the beginning of the placement");

            for (Body body : emptyBody) {
                successInserted = searchPlaceAndInsertPackage(pack, body);
                if (successInserted) {
                    break;
                }
            }

            log.debug("Package type " + pack.getSymbol() + " the ending of the placement");

            if (!successInserted) {
                log.warn("Package " + pack.getName() + " failed to post");
                throw new PlacementException("Package " + pack.getName() + " failed to post");
            }
        }

        return emptyBody;
    }

    @Override
    public String getType() {
        return "o";
    }
}
