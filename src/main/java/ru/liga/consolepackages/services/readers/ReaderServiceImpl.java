package ru.liga.consolepackages.services.readers;

import ru.liga.consolepackages.models.Package;
import ru.liga.consolepackages.services.readers.interfaces.ReaderService;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReaderServiceImpl implements ReaderService {
    public List<Package> readFile(File file) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        List<Package> packages = new ArrayList<>();
        String line;
        List<String> lines = new ArrayList<>();

        while ((line = reader.readLine()) != null) {
            if (line.isEmpty() && !lines.isEmpty()) {
                Package pack = new Package(convertLinesToCharArr(lines));
                packages.add(pack);
                lines.clear();
            } else {
                lines.add(line);
            }
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
