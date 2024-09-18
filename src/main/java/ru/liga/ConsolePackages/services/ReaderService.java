package ru.liga.ConsolePackages.services;

import ru.liga.ConsolePackages.models.Package;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReaderService {
    public static List<Package> readFile(File file) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        List<Package> packages = new ArrayList<>();
        String line;
        List<String> lines = new ArrayList<>();

        while ((line = reader.readLine()) != null) {
            if (line.isEmpty() && !lines.isEmpty()) {
                packages.add(new Package(convertLinesToCharArr(lines)));
                lines.clear();
            } else {
                lines.add(line);
            }
        }

        return packages;
    }

    private static char[][] convertLinesToCharArr(List<String> lines) {
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
