package ru.liga.consolepackages.services.packages;

import ru.liga.consolepackages.DTOs.ChangePackageDTO;
import ru.liga.consolepackages.DTOs.NewPackageDTO;
import ru.liga.consolepackages.models.Package;

import java.util.List;

public interface PackageService {
    List<Package> getAll();

    Package findByName(String name);

    void change(String name, String sPack);
    void change(String name, ChangePackageDTO pack);

    void create(String sPack);

    void create(NewPackageDTO pack);

    void delete(String id);
}
