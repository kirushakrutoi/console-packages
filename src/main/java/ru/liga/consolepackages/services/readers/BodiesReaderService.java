package ru.liga.consolepackages.services.readers;

import ru.liga.consolepackages.models.Body;

import java.util.List;

public interface BodiesReaderService {
    List<Body> readBodiesFromJson(String filePath);
}
