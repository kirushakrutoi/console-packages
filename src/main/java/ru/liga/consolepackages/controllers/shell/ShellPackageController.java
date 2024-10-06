package ru.liga.consolepackages.controllers.shell;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.liga.consolepackages.services.packages.PackageService;

import java.util.stream.Collectors;

@ShellComponent
public class ShellPackageController {
    private static final Logger logger = LoggerFactory.getLogger(ShellPackageController.class);
    private final PackageService packageService;

    @Autowired
    public ShellPackageController(PackageService packageService) {
        this.packageService = packageService;
    }

    /**
     * Метод для получения всех посылок.
     *
     * @return строка, содержащая все посылки, разделенные символом новой строки
     */
    @ShellMethod("Метод для получения всех посылок")
    public String getAll() {
        logger.info("Get all packages");
        try {
            return packageService.getAll()
                    .stream()
                    .map(Object::toString)
                    .collect(Collectors.joining("\n"));
        } catch (RuntimeException e) {
            logger.warn(e.getMessage());
            return e.getMessage();
        }
    }

    /**
     * Метод для получения посылки по id.
     *
     * @param name идентификатор посылки
     * @return посылка с указанным идентификатором
     */
    @ShellMethod("Метод для получения посылки по id")
    public String getById(String name) {
        logger.info("Get package by id - {}", name);
        try {
            return packageService.findByName(name).toString();
        } catch (RuntimeException e) {
            logger.warn(e.getMessage());
            return e.getMessage();
        }
    }

    /**
     * Метод для изменения параметров посылки.
     *
     * @param id      идентификатор посылки для изменения
     * @param newPack новые параметры посылки
     */
    @ShellMethod("Метод для изменения параметров посылки")
    public String change(String id, String newPack) {
        logger.info("Change package with id - {}", id);
        try {
            packageService.change(id, newPack);
            return "Successful changed";
        } catch (RuntimeException e) {
            logger.warn(e.getMessage());
            return e.getMessage();
        }
    }

    /**
     * Метод для создания посылки.
     *
     * @param newPack параметры новой посылки
     */
    @ShellMethod("Метод для создания посылки")
    public String create(String newPack) {
        logger.info("Create new package");
        try {
            packageService.create(newPack);
            return "Successful created";
        } catch (RuntimeException e) {
            logger.warn(e.getMessage());
            return e.getMessage();
        }
    }

    /**
     * Метод для удаления посылки.
     *
     * @param id идентификатор посылки для удаления
     */
    @ShellMethod("Метод для удаления посылки")
    public String delete(String id) {
        logger.info("Delete package with id {}", id);
        try {
            packageService.delete(id);
            return "Successful deleted";
        } catch (RuntimeException e) {
            logger.warn(e.getMessage());
            return e.getMessage();
        }
    }
}
