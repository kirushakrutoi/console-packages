package ru.liga.consolepackages.services.readers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.liga.consolepackages.models.Body;
import ru.liga.consolepackages.models.Package;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReaderService implements PackagesReaderService, BodiesReaderService {
    private final ObjectMapper objectMapper = new ObjectMapper();
    private static final Logger logger = LoggerFactory.getLogger(ReaderService.class);

    @Override
    public List<Body> readBodiesFromJson(File file) throws IOException {
        return objectMapper.readValue(file, new TypeReference<List<Body>>() {
        });
    }

    @Override
    public List<Package> readPackagesFromTxt(File file) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        List<Package> packages = new ArrayList<>();
        String line;
        List<String> lines = new ArrayList<>();

        while ((line = reader.readLine()) != null) {
            if (line.isEmpty() && !lines.isEmpty()) {
                Package pack = new Package(convertLinesToCharArr(lines));
                logger.debug("A figure of typ" + pack.getSymbol() + "has been read");
                packages.add(pack);
                lines.clear();
            } else {
                lines.add(line);
            }
        }

        if (!lines.isEmpty()) {
            Package pack = new Package(convertLinesToCharArr(lines));
            packages.add(pack);
        }

        return packages;
    }

    private char[][] convertLinesToCharArr(List<String> lines) {
        char[][] pack = new char[lines.size()][];

        for (int i = 0; i < lines.size(); i++) {
            pack[i] = new char[lines.get(i).length()];
            for (int j = 0; j < lines.get(i).length(); j++) {
                pack[i][j] = lines.get(i).charAt(j);
            }
        }

        return pack;
    }
}
