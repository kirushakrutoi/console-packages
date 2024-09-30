package ru.liga.consolepackages.services.readers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import ru.liga.consolepackages.exceptions.FailedReadFileException;
import ru.liga.consolepackages.models.Body;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class BodiesReaderServiceImpl implements BodiesReaderService {
    private final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Метод для чтения заполненных кузовов грузовиков из JSON-файла.
     *
     * @param filePath путь к JSON-файлу
     * @return список заполненных кузовов грузовиков
     * @throws FileNotFoundException   если указанный файл не найден
     * @throws FailedReadFileException если возникла ошибка при чтении файла
     */
    @Override
    public List<Body> readBodiesFromJson(String filePath) throws FileNotFoundException {
        File file = new File(filePath);
        checkExistenceFile(file);

        try {
            return objectMapper.readValue(file, new TypeReference<List<Body>>() {
            });
        } catch (IOException e) {
            throw new FailedReadFileException("Failed to read the file - " + filePath);
        }
    }

    private void checkExistenceFile(File file) throws FileNotFoundException {
        if (!file.exists()) {
            throw new FileNotFoundException("File - " + file.getName() + " not found");
        }
    }
}
