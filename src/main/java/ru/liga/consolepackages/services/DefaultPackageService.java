package ru.liga.consolepackages.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ru.liga.consolepackages.models.Package;
import ru.liga.consolepackages.repositories.PackageRepository;
import ru.liga.consolepackages.validators.PackageValidator;

import java.util.List;

public class DefaultPackageService implements PackageService {

    private final PackageRepository packageRepository;
    private final PackageValidator packageValidator;

    public DefaultPackageService(PackageRepository packageRepository, PackageValidator packageValidator) {
        this.packageRepository = packageRepository;
        this.packageValidator = packageValidator;
    }

    @Override
    public List<Package> getAll() {
        return packageRepository.getAll();
    }

    @Override
    public Package getById(String id) {
        return packageRepository.getById(id);
    }

    @Override
    public void change(String id, String sPack) throws JsonProcessingException {
        Package newPack = new ObjectMapper().readValue(sPack, Package.class);
        packageValidator.validation(newPack);
        packageRepository.change(id, newPack);
    }

    @Override
    public void create(String sPack) throws JsonProcessingException {
        Package newPack = new ObjectMapper().readValue(sPack, Package.class);
        packageValidator.validation(newPack);
        packageRepository.create(newPack);
    }
}
