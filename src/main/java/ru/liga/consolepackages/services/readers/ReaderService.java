package ru.liga.consolepackages.services.readers;

import ru.liga.consolepackages.models.Body;
import ru.liga.consolepackages.models.Package;

import java.io.File;
import java.io.IOException;
import java.util.List;

public interface ReaderService {
    List<Package> readPackagesFromTxt(File file) throws IOException;
    List<Body> readBodiesFromJson(File file) throws IOException;
}
