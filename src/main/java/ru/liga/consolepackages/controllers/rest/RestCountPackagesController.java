package ru.liga.consolepackages.controllers.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.liga.consolepackages.DTOs.ResponseDTO;
import ru.liga.consolepackages.coordinators.CountPackageCoordinator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

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
            return new ResponseEntity<>(coordinator.countPackage(multipartFile), HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(new ResponseDTO(e.getMessage()),HttpStatus.BAD_REQUEST);
        }
    }
}
