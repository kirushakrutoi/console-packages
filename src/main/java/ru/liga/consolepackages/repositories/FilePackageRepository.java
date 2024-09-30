package ru.liga.consolepackages.repositories;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.liga.consolepackages.exceptions.FailedReadFileException;
import ru.liga.consolepackages.exceptions.pacakgesexceptions.PackageAlreadyExistException;
import ru.liga.consolepackages.exceptions.pacakgesexceptions.PackageNotFoundException;
import ru.liga.consolepackages.models.Package;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class FilePackageRepository implements PackageRepository {
    private static final Logger logger = LoggerFactory.getLogger(FilePackageRepository.class);
    private final ObjectMapper objectMapper;
    private final String BASE_DIR = "packages";

    public FilePackageRepository(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public List<Package> getAll() {
        List<Package> packages = new ArrayList<>();

        File rootDir = new File(BASE_DIR);
        File[] files = rootDir.listFiles();

        assert files != null;
        for (File file : files) {
            try {
                packages.add(objectMapper.readValue(file, Package.class));
            } catch (IOException e) {
                logger.warn("File " + file.getName() + "couldn't read");
                throw new FailedReadFileException(e.getMessage());
            }

        }
        return packages;
    }

    @Override
    public Package getById(String id) {
        File file = new File(BASE_DIR + "/" + id + ".json");

        if (!file.exists()) {
            throw new PackageNotFoundException("Package with id " + id + " not found");
        }

        try {
            return objectMapper.readValue(file, Package.class);
        } catch (IOException e) {
            logger.warn("File " + file.getName() + "couldn't read");
            throw new FailedReadFileException("File " + file.getName() + "couldn't read");
        }
    }

    @Override
    public void change(String id, Package pack) {
        File oldFile = new File(BASE_DIR + "/" + id + ".json");
        File newFile = new File(BASE_DIR + "/" + pack.getId() + ".json");

        if (!oldFile.delete()) {
            throw new PackageNotFoundException("Package with id " + id + " not found");
        }

        try {
            objectMapper.writeValue(newFile, pack);
        } catch (IOException e) {
            logger.warn("File " + oldFile.getName() + "couldn't read");
            throw new FailedReadFileException("File " + oldFile.getName() + "couldn't read");
        }
    }

    @Override
    public void create(Package pack) {
        File newFile = new File(BASE_DIR + "/" + pack.getId() + ".json");

        if (newFile.exists()) {
            throw new PackageAlreadyExistException("Package with id " + pack.getId() + " already exist");
        }

        try {
            objectMapper.writeValue(newFile, pack);
        } catch (IOException e) {
            logger.warn("File " + newFile.getName() + "couldn't read");
            throw new FailedReadFileException("File " + newFile.getName() + "couldn't read");
        }
    }
}
