package ru.liga.consolepackages.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.liga.consolepackages.models.Body;
import ru.liga.consolepackages.services.PlacePackagesCoordinator;
import ru.liga.consolepackages.services.PlacementServiceFactory;
import ru.liga.consolepackages.services.readers.PackagesReaderServiceImpl;
import ru.liga.consolepackages.services.writers.WriterServiceImpl;

import java.util.List;
import java.util.Scanner;

public class PlacementController {
    private static final Logger logger = LoggerFactory.getLogger(PlacementController.class);
    private final Scanner scanner;

    public PlacementController(Scanner scanner) {
        this.scanner = scanner;
    }

    /**
     * Метод отвечающий за принятие ответов от пользователя, размещение посылок,
     * вывод в консоль кузовы машин заполненых посылками.
     *
     * @param widthBody  ширина кузова машин
     * @param lengthBody длина кузова машин
     */
    public void placePackage(int widthBody, int lengthBody) {
        System.out.println("Enter file path");
        String filePath = scanner.nextLine();
        logger.debug("The path to the file has been entered");

        System.out.println("How many cars are available: ");
        int numberBodies = Integer.parseInt(scanner.nextLine());
        logger.debug("The number of bodies entered");

        System.out.println("Uniform or optimal algorithm?");
        System.out.println("u - uniform, o - optimal");
        String selectedAlgorithm = scanner.nextLine();
        logger.debug("The type {} of algorithm entered", selectedAlgorithm);

        PlacePackagesCoordinator placePackagesCoordinator =
                new PlacePackagesCoordinator(
                        new PackagesReaderServiceImpl(),
                        new WriterServiceImpl(),
                        new PlacementServiceFactory().getPlacementService(selectedAlgorithm)
                );

        logger.info("The placement of packages has begun");
        List<Body> bodies = placePackagesCoordinator.getFilledBodies(numberBodies, widthBody, lengthBody, filePath);
        logger.info("The placement of packages has been completed");

        for (Body body : bodies) {
            System.out.println(body);
        }
    }
}
