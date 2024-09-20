package ru.liga.consolepackages;

import ru.liga.consolepackages.exceptions.EmptyFileException;
import ru.liga.consolepackages.exceptions.IncorrectAnswerException;
import ru.liga.consolepackages.models.Body;
import ru.liga.consolepackages.services.CoordinatorService;
import ru.liga.consolepackages.services.writers.WriterServiceImpl;
import ru.liga.consolepackages.services.readers.ReaderServiceImpl;

import java.io.*;
import java.util.Arrays;
import java.util.List;

public class ConsolePackages {
    private static final int LENGTH_BODY = 6;
    private static final int WIDTH_BODY = 6;
    private static final String DIR_FOR_WRITE = "batches";

    public static void main(String[] args) {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            System.out.println("Enter file path");
            String filePath = reader.readLine();

            System.out.println("Simplest or optimal algorithm?");
            System.out.println("o - optimal, s - simplest");
            String answer = reader.readLine();

            CoordinatorService coordinatorService =
                    new CoordinatorService(
                            WIDTH_BODY,
                            LENGTH_BODY,
                            new ReaderServiceImpl(),
                            new WriterServiceImpl(DIR_FOR_WRITE)
                    );

            List<Body> bodies = coordinatorService.getFilledBodies(answer, filePath);

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