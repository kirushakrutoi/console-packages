package ru.liga.consolepackages.services.packages;

import ru.liga.consolepackages.DTOs.ChangePackageDTO;
import ru.liga.consolepackages.DTOs.NewPackageDTO;
import ru.liga.consolepackages.models.Package;

import java.util.List;

public interface PackageService {
    /**
     * Получает список всех посылок.
     *
     * @return список всех посылок
     */
    List<Package> getAll();

    /**
     * Получает посылку по ее идентификатору.
     *
     * @param name идентификатор посылки
     * @return посылка с указанным идентификатором
     */
    Package findByName(String name);

    /**
     * Изменяет данные посылки по ее имени.
     *
     * @param name Имя посылки.
     * @param sPack Измененные данные посылки в формате JSON.
     */
    void change(String name, String sPack);

    /**
     * Изменяет данные посылки по ее имени.
     *
     * @param name Имя посылки.
     * @param changePackageDTO Измененные данные посылки.
     */
    void change(String name, ChangePackageDTO changePackageDTO);

    /**
     * Создает новую посылку.
     *
     * @param sPack Данные новой посылки в формате JSON.
     */
    void create(String sPack);

    /**
     * Создает новую посылку.
     *
     * @param newPack Данные новой посылки.
     */
    void create(NewPackageDTO newPack);

    /**
     * Удаляет посылку по ее имени.
     *
     * @param name Имя посылки.
     */
    void delete(String name);
}
