package ru.liga.consolepackages.services.readers;

import ru.liga.consolepackages.models.Package;

import java.util.List;

public interface PackagesReaderService {
    List<Package> readPackagesFromTxt(String filePath);
}
