package ru.liga.consolepackages.services.readers;

import ru.liga.consolepackages.exceptions.FailedReadFileException;
import ru.liga.consolepackages.models.Package;

import java.io.FileNotFoundException;
import java.util.List;

public interface PackagesReaderService {
    List<Package> readPackagesFromTxt(String filePath) throws FileNotFoundException, FailedReadFileException;
}
