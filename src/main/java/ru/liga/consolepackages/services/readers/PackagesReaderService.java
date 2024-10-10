package ru.liga.consolepackages.services.readers;

import ru.liga.consolepackages.models.Package;

import java.util.List;

public interface PackagesReaderService {
    /**
     * Метод для чтения посылок из текстового файла.
     *
     * @param filePath путь к текстовому файлу
     * @return список посылок
     */
    List<Package> readPackagesFromTxt(String filePath);

    List<Package> readPackagesFromBytes(byte[] file);
}
