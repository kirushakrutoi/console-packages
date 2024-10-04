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
     * Получает заполненные тела из строки с размерами тел и файла с типами посылок.
     *
     * @param bodiesSize Строка, содержащая размеры тел.
     * @param filePath   Путь к файлу с типами посылок.
     * @return Результат размещения посылок в телах в виде строки.
     */
    public String getFilledBodiesFromFile(String bodiesSize, String filePath) {
        List<Package> packages;
        logger.debug("The file {} is being read", filePath);
        packages = readerService.readPackagesFromTxt(filePath);
        logger.debug("The file has been read successfully");
        List<Body> bodies = placementService.placementPackage(packages, bodiesConverter.fromStringToBodies(bodiesSize));
        logger.debug("Recording of downloaded machines to a file has begun");
        writerService.writeBodies(bodies);
        logger.debug("The recording of the downloaded machines to the file has been completed successfully");

        return bodiesConverter.fromBodiesToString(bodies);
    }

    /**
     * Получает заполненные тела из строк, содержащих размеры тел и типы посылок.
     *
     * @param bodiesSize размеры кузовов, разделенные пробелом в формате "ширинаxдлина"
     * @param sPackages  Строка, содержащая типы посылок.
     * @return Результат размещения посылок в телах в виде строки.
     */
    public String getFilledBodiesFromString(String bodiesSize, String sPackages) {
        List<Package> packages;
        packages = packagesConverter.convertFromString(sPackages);

        List<Body> bodies = placementService.placementPackage(packages, bodiesConverter.fromStringToBodies(bodiesSize));
        logger.debug("Recording of downloaded machines to a file has begun");
        writerService.writeBodies(bodies);
        logger.debug("The recording of the downloaded machines to the file has been completed successfully");

        return bodiesConverter.fromBodiesToString(bodies);
    }
}
