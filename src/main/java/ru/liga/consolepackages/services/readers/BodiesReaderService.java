package ru.liga.consolepackages.services.readers;

import org.springframework.web.multipart.MultipartFile;
import ru.liga.consolepackages.models.Body;

import java.io.File;
import java.util.List;

public interface BodiesReaderService {
    /**
     * Метод для чтения заполненных кузовов грузовиков из JSON-файла.
     *
     * @param filePath путь к JSON-файлу
     * @return список заполненных кузовов грузовиков
     */
    List<Body> readBodiesFromJson(String filePath);
    /**
     * Считывает данные о кузовах машин из файла JSON.
     *
     * @param multipartFile Файл JSON с данными о кузовах машин.
     * @return Список объектов кузовов машин.
     */
    List<Body> readBodiesFromJson(MultipartFile multipartFile);
    /**
     * Считывает данные о кузовах машин из файла JSON.
     *
     * @param file Файл в формате JSON.
     * @return Список заполненых кузовов машин.
     */
    List<Body> readBodiesFromJson(File file);
}
