package ru.liga.consolepackages.repositories;

import ru.liga.consolepackages.models.Package;

import java.util.List;

public interface PackageRepository {

    List<Package> getAll();

    Package getById(String id);

    void change(String id, Package pack);

    void create(Package pack);
}
