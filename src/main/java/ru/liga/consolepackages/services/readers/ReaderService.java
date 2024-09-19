package ru.liga.consolepackages.services.readers;

import ru.liga.consolepackages.models.Package;

import java.io.File;
import java.io.IOException;
import java.util.List;

public interface ReaderService {
    List<Package> readFile(File file) throws IOException;
}
