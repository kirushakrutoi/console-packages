package ru.liga.consolepackages.services.readers;

import ru.liga.consolepackages.models.Body;

import java.io.File;
import java.io.IOException;
import java.util.List;

public interface BodiesReaderService {
    List<Body> readBodiesFromJson(File file) throws IOException;
}
