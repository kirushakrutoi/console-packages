package ru.liga.consolepackages.services.writers;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.liga.consolepackages.exceptions.FailedWriteDataException;
import ru.liga.consolepackages.models.Body;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Slf4j
@Service
public class DefaultWriterService implements WriterService {
    private final ObjectMapper objectMapper;
    @Value("${dir.for.write}")
    private String DIR_FOR_WRITE;

    public DefaultWriterService(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }


    /**
     * Метод для записи заполненных кузовов грузовиков в файл.
     *
     * @param bodies список заполненных кузовов грузовиков для записи
     */
    public void writeBodies(List<Body> bodies) {
        createDir();
        try {
            objectMapper.writeValue(getNextFile(), bodies);
        } catch (IOException e) {
            log.warn(e.getMessage());
            throw new FailedWriteDataException(e.getMessage());
        }

    }

    private File getNextFile() {
        int i = 1;
        while (true) {
            File file = new File(DIR_FOR_WRITE + "/batch-" + i + ".json");
            if (!file.exists()) {
                log.debug("New file {} has been created", file.getName());
                return file;
            }
            i++;
        }
    }

    private void createDir() {
        File file = new File(DIR_FOR_WRITE);
        if (file.mkdir())
            log.debug("New folder {} has been created", file.getName());
    }
}
