package ru.liga.consolepackages.services.writers;

import ru.liga.consolepackages.models.Body;

import java.io.IOException;
import java.util.List;

public interface WriterService {
    void writeBodies(List<Body> bodies) throws IOException;
}
