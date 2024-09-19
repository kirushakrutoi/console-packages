package ru.liga.consolepackages;

import ru.liga.consolepackages.exceptions.EmptyFileException;
import ru.liga.consolepackages.exceptions.IncorrectAnswerException;
import ru.liga.consolepackages.models.Body;
import ru.liga.consolepackages.services.CoordinatorService;
import ru.liga.consolepackages.services.readers.ReaderServiceImpl;

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

            System.out.println("Simplest or optimal algorithm?");
            System.out.println("o - optimal, s - simplest");
            String ans = reader.readLine();

            CoordinatorService coordinatorService = new CoordinatorService(WIDTH_BODY, LENGTH_BODY, new ReaderServiceImpl());
            List<Body> bodies = coordinatorService.getFilledBodies(ans, filePath);

            for (Body body : bodies) {
                System.out.println(body);
            }
        } catch (EmptyFileException | IncorrectAnswerException | FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println("Unknown error");
        }
    }
}