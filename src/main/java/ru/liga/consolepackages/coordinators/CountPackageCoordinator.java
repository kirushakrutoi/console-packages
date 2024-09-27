package ru.liga.consolepackages.coordinators;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.liga.consolepackages.models.Body;
import ru.liga.consolepackages.services.CountPackagesService;
import ru.liga.consolepackages.services.readers.BodiesReaderService;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Map;

public class CountPackageCoordinator {
    private static final Logger logger = LoggerFactory.getLogger(CountPackageCoordinator.class);
    private final BodiesReaderService readerService;
    private final CountPackagesService countPackagesService;

    public CountPackageCoordinator(BodiesReaderService readerService, CountPackagesService countPackagesService) {
        this.readerService = readerService;
        this.countPackagesService = countPackagesService;
    }

    /**
     * Метод для подсчета посылок.
     *
     * @param filePath путь к файлу, содержащему данные о кузовах грузовиков
     * @return карта, содержащая количество посылок каждого типа
     * @throws FileNotFoundException если указанный файл не найден
     */
    public Map<Character, Integer> countPackage(String filePath) throws FileNotFoundException {
        logger.debug("start reading file {}", filePath);
        List<Body> bodies = readerService.readBodiesFromJson(filePath);
        logger.debug("end reading file {}", filePath);

        for (Body body : bodies) {
            System.out.println(body);
        }

        return countPackagesService.countPackagesFromBodies(bodies);
    }
}
