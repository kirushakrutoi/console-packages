package ru.liga;

import ru.liga.controllers.PlacementController;
import ru.liga.exceptions.EmptyFileException;
import ru.liga.exceptions.IncorrectAnswerException;
import ru.liga.models.Body;
import ru.liga.models.Package;
import ru.liga.services.OptimalPlacementService;
import ru.liga.services.PlacementService;
import ru.liga.services.ReaderService;
import ru.liga.services.SimplestPlacementService;
import ru.liga.utils.PlacementUtil;
import ru.liga.views.BodyView;

import java.io.*;
import java.util.*;

public class ConsolePackages {
    private static final int LENGTH_BODY = 6;
    private static final int WIDTH_BODY = 6;
    public static void main(String[] args) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String filePath = reader.readLine();
            System.out.println("Simplest or optimal algorithm?");
            System.out.println("o - optimal, s - simplest");
            String ans = reader.readLine();

            PlacementController placementController = new PlacementController(WIDTH_BODY, LENGTH_BODY);
            List<Body> bodies = placementController.placement(ans, filePath);

            for (Body body : bodies) {
                System.out.println(body);
            }

        } catch (FileNotFoundException e) {
            System.out.println("File not exist");
        } catch (EmptyFileException | IncorrectAnswerException e){
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println("Unknown error");
        }
    }
}