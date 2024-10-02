package ru.liga.consolepackages.services.readers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.liga.consolepackages.exceptions.FailedReadFileException;
import ru.liga.consolepackages.models.Package;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Service
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
                    logger.debug("A figure of typ" + pack.getSymbol() + "has been read");
                    packages.add(pack);
                    lines.clear();
                } else {
                    lines.add(line);
                }
            }
        } catch (IOException e) {
            throw new FailedReadFileException("Failed to read the file - " + filePath);
        }


        if (!lines.isEmpty()) {
            Package pack = new Package(lines);
            packages.add(pack);
        }

        return packages;
    }
}
