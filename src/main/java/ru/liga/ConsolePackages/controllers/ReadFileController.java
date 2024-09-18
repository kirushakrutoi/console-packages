package ru.liga.ConsolePackages.controllers;

import ru.liga.ConsolePackages.exceptions.EmptyFileException;
import ru.liga.ConsolePackages.models.Package;
import ru.liga.ConsolePackages.services.ReaderService;

import java.io.*;
import java.util.List;

public class ReadFileController {

    public List<Package> readFile(String filePath) throws IOException {
        ReaderService readerService = new ReaderService();
        List<Package> packages = readerService.readFile(new File(filePath));

        if(packages.isEmpty()){
            throw new EmptyFileException("Empty file\ntry other file");
        }

        return packages;
    }
}
