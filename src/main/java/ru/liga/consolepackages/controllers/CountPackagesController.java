package ru.liga.consolepackages.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.liga.consolepackages.services.CountPackageCoordinator;
import ru.liga.consolepackages.services.CountPackagesService;
import ru.liga.consolepackages.services.readers.BodiesReaderServiceImpl;

import java.util.Map;
import java.util.Scanner;

public class CountPackagesController {
    private static final Logger logger = LoggerFactory.getLogger(CountPackagesController.class);
    private final Scanner scanner;

    public CountPackagesController(Scanner scanner) {
        this.scanner = scanner;
    }

    /**
     * Метод отвечающий за принятие ответов от пользователя и для подсчета посылок.
     */
    public void countPackages() {
        System.out.println("Enter file name");
        String filePath = scanner.nextLine();
        logger.debug("The path to the file has been entered");

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
