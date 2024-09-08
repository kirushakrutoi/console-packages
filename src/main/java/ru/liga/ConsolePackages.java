package ru.liga;

import ru.liga.services.PlacementService;
import ru.liga.services.ReaderService;
import ru.liga.views.BodyView;

import java.io.*;
import java.util.*;

public class ConsolePackages {
    private static final int LENGTH_BODY = 6;
    private static final int WIDTH_BODY = 6;

    public static void main(String[] args) throws IOException {
        try {
            List<char[][]> packages = ReaderService.readFile(new File(args[0]));
            PlacementService placementService = new PlacementService(LENGTH_BODY, WIDTH_BODY);
            BodyView bodyView = new BodyView(LENGTH_BODY, WIDTH_BODY);

            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Simplest or optimal algorithm?");
            System.out.println("o - optimal, s - simplest");
            String ans = reader.readLine();

            if (ans.equals("o")) {
                List<char[][]> bodies = placementService.placementPackage(packages);
                for (char[][] body : bodies) {
                    bodyView.printBody(body);
                }
            } else if (ans.equals("s")) {
                List<char[][]> bodies = placementService.simplestPlacementPackage(packages);
                for (char[][] body : bodies) {
                    bodyView.printBody(body);
                }
            } else {
                System.out.println("incorrect");
            }
        } catch (IOException e) {
            System.out.println("File not exist");
        }
    }
}