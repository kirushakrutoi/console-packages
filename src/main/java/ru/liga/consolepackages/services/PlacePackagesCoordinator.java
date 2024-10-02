package ru.liga.consolepackages.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.liga.consolepackages.models.Body;
import ru.liga.consolepackages.models.Package;
import ru.liga.consolepackages.services.placements.PlacementService;
import ru.liga.consolepackages.services.readers.PackagesReaderService;
import ru.liga.consolepackages.services.writers.WriterService;

import java.util.List;

public class PlacePackagesCoordinator {
    private static final Logger logger = LoggerFactory.getLogger(PlacePackagesCoordinator.class);
    private final PackagesReaderService readerService;
    private final WriterService writerService;
    private final PlacementService placementService;

    public PlacePackagesCoordinator(PackagesReaderService readerService, WriterService writerService, PlacementService placementService) {
        this.readerService = readerService;
        this.writerService = writerService;
        this.placementService = placementService;
    }

    /**
     * Метод для получения заполненных кузовов грузовиков.
     *
     * @param numberBodies количество доступных кузовов грузовиков
     * @param widthBody    ширина кузова машин
     * @param lengthBody   длина кузова машин
     * @param filePath     путь к файлу, содержащему данные о посылках
     * @return список заполненных кузовов грузовиков
     */
    public List<Body> getFilledBodies(int numberBodies, int widthBody, int lengthBody, String filePath) {
        logger.debug("The file {} is being read", filePath);
        List<Package> packages = readerService.readPackagesFromTxt(filePath);
        logger.debug("The file has been read successfully");

        List<Body> bodies = placementService.placementPackage(packages, numberBodies, widthBody, lengthBody);
        logger.debug("Recording of downloaded machines to a file has begun");
        writerService.writeBodies(bodies);
        logger.debug("The recording of the downloaded machines to the file has been completed successfully");

        return bodies;
    }
}
