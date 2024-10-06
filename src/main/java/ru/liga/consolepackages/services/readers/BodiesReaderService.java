package ru.liga.consolepackages.services.readers;

import org.springframework.web.multipart.MultipartFile;
import ru.liga.consolepackages.models.Body;

import java.util.List;

public interface BodiesReaderService {
    List<Body> readBodiesFromJson(String filePath);
    List<Body> readBodiesFromJson(MultipartFile filePath);
}
