package ru.liga.consolepackages.services.writers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.liga.consolepackages.exceptions.FailedWriteDataException;
import ru.liga.consolepackages.models.Body;

import java.io.File;
import java.io.IOException;
import java.util.List;


public class WriterServiceImpl implements WriterService {
    private static final Logger logger = LoggerFactory.getLogger(WriterServiceImpl.class);
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final String DIR_FOR_WRITE;

    public WriterServiceImpl(String DIR_FOR_WRITE) {
        this.DIR_FOR_WRITE = DIR_FOR_WRITE;
        createDir();
    }


    /**
     * Метод для записи заполненных кузовов грузовиков в файл.
     *
     * @param bodies список заполненных кузовов грузовиков для записи
     */
    public void writeBodies(List<Body> bodies) {
        try {
            objectMapper.writeValue(getNextFile(), bodies);
        } catch (IOException e) {
            throw new FailedWriteDataException(e.getMessage());
        }

    }

    private File getNextFile() {
        int i = 1;
        while (true) {
            File file = new File(DIR_FOR_WRITE + "/batch-" + i + ".json");
            if (!file.exists()) {
                logger.debug("New file {} has been created", file.getName());
                return file;
            }
            i++;
        }
    }

    private void createDir() {
        File file = new File(DIR_FOR_WRITE);
        file.mkdir();
        logger.debug("New folder {} has been created", file.getName());
    }
}
