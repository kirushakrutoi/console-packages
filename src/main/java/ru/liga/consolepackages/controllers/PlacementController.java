package ru.liga.consolepackages.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.liga.consolepackages.coordinators.PlacePackagesCoordinator;
import ru.liga.consolepackages.utils.PlacementServiceFactory;

@ShellComponent
public class PlacementController {
    private static final Logger logger = LoggerFactory.getLogger(PlacementController.class);
    private final PlacePackagesCoordinator coordinator;

    public PlacementController(PlacePackagesCoordinator coordinator) {
        this.coordinator = coordinator;
    }

    /**
     * Метод для размещения посылок.
     *
     * @param filePath          Путь к файлу с посылками.
     * @param selectedAlgorithm Выбранный алгоритм размещения.
     * @param bodiesSize        Строка, содержащая размеры доступного пространства.
     * @return Результат размещения посылок или сообщение об ошибке.
     */
    @ShellMethod("Метод для размещения посылок")
    public String placePackageFromFile(String filePath, String selectedAlgorithm, String bodiesSize) {
        try {
            logger.debug("The path to the file {} has been entered", filePath);
            logger.debug("The type {} of algorithm entered", selectedAlgorithm);
            logger.info("The placement of packages has begun");
            coordinator.setPlacementService(new PlacementServiceFactory().getPlacementService(selectedAlgorithm));
            String bodies = coordinator.getFilledBodiesFromFile(bodiesSize, filePath);
            logger.info("The placement of packages has been completed");

            return bodies;

        } catch (RuntimeException e) {
            logger.warn(e.getMessage());
            return e.getMessage();
        }
    }

    /**
     * Метод для размещения посылок.
     *
     * @param packages          Строка, содержащая типы посылок.
     * @param selectedAlgorithm Выбранный алгоритм размещения.
     * @param bodiesSize        размеры кузовов, разделенные пробелом в формате "ширинаxдлина"
     * @return Результат размещения посылок или сообщение об ошибке.
     */
    @ShellMethod("Метод для размещения посылок")
    public String placePackageById(String packages, String selectedAlgorithm, String bodiesSize) {
        try {
            logger.debug("The type {} of algorithm entered", selectedAlgorithm);
            logger.info("The placement of packages has begun");
            coordinator.setPlacementService(new PlacementServiceFactory().getPlacementService(selectedAlgorithm));
            String bodies = coordinator.getFilledBodiesFromString(bodiesSize, packages);
            logger.info("The placement of packages has been completed");
            return bodies;

        } catch (RuntimeException e) {
            logger.warn(e.getMessage());
            return e.getMessage();
        }
    }
}
