package ru.liga.consolepackages.controllers.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.liga.consolepackages.dtos.ChangePackageDto;
import ru.liga.consolepackages.dtos.NewPackageDto;
import ru.liga.consolepackages.dtos.ResponseDto;
import ru.liga.consolepackages.exceptions.pacakgesexceptions.PackageNotFoundException;
import ru.liga.consolepackages.mappers.PackageMapper;
import ru.liga.consolepackages.services.packages.PackageService;

@Slf4j
@RestController()
@RequestMapping("/packages")
public class RestPackageController {
    private final PackageService packageService;
    private final PackageMapper packageMapper;

    public RestPackageController(PackageService packageService, PackageMapper packageMapper) {
        this.packageService = packageService;
        this.packageMapper = packageMapper;
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
            return new ResponseEntity<>(new ResponseDto(e.getMessage()), HttpStatus.BAD_GATEWAY);
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
            return new ResponseEntity<>(new ResponseDto(e.getMessage()), HttpStatus.NOT_FOUND);
        } catch (RuntimeException e) {
            log.warn(e.getMessage());
            return new ResponseEntity<>(new ResponseDto(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Создает новую посылку.
     *
     * @param newPackageDTO Данные новой посылки.
     * @return Ответ с сообщением об успешном создании.
     */
    @PostMapping
    public ResponseEntity<?> create(@RequestBody NewPackageDto newPackageDTO) {
        try {

            packageService.create(packageMapper.fromNewDtoToPackage(newPackageDTO));
            return new ResponseEntity<>(new ResponseDto("Successful created"), HttpStatus.CREATED);
        } catch (RuntimeException e) {
            log.warn(e.getMessage());
            return new ResponseEntity<>(new ResponseDto(e.getMessage()), HttpStatus.BAD_REQUEST);
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
    public ResponseEntity<?> change(@PathVariable("name") String name, @RequestBody ChangePackageDto changePackageDTO) {
        try {
            packageService.change(name, packageMapper.fromChangeDtoToPackage(changePackageDTO));
            return new ResponseEntity<>(new ResponseDto("Successful changed"), HttpStatus.ACCEPTED);
        } catch (PackageNotFoundException e) {
            log.warn(e.getMessage());
            return new ResponseEntity<>(new ResponseDto(e.getMessage()), HttpStatus.NOT_FOUND);
        } catch (RuntimeException e) {
            log.warn(e.getMessage());
            return new ResponseEntity<>(new ResponseDto(e.getMessage()), HttpStatus.BAD_REQUEST);
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
            return new ResponseEntity<>(new ResponseDto("Successful deleted"), HttpStatus.OK);
        } catch (PackageNotFoundException e) {
            log.warn(e.getMessage());
            return new ResponseEntity<>(new ResponseDto(e.getMessage()), HttpStatus.NOT_FOUND);
        } catch (RuntimeException e) {
            log.warn(e.getMessage());
            return new ResponseEntity<>(new ResponseDto(e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }
}
