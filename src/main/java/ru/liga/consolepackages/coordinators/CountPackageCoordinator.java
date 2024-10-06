package ru.liga.consolepackages.coordinators;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import ru.liga.consolepackages.DTOs.CountPackageDTO;
import ru.liga.consolepackages.converters.BodiesConverter;
import ru.liga.consolepackages.converters.CountingPackagesConverter;
import ru.liga.consolepackages.models.Body;
import ru.liga.consolepackages.services.packages.CountPackagesService;
import ru.liga.consolepackages.services.readers.BodiesReaderService;

import java.util.List;
import java.util.Map;

@Component
public class CountPackageCoordinator {
    private static final Logger logger = LoggerFactory.getLogger(CountPackageCoordinator.class);
    private final BodiesReaderService readerService;
    private final CountPackagesService countPackagesService;
    private final CountingPackagesConverter countingPackagesConverter;
    private final BodiesConverter bodiesConverter;

    @Autowired
    public CountPackageCoordinator(BodiesReaderService readerService, CountPackagesService countPackagesService, CountingPackagesConverter countingPackagesConverter, BodiesConverter bodiesConverter) {
        this.readerService = readerService;
        this.countPackagesService = countPackagesService;
        this.countingPackagesConverter = countingPackagesConverter;
        this.bodiesConverter = bodiesConverter;
    }

    /**
     * Подсчитывает количество посылок в файле.
     *
     * @param filePath Путь к файлу с данными о посылках в формате JSON.
     * @return Результат подсчета посылок в виде строки.
     */
    public String countPackage(String filePath) {
        logger.debug("start reading file {}", filePath);
        List<Body> bodies = readerService.readBodiesFromJson(filePath);
        logger.debug("end reading file {}", filePath);
        logger.info("Start counting packages");
        Map<Character, Integer> packageIntegerMap = countPackagesService.countPackagesFromBodies(bodies);
        logger.info("End counting packages");
        return countingPackagesConverter.convertPackagesIngerMapToString(packageIntegerMap)
                + "\n"
                + bodiesConverter.fromBodiesToString(bodies);
    }

    /**
     * Подсчитывает количество посылок на основе данных из файла.
     *
     * @param multipartFile Файл с данными о кузовах машин.
     * @return Количество посылок в каждом кузове.
     */
    public CountPackageDTO countPackage(MultipartFile multipartFile) {
        List<Body> bodies = readerService.readBodiesFromJson(multipartFile);
        logger.info("Start counting packages");
        Map<Character, Integer> packageIntegerMap = countPackagesService.countPackagesFromBodies(bodies);
        logger.info("End counting packages");
        return new CountPackageDTO(packageIntegerMap, bodies);
    }
}
