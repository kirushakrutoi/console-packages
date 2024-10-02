package ru.liga.consolepackages.services.readers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.liga.consolepackages.exceptions.FailedReadFileException;
import ru.liga.consolepackages.exceptions.FileNotFoundException;
import ru.liga.consolepackages.models.Body;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class BodiesReaderServiceImpl implements BodiesReaderService {
    private static final Logger logger = LoggerFactory.getLogger(BodiesReaderServiceImpl.class);
    private final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Метод для чтения заполненных кузовов грузовиков из JSON-файла.
     *
     * @param filePath путь к JSON-файлу
     * @return список заполненных кузовов грузовиков
     */
    @Override
    public List<Body> readBodiesFromJson(String filePath) {
        File file = new File(filePath);
        checkExistenceFile(file);

        try {
            return objectMapper.readValue(file, new TypeReference<List<Body>>() {
            });
        } catch (IOException e) {
            logger.warn("Failed to read the file - {}. {}", filePath, e.getMessage());
            throw new FailedReadFileException("Failed to read the file - " + filePath + ". " + e.getMessage());
        }
    }

    private void checkExistenceFile(File file) {
        if (!file.exists()) {
            logger.warn("File - {} not found", file.getName());
            throw new FileNotFoundException("File - " + file.getName() + " not found");
        }
    }
}
