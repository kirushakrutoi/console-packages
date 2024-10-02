package ru.liga.consolepackages.coordinators;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.liga.consolepackages.models.Body;
import ru.liga.consolepackages.services.packages.CountPackagesService;
import ru.liga.consolepackages.services.readers.BodiesReaderService;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;

@Component
public class CountPackageCoordinator {
    private static final Logger logger = LoggerFactory.getLogger(CountPackageCoordinator.class);
    private final BodiesReaderService readerService;
    private final CountPackagesService countPackagesService;

    @Autowired
    public CountPackageCoordinator(BodiesReaderService readerService, CountPackagesService countPackagesService) {
        this.readerService = readerService;
        this.countPackagesService = countPackagesService;
    }

    /**
     * Метод для подсчета посылок.
     *
     * @param filePath путь к файлу, содержащему данные о кузовах грузовиков
     * @return карта, содержащая количество посылок каждого типа
     */
    public Map<Character, Integer> countPackage(String filePath) {
        logger.debug("start reading file {}", filePath);
        List<Body> bodies = readerService.readBodiesFromJson(filePath);
        logger.debug("end reading file {}", filePath);

        for (Body body : bodies) {
            System.out.println(body);
        }

        return countPackagesService.countPackagesFromBodies(bodies);
    }
}
