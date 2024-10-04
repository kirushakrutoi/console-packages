package ru.liga.consolepackages.services.packages;

import ru.liga.consolepackages.models.Package;

import java.util.List;

public interface PackageService {
    List<Package> getAll();

    Package getById(String id);

    void change(String id, String sPack);

    void create(String sPack);

    void delete(String id);
}
