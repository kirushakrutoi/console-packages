package ru.liga.consolepackages.controllers.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.liga.consolepackages.coordinators.PlacePackagesCoordinator;
import ru.liga.consolepackages.dtos.PlacementRequestDto;
import ru.liga.consolepackages.dtos.PlacementResponseDto;
import ru.liga.consolepackages.dtos.ResponseDto;

import java.io.IOException;

@Slf4j
@RestController()
@RequestMapping("/placement")
public class RestPlacementController {
    private final PlacePackagesCoordinator coordinator;

    public RestPlacementController(PlacePackagesCoordinator coordinator) {
        this.coordinator = coordinator;
    }

    /**
     * Размещает посылки в кузова машин на основе данных из файла.
     *
     * @param multipartFile Файл с данными о посылках.
     * @param algorithm     Название алгоритма размещения.
     * @param bodiesSize    Размер кузовов.
     * @return Ответ с результатом размещения.
     */
    @GetMapping("/from/file")
    public ResponseEntity<?> placePackageFromFile(@RequestPart("file") MultipartFile multipartFile,
                                                  @RequestParam("selected_algorithm") String algorithm,
                                                  @RequestParam("bodies_size") String bodiesSize) {
        try {
            log.info("Place packages from file");
            PlacementResponseDto placementResponseDTO =
                    coordinator.getFilledBodiesFromFile(
                            bodiesSize,
                            multipartFile.getBytes(),
                            algorithm
                    );
            return new ResponseEntity<>(placementResponseDTO, HttpStatus.OK);
        } catch (RuntimeException | IOException e) {
            log.warn(e.getMessage());
            return new ResponseEntity<>(new ResponseDto(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Размещение тел из JSON-строки.
     *
     * @param placementRequestDTO Запрос на размещение тел.
     * @return Ответ с размещенными телами.
     */
    @GetMapping("/from/json")
    public ResponseEntity<?> placePackageFromJson(@RequestBody PlacementRequestDto placementRequestDTO) {
        try {
            log.info("Place package from json");
            PlacementResponseDto placementResponseDTO =
                    coordinator.getFilledBodiesFromString(placementRequestDTO);
            return new ResponseEntity<>(placementResponseDTO, HttpStatus.OK);
        } catch (RuntimeException e) {
            log.warn(e.getMessage());
            return new ResponseEntity<>(new ResponseDto(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
}
