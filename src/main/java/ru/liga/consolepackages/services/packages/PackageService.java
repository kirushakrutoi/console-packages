package ru.liga.consolepackages.services.packages;

import com.fasterxml.jackson.core.JsonProcessingException;
import ru.liga.consolepackages.models.Package;

import java.util.List;

public interface PackageService {
    List<Package> getAll();

    Package getById(String id);

    void change(String id, String sPack) throws JsonProcessingException;

    void create(String sPack) throws JsonProcessingException;
}
