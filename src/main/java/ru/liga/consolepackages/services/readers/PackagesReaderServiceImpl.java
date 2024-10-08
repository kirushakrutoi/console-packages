package ru.liga.consolepackages.services.readers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.liga.consolepackages.exceptions.FailedReadFileException;
import ru.liga.consolepackages.models.Package;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PackagesReaderServiceImpl implements PackagesReaderService {
    private static final Logger logger = LoggerFactory.getLogger(PackagesReaderServiceImpl.class);

    /**
     * Метод для чтения посылок из текстового файла.
     *
     * @param filePath путь к текстовому файлу
     * @return список посылок
     */
    @Override
    public List<Package> readPackagesFromTxt(String filePath) {
        File file = new File(filePath);
        List<Package> packages = new ArrayList<>();
        String line;
        List<String> lines = new ArrayList<>();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            while ((line = reader.readLine()) != null) {
                if (line.isEmpty() && !lines.isEmpty()) {
                    Package pack = new Package(lines);
                    logger.debug("A figure of typ {} has been read", pack.getSymbol());
                    packages.add(pack);
                    lines.clear();
                } else {
                    lines.add(line);
                }
            }
        } catch (IOException e) {
            logger.warn("Failed to read the file - " + filePath + ". " + e.getMessage());
            throw new FailedReadFileException("Failed to read the file - " + filePath + ". " + e.getMessage());
        }


        if (!lines.isEmpty()) {
            Package pack = new Package(lines);
            logger.debug("A figure of typ {} has been read", pack.getSymbol());
            packages.add(pack);
        }

        return packages;
    }
}
