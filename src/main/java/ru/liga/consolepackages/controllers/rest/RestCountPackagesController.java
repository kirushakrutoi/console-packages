package ru.liga.consolepackages.controllers.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import ru.liga.consolepackages.DTOs.ResponseDTO;
import ru.liga.consolepackages.coordinators.CountPackageCoordinator;

@Slf4j
@RestController()
@RequestMapping("/count")
public class RestCountPackagesController {
    private final CountPackageCoordinator coordinator;

    @Autowired
    public RestCountPackagesController(CountPackageCoordinator packageCoordinator) {
        this.coordinator = packageCoordinator;
    }

    /**
     * Подсчитывает количество посылок в файле.
     *
     * @param multipartFile Файл с заполненными кузовами машин.
     * @return Количество посылок в файле.
     */
    @GetMapping
    public ResponseEntity<?> countPackages(@RequestPart("file") MultipartFile multipartFile) {
        try {
            log.info("Count package");
            return new ResponseEntity<>(coordinator.countPackage(multipartFile), HttpStatus.OK);
        } catch (RuntimeException e) {
            log.warn(e.getMessage());
            return new ResponseEntity<>(new ResponseDTO(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
}
