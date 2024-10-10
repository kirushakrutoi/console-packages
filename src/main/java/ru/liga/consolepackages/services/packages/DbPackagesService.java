package ru.liga.consolepackages.services.packages;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import ru.liga.consolepackages.exceptions.pacakgesexceptions.InvalidPackageDataException;
import ru.liga.consolepackages.exceptions.pacakgesexceptions.PackageNotFoundException;
import ru.liga.consolepackages.mappers.PackageMapper;
import ru.liga.consolepackages.models.Package;
import ru.liga.consolepackages.repositories.JpaPackageRepository;

import java.util.List;

@Service
@Primary
public class DbPackagesService implements PackageService {
    private final JpaPackageRepository jpaPackageRepository;
    private final ObjectMapper objectMapper;
    private final PackageMapper packageMapper;

    @Autowired
    public DbPackagesService(JpaPackageRepository jpaPackageRepository, ObjectMapper objectMapper, PackageMapper packageMapper) {
        this.jpaPackageRepository = jpaPackageRepository;
        this.objectMapper = objectMapper;
        this.packageMapper = packageMapper;
    }

    /**
     * Возвращает список всех посылок.
     *
     * @return Список всех посылок.
     */
    @Override
    public List<Package> getAll() {
        return jpaPackageRepository.findAll();
    }

    /**
     * Возвращает посылку по ее имени.
     *
     * @param name Имя посылки.
     * @return Посылка с указанным именем.
     */
    @Override
    public Package findByName(String name) {
        return jpaPackageRepository
                .findByName(name)
                .orElseThrow(() -> new PackageNotFoundException("Package with id " + name + " not found"));
    }

    /**
     * Изменяет данные посылки по ее имени.
     *
     * @param name  Имя посылки.
     * @param sPack Измененные данные посылки в формате JSON.
     */
    @Override
    public void change(String name, String sPack) {
        Package oldPack = findByName(name);
        Package newPack;
        try {
            newPack = objectMapper.readValue(sPack, Package.class);
            packageMapper.updatePackageFields(oldPack, newPack);

            jpaPackageRepository.save(oldPack);
        } catch (JsonProcessingException e) {
            throw new InvalidPackageDataException("Incorrect package data " + e.getMessage());
        }
    }

    /**
     * Изменяет данные посылки по ее имени.
     *
     * @param name       Имя посылки.
     * @param changePack Измененные данные посылки.
     */
    @Override
    public void change(String name, Package changePack) {
        Package oldPack = findByName(name);
        packageMapper.updatePackageFields(oldPack, changePack);
        jpaPackageRepository.save(oldPack);
    }

    /**
     * Создает новую посылку.
     *
     * @param sPack Данные новой посылки в формате JSON.
     */
    @Override
    public void create(String sPack) {
        try {
            Package pack = objectMapper.readValue(sPack, Package.class);
            jpaPackageRepository.save(pack);
        } catch (JsonProcessingException e) {
            throw new InvalidPackageDataException("Incorrect package data " + e.getMessage());
        }
    }

    /**
     * Создает новую посылку.
     *
     * @param pack Данные новой посылки.
     */
    @Override
    public void create(Package pack) {
        jpaPackageRepository.save(pack);
    }

    /**
     * Удаляет посылку по ее имени.
     *
     * @param name Имя посылки.
     */
    @Override
    public void delete(String name) {
        jpaPackageRepository.delete(findByName(name));
    }
}
