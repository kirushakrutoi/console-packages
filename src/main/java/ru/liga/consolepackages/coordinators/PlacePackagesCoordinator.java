package ru.liga.consolepackages.coordinators;

import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.liga.consolepackages.converters.BodiesConverter;
import ru.liga.consolepackages.converters.PackagesConverter;
import ru.liga.consolepackages.models.Body;
import ru.liga.consolepackages.models.Package;
import ru.liga.consolepackages.services.placements.PlacementService;
import ru.liga.consolepackages.services.readers.PackagesReaderService;
import ru.liga.consolepackages.services.writers.WriterService;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

@Component
public class PlacePackagesCoordinator {
    private final PackagesReaderService readerService;
    private final WriterService writerService;
    @Setter
    private PlacementService placementService;
    private final BodiesConverter bodiesConverter;
    private final PackagesConverter packagesConverter;
    private static final Logger logger = LoggerFactory.getLogger(PlacePackagesCoordinator.class);

    @Autowired
    public PlacePackagesCoordinator(PackagesReaderService readerService, WriterService writerService, BodiesConverter bodiesConverter, PackagesConverter packagesConverter) {
        this.readerService = readerService;
        this.writerService = writerService;
        this.bodiesConverter = bodiesConverter;
        this.packagesConverter = packagesConverter;
    }

    /**
     * Метод для получения заполненных кузовов грузовиков.
     *
     * @param bodiesSize размеры кузовов грузовиков
     * @param filePath     путь к файлу, содержащему данные о посылках
     * @return список заполненных кузовов грузовиков
     * @throws IOException если возникла проблема при чтении входного файла или записи результатов
     */
    public List<Body> getFilledBodiesFromFile(String bodiesSize, String filePath) throws IOException {
        List<Package> packages;
        try {
            logger.debug("The file {} is being read", filePath);
            packages = readerService.readPackagesFromTxt(filePath);
            logger.debug("The file has been read successfully");
        } catch (IOException e) {
            logger.debug("Failed to read the file {}", filePath);
            logger.warn(e.getMessage());
            throw new FileNotFoundException("File " + filePath + " not found");
        }

        List<Body> bodies = placementService.placementPackage(packages, bodiesConverter.fromStringToBodies(bodiesSize));
        logger.debug("Recording of downloaded machines to a file has begun");
        writerService.writeBodies(bodies);
        logger.debug("The recording of the downloaded machines to the file has been completed successfully");

        return bodies;
    }

    public List<Body> getFilledBodiesFromString(String bodiesSize, String sPackages) throws IOException {
        List<Package> packages;
        //logger.debug("The file {} is being read");
        packages = packagesConverter.convertFromString(sPackages);
        //logger.debug("The file has been read successfully");

        List<Body> bodies = placementService.placementPackage(packages, bodiesConverter.fromStringToBodies(bodiesSize));
        logger.debug("Recording of downloaded machines to a file has begun");
        writerService.writeBodies(bodies);
        logger.debug("The recording of the downloaded machines to the file has been completed successfully");

        return bodies;
    }
}
