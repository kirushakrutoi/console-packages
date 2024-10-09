package ru.liga.consolepackages.services.writers;

import ru.liga.consolepackages.models.Body;

import java.util.List;

public interface WriterService {
    /**
     * Метод для записи заполненных кузовов грузовиков в файл.
     *
     * @param bodies список заполненных кузовов грузовиков для записи
     */
    void writeBodies(List<Body> bodies);
}
