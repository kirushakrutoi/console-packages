package ru.liga.consolepackages.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.liga.consolepackages.controllers.CountPackagesController;
import ru.liga.consolepackages.models.Body;
import ru.liga.consolepackages.models.Package;
import ru.liga.consolepackages.services.readers.BodiesReaderService;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class CountPackageCoordinator {
    private static final Logger logger = LoggerFactory.getLogger(CountPackageCoordinator.class);
    private final BodiesReaderService readerService;
    private final CountPackagesService countPackagesService;

    public CountPackageCoordinator(BodiesReaderService readerService, CountPackagesService countPackagesService) {
        this.readerService = readerService;
        this.countPackagesService = countPackagesService;
    }

    public Map<Package, Integer> countPackage(String filePath) throws IOException {
        logger.debug("start reading file " + filePath);
        List<Body> bodies = readerService.readBodiesFromJson(new File(filePath));
        logger.debug("end reading file " + filePath);

        return countPackagesService.countPackagesFromBodies(bodies);
    }
}
