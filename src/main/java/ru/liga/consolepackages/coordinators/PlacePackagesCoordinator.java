package ru.liga.consolepackages.coordinators;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.liga.consolepackages.models.Body;
import ru.liga.consolepackages.models.Package;
import ru.liga.consolepackages.services.placements.PlacementService;
import ru.liga.consolepackages.services.readers.PackagesReaderService;
import ru.liga.consolepackages.services.writers.WriterService;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class PlacePackagesCoordinator {
    private final PackagesReaderService readerService;
    private final WriterService writerService;
    private final PlacementService placementService;
    private static final Logger logger = LoggerFactory.getLogger(PlacePackagesCoordinator.class);

    public PlacePackagesCoordinator(PackagesReaderService readerService, WriterService writerService, PlacementService placementService) {
        this.readerService = readerService;
        this.writerService = writerService;
        this.placementService = placementService;
    }

    /**
     * Метод для получения заполненных кузовов грузовиков.
     *
     * @param numberBodies количество доступных кузовов грузовиков
     * @param filePath путь к файлу, содержащему данные о посылках
     * @return список заполненных кузовов грузовиков
     * @throws IOException если возникла проблема при чтении входного файла или записи результатов
     */
    public List<Body> getFilledBodies(int numberBodies, String filePath) throws IOException {
        List<Package> packages;
        try {
            logger.debug("The file {} is being read", filePath);
            packages = readerService.readPackagesFromTxt(filePath);
            logger.debug("The file has been read successfully");
        } catch (IOException e) {
            logger.debug("Failed to read the file {}", filePath);
            logger.warn(e.getMessage());
            throw new FileNotFoundException("File " + filePath + " not found");
        }

        List<Body> bodies = placementService.placementPackage(packages, numberBodies);
        logger.debug("Recording of downloaded machines to a file has begun");
        writerService.writeBodies(bodies);
        logger.debug("The recording of the downloaded machines to the file has been completed successfully");

        return bodies;
    }
}
