package ru.liga.consolepackages;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.liga.consolepackages.controllers.CountPackagesController;
import ru.liga.consolepackages.controllers.PlacementController;

import java.util.Scanner;


public class ConsolePackages {
    private static final int LENGTH_BODY = 6;
    private static final int WIDTH_BODY = 6;
    private static final String PLACE_PACKAGE = "1";
    private static final String COUNT_PACKAGES = "2";
    private static final Logger logger = LoggerFactory.getLogger(ConsolePackages.class);

    public static void main(String[] args) {
        try {
            logger.info("Start application");
            Scanner sc = new Scanner(System.in);

            System.out.println("sort package or count package");
            System.out.println("1 - place, 2 - count");
            String ans = sc.nextLine();

            switch (ans) {
                case PLACE_PACKAGE:
                    logger.info("Placement selected");
                    PlacementController placementController = new PlacementController(sc);
                    placementController.placePackage(WIDTH_BODY, LENGTH_BODY);
                    break;
                case COUNT_PACKAGES:
                    logger.info("Sorting is selected");
                    CountPackagesController countPackagesController = new CountPackagesController(sc);
                    countPackagesController.countPackages();
                    break;
                default:
                    System.out.println("Incorrect answer");
                    logger.info("end application");
            }
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            logger.info("end application");
        }
    }
}