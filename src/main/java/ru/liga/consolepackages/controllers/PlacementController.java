package ru.liga.consolepackages.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.liga.consolepackages.coordinators.PlacePackagesCoordinator;
import ru.liga.consolepackages.models.Body;
import ru.liga.consolepackages.services.readers.PackagesReaderServiceImpl;
import ru.liga.consolepackages.services.writers.WriterServiceImpl;
import ru.liga.consolepackages.utils.PlacementServiceFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

@ShellComponent
public class PlacementController {

    private final int LENGTH_BODY = 6;
    private final int WIDTH_BODY = 6;
    private static final Logger logger = LoggerFactory.getLogger(PlacementController.class);
    private final PlacePackagesCoordinator coordinator;

    public PlacementController(PlacePackagesCoordinator coordinator) {
        this.coordinator = coordinator;
    }

    /**
     * Метод отвечающий за принятие ответов от пользователя, размещение посылок,
     * вывод в консоль кузовы машин заполненых посылками.
     *
     * @throws IOException           если возникает проблема с чтением из консоли.
     * @throws FileNotFoundException если файл для чтения не найден.
     */
    @ShellMethod("Метод для размещения посылок")
    public void placePackageFromFile(String filePath, String selectedAlgorithm, String bodiesSize) throws FileNotFoundException, IOException {
        logger.debug("The path to the file has been entered");

        logger.debug("The number of bodies entered");

        logger.debug("The type {} of algorithm entered", selectedAlgorithm);

        logger.info("The placement of packages has begun");
        coordinator.setPlacementService(new PlacementServiceFactory().getPlacementService(selectedAlgorithm));
        List<Body> bodies = coordinator.getFilledBodiesFromFile(bodiesSize, filePath);
        logger.info("The placement of packages has been completed");

        for (Body body : bodies) {
            System.out.println(body);
        }
    }

    @ShellMethod("Метод для размещения посылок")
    public void placePackageById(String packages, String selectedAlgorithm, String bodiesSize) throws FileNotFoundException, IOException {
        logger.debug("The path to the file has been entered");

        logger.debug("The number of bodies entered");

        logger.debug("The type {} of algorithm entered", selectedAlgorithm);

        logger.info("The placement of packages has begun");
        coordinator.setPlacementService(new PlacementServiceFactory().getPlacementService(selectedAlgorithm));
        List<Body> bodies = coordinator.getFilledBodiesFromString(bodiesSize, packages);
        logger.info("The placement of packages has been completed");

        for (Body body : bodies) {
            System.out.println(body);
        }
    }
}
