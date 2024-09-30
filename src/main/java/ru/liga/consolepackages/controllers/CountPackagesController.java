package ru.liga.consolepackages.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.liga.consolepackages.coordinators.CountPackageCoordinator;
import ru.liga.consolepackages.services.CountPackagesService;
import ru.liga.consolepackages.services.readers.BodiesReaderServiceImpl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

@ShellComponent
public class CountPackagesController {
    private static final Logger logger = LoggerFactory.getLogger(CountPackagesController.class);

    public CountPackagesController() {

    }

    /**
     * Метод вечающий за принятие ответов от пользователя и для подсчета посылок.
     *
     * @throws IOException если возникла проблема с чтением из консоли.
     */
    @ShellMethod("Метод для подсчета посылок")
    public void countPackages(String filePath) throws FileNotFoundException, IOException {
        logger.debug("The path to the file has been entered");
        logger.info("Start counting packages");

        logger.debug("Start counting packages");
        CountPackageCoordinator countPackageCoordinator =
                new CountPackageCoordinator(
                        new BodiesReaderServiceImpl(),
                        new CountPackagesService()
                );
        Map<Character, Integer> packageIntegerMap = countPackageCoordinator.countPackage(filePath);
        logger.debug("End counting packages");

        for (Map.Entry<Character, Integer> entry : packageIntegerMap.entrySet()) {
            System.out.println("Package of type " + entry.getKey() + " found " + entry.getValue());
        }
    }

}
