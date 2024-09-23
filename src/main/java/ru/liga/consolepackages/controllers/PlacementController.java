package ru.liga.consolepackages.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.liga.consolepackages.models.Body;
import ru.liga.consolepackages.services.PlacePackagesCoordinator;
import ru.liga.consolepackages.services.PlacementServiceFactory;
import ru.liga.consolepackages.services.readers.PackagesReaderServiceImpl;
import ru.liga.consolepackages.services.writers.WriterServiceImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

public class PlacementController {

    private final int LENGTH_BODY;
    private final int WIDTH_BODY;
    private final BufferedReader reader;
    private static final Logger logger = LoggerFactory.getLogger(PlacementController.class);

    public PlacementController(int lengthBody, int widthBody, BufferedReader reader) {
        LENGTH_BODY = lengthBody;
        WIDTH_BODY = widthBody;
        this.reader = reader;
    }

    public void placePackage() throws IOException {
        System.out.println("Enter file path");
        String filePath = reader.readLine();
        logger.debug("The path to the file has been entered");


        System.out.println("How many cars are available: ");
        int numberBodies = Integer.parseInt(reader.readLine());
        logger.debug("The number of bodies entered");

        System.out.println("Uniform or optimal algorithm?");
        System.out.println("u - uniform, o - optimal");
        String selectedAlgorithm = reader.readLine();
        logger.debug("The type {} of algorithm entered", selectedAlgorithm);

        System.out.println("Enter the directory for writing");
        String dirName = reader.readLine();

        PlacePackagesCoordinator placePackagesCoordinator =
                new PlacePackagesCoordinator(
                        new PackagesReaderServiceImpl(),
                        new WriterServiceImpl(dirName),
                        new PlacementServiceFactory(WIDTH_BODY, LENGTH_BODY).getPlacementService(selectedAlgorithm)
                );

        logger.info("The placement of packages has begun");
        List<Body> bodies = placePackagesCoordinator.getFilledBodies(numberBodies, filePath);
        logger.info("The placement of packages has been completed");

        for (Body body : bodies) {
            System.out.println(body);
        }
    }
}
