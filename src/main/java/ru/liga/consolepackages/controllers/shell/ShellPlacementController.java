package ru.liga.consolepackages.controllers.shell;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.liga.consolepackages.coordinators.PlacePackagesCoordinator;

@ShellComponent
public class ShellPlacementController {
    private static final Logger logger = LoggerFactory.getLogger(ShellPlacementController.class);
    private final PlacePackagesCoordinator coordinator;

    public ShellPlacementController(PlacePackagesCoordinator coordinator) {
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
            String bodies = coordinator.getFilledBodiesFromFile(bodiesSize, filePath, selectedAlgorithm);
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
            String bodies = coordinator.getFilledBodiesFromString(bodiesSize, packages, selectedAlgorithm);
            logger.info("The placement of packages has been completed");
            return bodies;

        } catch (RuntimeException e) {
            logger.warn(e.getMessage(), e);
            return e.getMessage();
        }
    }
}
