package ru.liga.consolepackages.services.readers;

import ru.liga.consolepackages.models.Package;

import java.io.File;
import java.io.IOException;
import java.util.List;

public interface PackagesReaderService {
    List<Package> readPackagesFromTxt(String filePath) throws IOException;
}
