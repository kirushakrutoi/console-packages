package ru.liga.consolepackages.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.liga.consolepackages.ConsolePackages;
import ru.liga.consolepackages.models.Body;
import ru.liga.consolepackages.services.PlacePackagesCoordinator;
import ru.liga.consolepackages.services.readers.ReaderService;
import ru.liga.consolepackages.services.writers.WriterServiceImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

public class PlacementController {

    private final int LENGTH_BODY;
    private final int WIDTH_BODY;
    private final String DIR_FOR_WRITE;
    private final BufferedReader reader;
    private static final Logger logger = LoggerFactory.getLogger(PlacementController.class);

    public PlacementController(int lengthBody, int widthBody, String dirForWrite, BufferedReader reader) {
        LENGTH_BODY = lengthBody;
        WIDTH_BODY = widthBody;
        DIR_FOR_WRITE = dirForWrite;
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
        String answer = reader.readLine();
        logger.debug("The type " + answer + " of algorithm entered");

        PlacePackagesCoordinator placePackagesCoordinator =
                new PlacePackagesCoordinator(
                        WIDTH_BODY,
                        LENGTH_BODY,
                        new ReaderService(),
                        new WriterServiceImpl(DIR_FOR_WRITE)
                );

        logger.info("The placement of packages has begun");
        List<Body> bodies = placePackagesCoordinator.getFilledBodies(numberBodies, answer, filePath);
        logger.info("The placement of packages has been completed");

        for (Body body : bodies) {
            System.out.println(body);
        }
    }
}
