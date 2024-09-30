package ru.liga.consolepackages.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.liga.consolepackages.models.Package;
import ru.liga.consolepackages.services.PackageService;

import java.util.stream.Collectors;

@ShellComponent
public class PackageController {
    private final PackageService packageService;

    @Autowired
    public PackageController(PackageService packageService) {
        this.packageService = packageService;
    }

    @ShellMethod("Метод для получения всех посылок")
    public String getAll() {
        return packageService.getAll().stream().map(Object::toString).collect(Collectors.joining("\n"));
    }

    @ShellMethod
    public Package getById(String id) {
        return packageService.getById(id);
    }

    @ShellMethod
    public void change(String id, String newPack) throws JsonProcessingException {
        packageService.change(id, newPack);
    }

    @ShellMethod
    public void create(String newPack) throws JsonProcessingException {
        packageService.create(newPack);
    }
}
