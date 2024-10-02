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

import java.io.IOException;
import java.util.List;

@Component
public class PlacePackagesCoordinator {
    private static final Logger logger = LoggerFactory.getLogger(PlacePackagesCoordinator.class);
    private final PackagesReaderService readerService;
    private final WriterService writerService;
    private final BodiesConverter bodiesConverter;
    private final PackagesConverter packagesConverter;
    @Setter
    private PlacementService placementService;

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
     * @param filePath   путь к файлу, содержащему данные о посылках
     * @return список заполненных кузовов грузовиков
     */
    public List<Body> getFilledBodiesFromFile(String bodiesSize, String filePath) {
        List<Package> packages;
        logger.debug("The file {} is being read", filePath);
        packages = readerService.readPackagesFromTxt(filePath);
        logger.debug("The file has been read successfully");

        List<Body> bodies = placementService.placementPackage(packages, bodiesConverter.fromStringToBodies(bodiesSize));
        logger.debug("Recording of downloaded machines to a file has begun");
        writerService.writeBodies(bodies);
        logger.debug("The recording of the downloaded machines to the file has been completed successfully");

        return bodies;
    }

    /**
     * Размещает пакеты в указанном количестве кузовов из строки.
     *
     * @param bodiesSize размеры кузовов, разделенные пробелом в формате "ширина длина"
     * @param sPackages  строка с идентификаторами пакетов, разделенных пробелом
     * @return список заполненных кузовов
     */
    public List<Body> getFilledBodiesFromString(String bodiesSize, String sPackages) {
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
