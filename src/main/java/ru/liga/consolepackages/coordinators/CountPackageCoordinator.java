package ru.liga.consolepackages.coordinators;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import ru.liga.consolepackages.converters.BodyConverter;
import ru.liga.consolepackages.converters.CountingPackagesConverter;
import ru.liga.consolepackages.dtos.CountPackageDto;
import ru.liga.consolepackages.models.Body;
import ru.liga.consolepackages.services.packages.CountPackagesService;
import ru.liga.consolepackages.services.readers.BodiesReaderService;

import java.io.File;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
public class CountPackageCoordinator {
    private final BodiesReaderService readerService;
    private final CountPackagesService countPackagesService;
    private final CountingPackagesConverter countingPackagesConverter;
    private final BodyConverter bodyConverter;

    @Autowired
    public CountPackageCoordinator(BodiesReaderService readerService, CountPackagesService countPackagesService, CountingPackagesConverter countingPackagesConverter, BodyConverter bodyConverter) {
        this.readerService = readerService;
        this.countPackagesService = countPackagesService;
        this.countingPackagesConverter = countingPackagesConverter;
        this.bodyConverter = bodyConverter;
    }

    /**
     * Подсчитывает количество посылок в файле.
     *
     * @param filePath Путь к файлу с данными о посылках в формате JSON.
     * @return Результат подсчета посылок в виде строки.
     */
    public String countPackage(String filePath) {
        log.debug("start reading file {}", filePath);
        List<Body> bodies = readerService.readBodiesFromJson(filePath);
        log.debug("end reading file {}", filePath);
        log.info("Start counting packages");
        Map<Character, Integer> packageIntegerMap = countPackagesService.countPackagesFromBodies(bodies);
        log.info("End counting packages");
        return countingPackagesConverter.convertPackagesIngerMapToString(packageIntegerMap)
                + "\n"
                + bodyConverter.converter(bodies);
    }

    /**
     * Подсчитывает количество посылок на основе данных из файла.
     *
     * @param multipartFile Файл с данными о кузовах машин.
     * @return Количество посылок в каждом кузове.
     */
    public CountPackageDto countPackage(MultipartFile multipartFile) {
        List<Body> bodies = readerService.readBodiesFromJson(multipartFile);
        log.info("Start counting packages");
        Map<Character, Integer> packageIntegerMap = countPackagesService.countPackagesFromBodies(bodies);
        log.info("End counting packages");
        return new CountPackageDto(packageIntegerMap, bodies);
    }

    public String countPackage(File file) {
        List<Body> bodies = readerService.readBodiesFromJson(file);
        log.info("Start counting packages");
        Map<Character, Integer> packageIntegerMap = countPackagesService.countPackagesFromBodies(bodies);
        log.info("End counting packages");
        return countingPackagesConverter.convertPackagesIngerMapToString(packageIntegerMap)
                + "\n"
                + bodyConverter.converter(bodies);
    }
}
