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
    private static final String OPTIMAL_ALGORITHM = "o";
    private static final String UNIFORM_ALGORITHM = "u";
    private static final Map<String, PlacementService> placementServicesMap = new HashMap<>();
    private final PackagesReaderService readerService;
    private final WriterService writerService;
    private static final Logger logger = LoggerFactory.getLogger(PlacePackagesCoordinator.class);

    public PlacePackagesCoordinator(int WIDTH_BODY, int LENGTH_BODY, PackagesReaderService readerService, WriterService writerService) {
        this.readerService = readerService;
        this.writerService = writerService;
        placementServicesMap.put(OPTIMAL_ALGORITHM, new OptimalPlacementService(WIDTH_BODY, LENGTH_BODY));
        placementServicesMap.put(UNIFORM_ALGORITHM, new UniformPlacementService(WIDTH_BODY, LENGTH_BODY));
    }

    public List<Body> getFilledBodies(int numberBodies, String answer, String filePath) throws IncorrectAnswerException, IOException {
        List<Package> packages;

        if (!placementServicesMap.containsKey(answer)) {
            logger.warn("Incorrect user response");
            throw new IncorrectAnswerException("Incorrect answer\n o - optimal algorithm \n s - simplest algorithm");
        }

        try {
            logger.debug("The file " + filePath + " is being read");
            packages = readerService.readPackagesFromTxt(new File(filePath));
            logger.debug("The file has been read successfully");
        } catch (IOException e) {
            logger.debug("Failed to read the file " + filePath);
            logger.warn(e.getMessage());
            throw new FileNotFoundException("File " + filePath + " not found");
        }


        List<Body> bodies = placementServicesMap.get(answer).placementPackage(packages, numberBodies);
        logger.debug("Recording of downloaded machines to a file has begun");
        writerService.writeBodies(bodies);
        logger.debug("The recording of the downloaded machines to the file has been completed successfully");

        return bodies;
    }
}
