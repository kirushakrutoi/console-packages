package ru.liga.ConsolePackages.controllers;

import ru.liga.ConsolePackages.exceptions.IncorrectAnswerException;
import ru.liga.ConsolePackages.models.Body;
import ru.liga.ConsolePackages.models.Package;
import ru.liga.ConsolePackages.services.OptimalPlacementService;
import ru.liga.ConsolePackages.services.PlacementService;
import ru.liga.ConsolePackages.services.SimplestPlacementService;

import java.io.IOException;
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

    public List<Body> placement(String ans, List<Package> packages) throws IOException {
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

        return placementService.placementPackage(packages);
    }
}
