package ru.liga.consolepackages.coordinators;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import ru.liga.consolepackages.DTOs.PlacementRequestDTO;
import ru.liga.consolepackages.DTOs.PlacementResponseDTO;
import ru.liga.consolepackages.converters.BodyConverter;
import ru.liga.consolepackages.converters.PackagesConverter;
import ru.liga.consolepackages.models.Body;
import ru.liga.consolepackages.models.Package;
import ru.liga.consolepackages.services.placements.PlacementService;
import ru.liga.consolepackages.services.readers.PackagesReaderService;
import ru.liga.consolepackages.services.writers.WriterService;

import java.util.List;
import java.util.Map;

@Slf4j
@Component
public class PlacePackagesCoordinator {
    private final PackagesReaderService readerService;
    private final WriterService writerService;
    private final BodyConverter bodyConverter;
    private final PackagesConverter packagesConverter;
    private final Map<String, PlacementService> placementServiceMap;

    @Autowired
    public PlacePackagesCoordinator(PackagesReaderService readerService, WriterService writerService, BodyConverter bodyConverter, PackagesConverter packagesConverter, Map<String, PlacementService> placementServiceMap) {
        this.readerService = readerService;
        this.writerService = writerService;
        this.bodyConverter = bodyConverter;
        this.packagesConverter = packagesConverter;
        this.placementServiceMap = placementServiceMap;
    }

    /**
     * Получает заполненные тела из строки с размерами кузовов и файла с типами посылок.
     *
     * @param bodiesSize Строка, содержащая размеры тел.
     * @param filePath   Путь к файлу с типами посылок.
     * @return Результат размещения посылок в телах в виде строки.
     */
    public String getFilledBodiesFromFile(String bodiesSize, String filePath, String selectedAlgorithm) {
        log.debug("The file {} is being read", filePath);
        PlacementService placementService = placementServiceMap.get(selectedAlgorithm);
        List<Package> packages = readerService.readPackagesFromTxt(filePath);
        log.debug("The file has been read successfully");
        List<Body> bodies = placementAndWrite(packages, bodiesSize, placementService);

        return bodyConverter.converter(bodies);
    }

    /**
     * Получает заполненные тела из строк, содержащих размеры кузовов и типы посылок.
     *
     * @param bodiesSize размеры кузовов, разделенные пробелом в формате "ширинаxдлина"
     * @param sPackages  Строка, содержащая типы посылок.
     * @return Результат размещения посылок в телах в виде строки.
     */
    public String getFilledBodiesFromString(String bodiesSize, String sPackages, String selectedAlgorithm) {
        PlacementService placementService = placementServiceMap.get(selectedAlgorithm);
        List<Package> packages = packagesConverter.convertFromString(sPackages);
        List<Body> bodies = placementAndWrite(packages, bodiesSize, placementService);

        return bodyConverter.converter(bodies);
    }


    /**
     * Размещает посылки в кузова машин на основе данных из файла и строки содержащей размеры кузовов.
     *
     * @param bodiesSize Размер кузовов.
     * @param file       Файл с данными о посылках.
     * @return Ответ с результатом размещения.
     */
    public PlacementResponseDTO getFilledBodiesFromFile(String bodiesSize, MultipartFile file, String selectedAlgorithm) {
        List<Package> packages = readerService.readPackagesFromTxt(file);
        PlacementService placementService = placementServiceMap.get(selectedAlgorithm);
        log.debug("The file has been read successfully");
        List<Body> bodies = placementAndWrite(packages, bodiesSize, placementService);

        return new PlacementResponseDTO(bodies);
    }

    /**
     * Размещает посылки в кузова машин на основе данных из строки.
     *
     * @param placementRequestDTO Данные о посылках и размерах кузовов.
     * @return Ответ с результатом размещения.
     */
    public PlacementResponseDTO getFilledBodiesFromString(PlacementRequestDTO placementRequestDTO) {
        List<Package> packages = packagesConverter.convertFromString(placementRequestDTO.getPackagesNames());
        PlacementService placementService = placementServiceMap.get(placementRequestDTO.getSelectedAlgorithm());
        List<Body> bodies = placementAndWrite(packages, placementRequestDTO.getBodiesSize(), placementService);

        return new PlacementResponseDTO(bodies);
    }


    private List<Body> placementAndWrite(List<Package> packages, String bodiesSize, PlacementService placementService) {
        List<Body> bodies = placementService.placementPackage(packages, bodyConverter.converter(bodiesSize));
        log.debug("Recording of downloaded machines to a file has begun");
        writerService.writeBodies(bodies);
        log.debug("The recording of the downloaded machines to the file has been completed successfully");
        return bodies;
    }
}
