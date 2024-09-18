package ru.liga.services;

import ru.liga.models.Package;

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
                char[][] chars = new char[lines.size()][];
                int i = 0;
                for (String s : lines) {
                    chars[i] = new char[s.length()];
                    for (int j = 0; j < s.length(); j++) {
                        chars[i][j] = s.charAt(j);
                    }
                    i++;
                }
                Package pack = new Package(chars);
                packages.add(pack);
                lines.clear();
            } else {
                lines.add(line);
            }
        }

        return packages;
    }
}
