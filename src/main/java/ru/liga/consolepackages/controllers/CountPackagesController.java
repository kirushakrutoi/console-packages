package ru.liga.consolepackages.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.liga.consolepackages.models.Package;
import ru.liga.consolepackages.services.CountPackageCoordinator;
import ru.liga.consolepackages.services.CountPackagesService;
import ru.liga.consolepackages.services.readers.ReaderService;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Map;

public class CountPackagesController {
    private final BufferedReader reader;
    private static final Logger logger = LoggerFactory.getLogger(CountPackagesController.class);

    public CountPackagesController(BufferedReader reader) {
        this.reader = reader;
    }

    public void countPackages() throws IOException {
        System.out.println("Enter file name");
        String filePath = reader.readLine();
        logger.debug("The path to the file has been entered");

        logger.debug("Start counting packages");
        CountPackageCoordinator countPackageCoordinator =
                new CountPackageCoordinator(
                        new ReaderService(),
                        new CountPackagesService()
                );
        Map<Package, Integer> packageIntegerMap = countPackageCoordinator.countPackage(filePath);
        logger.debug("End counting packages");

        for (Map.Entry<Package, Integer> entry : packageIntegerMap.entrySet()) {
            System.out.println("Package of type " + entry.getKey().getSymbol() + " found " + entry.getValue());
        }
    }

}
