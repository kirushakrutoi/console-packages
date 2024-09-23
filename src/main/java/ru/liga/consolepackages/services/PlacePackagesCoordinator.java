package ru.liga.consolepackages.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.liga.consolepackages.exceptions.IncorrectAnswerException;
import ru.liga.consolepackages.models.Body;
import ru.liga.consolepackages.models.Package;
import ru.liga.consolepackages.services.placements.OptimalPlacementService;
import ru.liga.consolepackages.services.placements.PlacementService;
import ru.liga.consolepackages.services.placements.UniformPlacementService;
import ru.liga.consolepackages.services.readers.PackagesReaderService;
import ru.liga.consolepackages.services.writers.WriterService;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public List<Body> getFilledBodies(int numberBodies, String filePath) throws IncorrectAnswerException, IOException {
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
