package ru.liga.consolepackages.repositories;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import ru.liga.consolepackages.exceptions.FailedReadFileException;
import ru.liga.consolepackages.exceptions.pacakgesexceptions.PackageAlreadyExistException;
import ru.liga.consolepackages.exceptions.pacakgesexceptions.PackageNotFoundException;
import ru.liga.consolepackages.models.Package;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Repository
public class FilePackageRepository implements PackageRepository {
    private final ObjectMapper objectMapper;
    private final String BASE_DIR = "packages";

    public FilePackageRepository(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    /**
     * Получает список всех посылок.
     *
     * @return список всех посылок
     */
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
                log.warn("File " + file.getName() + "couldn't read");
                throw new FailedReadFileException(e.getMessage());
            }

        }
        return packages;
    }

    /**
     * Получает посылку по ее идентификатору.
     *
     * @param id идентификатор посылки
     * @return посылка с указанным идентификатором
     */
    @Override
    public Package getById(String id) {
        File file = new File(BASE_DIR + "/" + id + ".json");

        if (!file.exists()) {
            throw new PackageNotFoundException("Package with id " + id + " not found");
        }

        try {
            return objectMapper.readValue(file, Package.class);
        } catch (IOException e) {
            log.warn("File " + file.getName() + "couldn't read");
            throw new FailedReadFileException("File " + file.getName() + "couldn't read");
        }
    }


    /**
     * Изменяет параметры посылки.
     *
     * @param id   идентификатор посылки
     * @param pack посылка с новыми параметрами
     */
    @Override
    public void change(String id, Package pack) {
        File oldFile = new File(BASE_DIR + "/" + id + ".json");
        File newFile = new File(BASE_DIR + "/" + pack.getName() + ".json");

        if (!oldFile.delete()) {
            throw new PackageNotFoundException("Package with id " + id + " not found");
        }

        try {
            objectMapper.writeValue(newFile, pack);
        } catch (IOException e) {
            log.warn("File " + oldFile.getName() + "couldn't read");
            throw new FailedReadFileException("File " + oldFile.getName() + "couldn't read");
        }
    }

    /**
     * Создает новую посылку.
     *
     * @param pack новая посылка
     */
    @Override
    public void create(Package pack) {
        File newFile = new File(BASE_DIR + "/" + pack.getName() + ".json");

        if (newFile.exists()) {
            throw new PackageAlreadyExistException("Package with id " + pack.getName() + " already exist");
        }

        try {
            objectMapper.writeValue(newFile, pack);
        } catch (IOException e) {
            log.warn("File " + newFile.getName() + "couldn't read");
            throw new FailedReadFileException("File " + newFile.getName() + "couldn't read");
        }
    }

    /**
     * Удаляет посылку.
     *
     * @param id Идентификатор посылки.
     */
    @Override
    public void delete(String id) {
        File file = new File(BASE_DIR + "/" + id + ".json");

        if (!file.exists()) {
            throw new PackageNotFoundException("Package with id " + id + " not found");
        }

        file.delete();
    }
}
