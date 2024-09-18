package ru.liga.ConsolePackages;

import ru.liga.ConsolePackages.controllers.PlacementController;
import ru.liga.ConsolePackages.controllers.ReadFileController;
import ru.liga.ConsolePackages.exceptions.EmptyFileException;
import ru.liga.ConsolePackages.exceptions.IncorrectAnswerException;
import ru.liga.ConsolePackages.models.Body;
import ru.liga.ConsolePackages.models.Package;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class ConsolePackages {
    private static final int LENGTH_BODY = 6;
    private static final int WIDTH_BODY = 6;

    public static void main(String[] args) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            System.out.println("Enter file path");
            String filePath = reader.readLine();
            ReadFileController readFileController = new ReadFileController();
            List<Package> packages = readFileController.readFile(filePath);

            System.out.println("Simplest or optimal algorithm?");
            System.out.println("o - optimal, s - simplest");
            String ans = reader.readLine();

            PlacementController placementController = new PlacementController(WIDTH_BODY, LENGTH_BODY);
            List<Body> bodies = placementController.placement(ans, packages);

            for (Body body : bodies) {
                System.out.println(body);
            }

        } catch (FileNotFoundException e) {
            System.out.println("File not exist");
        } catch (EmptyFileException | IncorrectAnswerException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println("Unknown error");
        }
    }
}