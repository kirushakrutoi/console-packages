package ru.liga.consolepackages.services.readers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.liga.consolepackages.exceptions.FailedReadFileException;
import ru.liga.consolepackages.models.Package;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class DefaultPackagesReaderService implements PackagesReaderService {

    /**
     * Метод для чтения посылок из текстового файла.
     *
     * @param filePath путь к текстовому файлу
     * @return список посылок
     */
    @Override
    public List<Package> readPackagesFromTxt(String filePath) {
        return readPackages(getReader(filePath));
    }

    /**
     * Метод для чтения посылок из текстового файла.
     *
     * @param file текстовый файл для чтения.
     * @return список посылок
     */
    @Override
    public List<Package> readPackagesFromBytes(byte[] file) {
        return readPackages(getReader(file));
    }

    private List<Package> readPackages(BufferedReader reader) {
        List<Package> packages = new ArrayList<>();
        String line;
        List<String> lines = new ArrayList<>();

        try {
            while ((line = reader.readLine()) != null) {
                if (line.isEmpty() && !lines.isEmpty()) {
                    Package pack = new Package(lines);
                    log.debug("A figure of typ" + pack.getSymbol() + "has been read");
                    packages.add(pack);
                    lines.clear();
                } else {
                    lines.add(line);
                }
            }
        } catch (IOException e) {
            throw new FailedReadFileException("Failed to read the file - " + e.getMessage());
        }


        if (!lines.isEmpty()) {
            Package pack = new Package(lines);
            packages.add(pack);
        }

        return packages;
    }

    private BufferedReader getReader(byte[] file) {
        try {
            return new BufferedReader(new InputStreamReader(new ByteArrayInputStream(file)));
        } catch (Exception e) {
            throw new FailedReadFileException("Failed to read the file " + e.getMessage());
        }
    }

    private BufferedReader getReader(String filePath) {
        try {
            return new BufferedReader(new FileReader(filePath));
        } catch (IOException e) {
            throw new FailedReadFileException("Failed to read the file " + e.getMessage());
        }
    }
}
