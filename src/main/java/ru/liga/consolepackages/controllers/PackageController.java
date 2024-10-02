package ru.liga.consolepackages.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.liga.consolepackages.models.Package;
import ru.liga.consolepackages.services.packages.PackageService;

import java.util.stream.Collectors;

@ShellComponent
public class PackageController {
    private final PackageService packageService;

    @Autowired
    public PackageController(PackageService packageService) {
        this.packageService = packageService;
    }

    /**
     * Метод для получения всех посылок.
     *
     * @return строка, содержащая все посылки, разделенные символом новой строки
     */
    @ShellMethod("Метод для получения всех посылок")
    public String getAll() {
        return packageService.getAll()
                .stream()
                .map(Object::toString)
                .collect(Collectors.joining("\n"));
    }

    /**
     * Метод для получения посылки по id.
     *
     * @param id идентификатор посылки
     * @return посылка с указанным идентификатором
     */
    @ShellMethod("Метод для получения посылки по id")
    public Package getById(String id) {
        return packageService.getById(id);
    }

    /**
     * Метод для изменения параметров посылки.
     *
     * @param id идентификатор посылки для изменения
     * @param newPack новые параметры посылки
     */
    @ShellMethod("Метод для изменения параметров посылки")
    public void change(String id, String newPack) {
        packageService.change(id, newPack);
    }

    /**
     * Метод для создания посылки.
     *
     * @param newPack параметры новой посылки
     */
    @ShellMethod("Метод для создания посылки")
    public void create(String newPack) {
        packageService.create(newPack);
    }
}
