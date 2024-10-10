package ru.liga.consolepackages.services.readers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.liga.consolepackages.exceptions.FailedReadFileException;
import ru.liga.consolepackages.exceptions.FileNotFoundException;
import ru.liga.consolepackages.models.Body;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Slf4j
@Service
public class DefaultBodiesReaderService implements BodiesReaderService {
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
            log.warn("Failed to read the file - {}. {}", filePath, e.getMessage());
            throw new FailedReadFileException("Failed to read the file - " + filePath + ". " + e.getMessage());
        }
    }

    /**
     * Считывает данные о кузовах машин из файла JSON.
     *
     * @param multipartFile Файл JSON с данными о кузовах машин.
     * @return Список объектов кузовов машин.
     */
    @Override
    public List<Body> readBodiesFromJson(MultipartFile multipartFile) {
        try {
            return objectMapper.readValue(multipartFile.getBytes(), new TypeReference<List<Body>>() {
            });
        } catch (IOException e) {
            throw new FailedReadFileException("Failed to read the file - " + ". " + e.getMessage());
        }
    }

    /**
     * Считывает данные о кузовах машин из файла JSON.
     *
     * @param file Файл в формате JSON.
     * @return Список заполненых кузовов машин.
     */
    @Override
    public List<Body> readBodiesFromJson(File file) {
        try {
            return objectMapper.readValue(file, new TypeReference<List<Body>>() {
            });
        } catch (IOException e) {
            log.warn("Failed to read the file - {}. {}", file, e.getMessage());
            throw new FailedReadFileException("Failed to read the file " + e.getMessage());
        }
    }

    private void checkExistenceFile(File file) {
        if (!file.exists()) {
            log.warn("File - {} not found", file.getName());
            throw new FileNotFoundException("File - " + file.getName() + " not found");
        }
    }
}
