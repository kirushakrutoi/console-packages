package ru.liga.controllers;

import ru.liga.exceptions.EmptyFileException;
import ru.liga.exceptions.IncorrectAnswerException;
import ru.liga.models.Body;
import ru.liga.models.Package;
import ru.liga.services.OptimalPlacementService;
import ru.liga.services.PlacementService;
import ru.liga.services.ReaderService;
import ru.liga.services.SimplestPlacementService;
import ru.liga.views.BodyView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class PlacementController {
    private static final String OPTIMAL_ALGORITHM = "o";
    private static final String SIMPLEST_ALGORITHM = "s";
    private final int LENGTH_BODY;
    private final int WIDTH_BODY;

    public PlacementController(int WIDTH_BODY, int LENGTH_BODY) {
        this.WIDTH_BODY = WIDTH_BODY;
        this.LENGTH_BODY = LENGTH_BODY;
    }

    public List<Body> placement(String ans, String path) throws IOException {
        PlacementService placementService;

        switch (ans) {
            case OPTIMAL_ALGORITHM:
                placementService = new OptimalPlacementService(LENGTH_BODY, WIDTH_BODY);
                break;
            case SIMPLEST_ALGORITHM:
                placementService = new SimplestPlacementService(LENGTH_BODY, WIDTH_BODY);
                break;

            default:
                throw new IncorrectAnswerException("Incorrect answer\n o - optimal algorithm \n s - simplest algorithm");
        }

        List<Package> packages = ReaderService.readFile(new File(path));
        if(packages.isEmpty()){
            throw new EmptyFileException("Empty file\ntry other file");
        }
        return placementService.placementPackage(packages);
    }
}
