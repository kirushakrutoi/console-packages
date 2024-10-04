package ru.liga.consolepackages.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.liga.consolepackages.coordinators.CountPackageCoordinator;

@ShellComponent
public class CountPackagesController {
    private static final Logger logger = LoggerFactory.getLogger(CountPackagesController.class);
    private final CountPackageCoordinator coordinator;

    public CountPackagesController(CountPackageCoordinator coordinator) {
        this.coordinator = coordinator;
    }

    /**
     * Метод для подсчета посылок.
     *
     * @param filePath Путь к файлу с данными о кузовах машин.
     * @return Результат подсчета посылок или сообщение об ошибке.
     */
    @ShellMethod("Метод для подсчета посылок")
    public String countPackages(String filePath) {
        try {
            logger.debug("The path to the file {} has been entered", filePath);
            return coordinator.countPackage(filePath);
        } catch (RuntimeException e) {
            logger.warn(e.getMessage());
            return e.getMessage();
        }
    }

}
