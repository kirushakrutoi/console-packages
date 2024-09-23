package ru.liga.consolepackages.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.liga.consolepackages.exceptions.FailedReadFileException;
import ru.liga.consolepackages.services.CountPackageCoordinator;
import ru.liga.consolepackages.services.CountPackagesService;
import ru.liga.consolepackages.services.readers.BodiesReaderServiceImpl;
import ru.liga.consolepackages.services.readers.PackagesReaderServiceImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Map;

public class CountPackagesController {
    private final BufferedReader reader;
    private static final Logger logger = LoggerFactory.getLogger(CountPackagesController.class);

    public CountPackagesController(BufferedReader reader) {
        this.reader = reader;
    }

    public void countPackages() throws FailedReadFileException, IOException {
        System.out.println("Enter file name");
        String filePath = reader.readLine();
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
