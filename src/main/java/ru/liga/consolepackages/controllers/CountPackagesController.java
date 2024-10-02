package ru.liga.consolepackages.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.liga.consolepackages.coordinators.CountPackageCoordinator;
import ru.liga.consolepackages.services.packages.CountPackagesService;
import ru.liga.consolepackages.services.readers.BodiesReaderServiceImpl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

@ShellComponent
public class CountPackagesController {
    private final CountPackageCoordinator coordinator;
    private static final Logger logger = LoggerFactory.getLogger(CountPackagesController.class);

    public CountPackagesController(CountPackageCoordinator coordinator) {
        this.coordinator = coordinator;
    }

    /**
     * Метод вечающий за принятие ответов от пользователя и для подсчета посылок.
     */
    @ShellMethod("Метод для подсчета посылок")
    public void countPackages(String filePath) {
        logger.debug("The path to the file has been entered");
        logger.info("Start counting packages");

        logger.debug("Start counting packages");
        Map<Character, Integer> packageIntegerMap = coordinator.countPackage(filePath);
        logger.debug("End counting packages");

        for (Map.Entry<Character, Integer> entry : packageIntegerMap.entrySet()) {
            System.out.println("Package of type " + entry.getKey() + " found " + entry.getValue());
        }
    }

}
