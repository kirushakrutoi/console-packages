package ru.liga.consolepackages.services.packages;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.liga.consolepackages.DTOs.ChangePackageDTO;
import ru.liga.consolepackages.DTOs.NewPackageDTO;
import ru.liga.consolepackages.exceptions.pacakgesexceptions.InvalidPackageDataException;
import ru.liga.consolepackages.mappers.PackageMapper;
import ru.liga.consolepackages.models.Package;
import ru.liga.consolepackages.repositories.PackageRepository;
import ru.liga.consolepackages.validators.PackageValidator;

import java.util.List;

@Service
public class DefaultPackageService implements PackageService {
    private static final Logger logger = LoggerFactory.getLogger(DefaultPackageService.class);
    private final PackageRepository packageRepository;
    private final PackageValidator packageValidator;
    private final PackageMapper packageMapper;

    @Autowired
    public DefaultPackageService(PackageRepository packageRepository, PackageValidator packageValidator, PackageMapper packageMapper) {
        this.packageRepository = packageRepository;
        this.packageValidator = packageValidator;
        this.packageMapper = packageMapper;
    }

    /**
     * Получает список всех посылок.
     *
     * @return список всех посылок
     */
    @Override
    public List<Package> getAll() {
        return packageRepository.getAll();
    }


    /**
     * Получает посылку по ее идентификатору.
     *
     * @param id идентификатор посылки
     * @return посылка с указанным идентификатором
     */
    @Override
    public Package findByName(String id) {
        return packageRepository.getById(id);
    }

    /**
     * Изменяет параметры посылки.
     *
     * @param id    идентификатор посылки
     * @param sPack строка с новыми параметрами посылки в формате JSON
     */
    @Override
    public void change(String id, String sPack) {
        try {
            Package newPack = new ObjectMapper().readValue(sPack, Package.class);
            packageValidator.validation(newPack);
            packageRepository.change(id, newPack);
        } catch (JsonProcessingException e) {
            logger.warn(e.getMessage());
            throw new InvalidPackageDataException(e.getMessage());
        }
    }

    @Override
    public void change(String name, ChangePackageDTO changePackageDTO) {
        Package pack = packageMapper.fromChangeDtoToPackage(changePackageDTO);
        packageRepository.change(name, pack);
    }

    /**
     * Создает новую посылку.
     *
     * @param sPack строка с параметрами новой посылки в формате JSON
     */
    @Override
    public void create(String sPack) {
        try {
            Package newPack = new ObjectMapper().readValue(sPack, Package.class);
            packageValidator.validation(newPack);
            packageRepository.create(newPack);
        } catch (JsonProcessingException e) {
            logger.warn(e.getMessage());
            throw new InvalidPackageDataException(e.getMessage());
        }
    }

    @Override
    public void create(NewPackageDTO newPackageDTO) {
        Package pack = packageMapper.fromNewDtoToPackage(newPackageDTO);
        packageRepository.create(pack);
    }

    /**
     * Удаляет посылку.
     *
     * @param id Идентификатор пакета.
     */
    @Override
    public void delete(String id) {
        packageRepository.delete(id);
    }
}
