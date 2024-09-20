package ru.liga.consolepackages.services;

import ru.liga.consolepackages.exceptions.IncorrectAnswerException;
import ru.liga.consolepackages.models.Body;
import ru.liga.consolepackages.models.Package;
import ru.liga.consolepackages.services.placements.OptimalPlacementService;
import ru.liga.consolepackages.services.placements.PlacementService;
import ru.liga.consolepackages.services.placements.SimplestPlacementService;
import ru.liga.consolepackages.services.readers.ReaderService;
import ru.liga.consolepackages.services.writers.WriterService;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CoordinatorService {
    private static final String OPTIMAL_ALGORITHM = "o";
    private static final String SIMPLEST_ALGORITHM = "s";
    private static final Map<String, PlacementService> placementServicesMap = new HashMap<>();
    private final ReaderService readerService;
    private final WriterService writerService;

    public CoordinatorService(int WIDTH_BODY, int LENGTH_BODY, ReaderService readerService, WriterService writerService) {
        this.readerService = readerService;
        this.writerService = writerService;
        placementServicesMap.put(OPTIMAL_ALGORITHM, new OptimalPlacementService(WIDTH_BODY, LENGTH_BODY));
        placementServicesMap.put(SIMPLEST_ALGORITHM, new SimplestPlacementService(WIDTH_BODY, LENGTH_BODY));
    }

    public List<Body> getFilledBodies(String answer, String filePath) throws IncorrectAnswerException, IOException {
        List<Package> packages;

        try {
            packages = readerService.readPackagesFromTxt(new File(filePath));
        } catch (IOException e) {
            throw new FileNotFoundException("File " + filePath + " not found");
        }

        if (!placementServicesMap.containsKey(answer)) {
            throw new IncorrectAnswerException("Incorrect answer\n o - optimal algorithm \n s - simplest algorithm");
        }

        List<Body> bodies =  placementServicesMap.get(answer).placementPackage(packages);
        writerService.writeBodies(bodies);

        return bodies;
    }
}
