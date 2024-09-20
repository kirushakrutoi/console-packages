package ru.liga.consolepackages.services.writers;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.liga.consolepackages.models.Body;
import ru.liga.consolepackages.services.writers.WriterService;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class WriterServiceImpl implements WriterService {
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final String DIR_FOR_WRITE;

    public WriterServiceImpl(String DIR_FOR_WRITE) {
        this.DIR_FOR_WRITE = DIR_FOR_WRITE;
        createDir();
    }

    public void writeBodies(List<Body> bodies) throws IOException {
        objectMapper.writeValue(getNextFile(), bodies);
    }

    private File getNextFile() {
        int i = 1;
        while (true) {
            File file = new File(DIR_FOR_WRITE + "/batch-" + i + ".json");
            if (!file.exists()) {
                return file;
            }
            i++;
        }
    }

    private void createDir() {
        File file = new File(DIR_FOR_WRITE);
        file.mkdir();
    }
}
