package ru.liga.consolepackages.controllers.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.liga.consolepackages.DTOs.ChangePackageDTO;
import ru.liga.consolepackages.DTOs.NewPackageDTO;
import ru.liga.consolepackages.DTOs.ResponseDTO;
import ru.liga.consolepackages.exceptions.pacakgesexceptions.PackageNotFoundException;
import ru.liga.consolepackages.services.packages.PackageService;

@Slf4j
@RestController()
@RequestMapping("/packages")
public class RestPackageController {
    private final PackageService packageService;

    public RestPackageController(PackageService packageService) {
        this.packageService = packageService;
    }

    /**
     * Возвращает список всех посылок.
     *
     * @return Список всех посылок.
     */
    @GetMapping
    public ResponseEntity<?> findAll() {
        try {
            return new ResponseEntity<>(packageService.getAll(), HttpStatus.OK);
        } catch (RuntimeException e) {
            log.warn(e.getMessage());
            return new ResponseEntity<>(new ResponseDTO(e.getMessage()), HttpStatus.BAD_GATEWAY);
        }
    }

    /**
     * Возвращает посылку по ее имени.
     *
     * @param name Имя посылки.
     * @return Посылка с указанным именем.
     */
    @GetMapping("/{name}")
    public ResponseEntity<?> findByName(@PathVariable("name") String name) {
        try {
            return new ResponseEntity<>(packageService.findByName(name), HttpStatus.OK);
        } catch (PackageNotFoundException e) {
            log.warn(e.getMessage());
            return new ResponseEntity<>(new ResponseDTO(e.getMessage()), HttpStatus.NOT_FOUND);
        } catch (RuntimeException e) {
            log.warn(e.getMessage());
            return new ResponseEntity<>(new ResponseDTO(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Создает новую посылку.
     *
     * @param newPackageDTO Данные новой посылки.
     * @return Ответ с сообщением об успешном создании.
     */
    @PostMapping
    public ResponseEntity<?> create(@RequestBody NewPackageDTO newPackageDTO) {
        try {
            packageService.create(newPackageDTO);
            return new ResponseEntity<>(new ResponseDTO("Successful created"), HttpStatus.CREATED);
        } catch (RuntimeException e) {
            log.warn(e.getMessage());
            return new ResponseEntity<>(new ResponseDTO(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Изменяет данные посылки по ее имени.
     *
     * @param name             Имя посылки.
     * @param changePackageDTO Измененные данные посылки.
     * @return Ответ с сообщением об успешном изменении.
     */
    @PatchMapping("/{name}")
    public ResponseEntity<?> change(@PathVariable("name") String name, @RequestBody ChangePackageDTO changePackageDTO) {
        try {
            packageService.change(name, changePackageDTO);
            return new ResponseEntity<>(new ResponseDTO("Successful changed"), HttpStatus.ACCEPTED);
        } catch (PackageNotFoundException e) {
            log.warn(e.getMessage());
            return new ResponseEntity<>(new ResponseDTO(e.getMessage()), HttpStatus.NOT_FOUND);
        } catch (RuntimeException e) {
            log.warn(e.getMessage());
            return new ResponseEntity<>(new ResponseDTO(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Удаляет посылку по ее имени.
     *
     * @param name Имя посылки.
     * @return Ответ с сообщением об успешном удалении.
     */
    @DeleteMapping("/{name}")
    public ResponseEntity<?> delete(@PathVariable("name") String name) {
        try {
            packageService.delete(name);
            return new ResponseEntity<>(new ResponseDTO("Successful deleted"), HttpStatus.OK);
        } catch (PackageNotFoundException e) {
            log.warn(e.getMessage());
            return new ResponseEntity<>(new ResponseDTO(e.getMessage()), HttpStatus.NOT_FOUND);
        } catch (RuntimeException e) {
            log.warn(e.getMessage());
            return new ResponseEntity<>(new ResponseDTO(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
}
