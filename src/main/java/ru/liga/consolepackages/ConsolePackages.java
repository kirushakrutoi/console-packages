package ru.liga.consolepackages;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.liga.consolepackages.controllers.CountPackagesController;
import ru.liga.consolepackages.controllers.PlacementController;
import ru.liga.consolepackages.exceptions.EmptyFileException;
import ru.liga.consolepackages.exceptions.FailedReadFileException;
import ru.liga.consolepackages.exceptions.IncorrectAnswerException;
import ru.liga.consolepackages.exceptions.SmallNumberBodiesException;
import ru.liga.consolepackages.models.Package;

import java.io.*;

@SpringBootApplication
public class ConsolePackages {
    private static final int LENGTH_BODY = 6;
    private static final int WIDTH_BODY = 6;
    private static final String COUNT_PACKAGES = "1";
    private static final String PLACE_PACKAGE = "2";
    private static final Logger logger = LoggerFactory.getLogger(ConsolePackages.class);

    public static void main(String[] args) {
        SpringApplication.run(ConsolePackages.class);
/*        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writeValue(new File("packages/6.json"), new Package("6", '6', new char[][] {{'6', '6', '6'}, {'6', '6', '6'}}));
            logger.info("Start application");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            System.out.println("sort package or count package");
            System.out.println("1 - sort, 2 - count");
            String ans = reader.readLine();

            switch (ans) {
                case COUNT_PACKAGES:
                    logger.info("Sorting is selected");
                    PlacementController placementController =
                            new PlacementController(
                                    WIDTH_BODY,
                                    LENGTH_BODY,
                                    reader
                            );
                    placementController.placePackage();
                    break;
                case PLACE_PACKAGE:
                    logger.info("Placement selected");
                    CountPackagesController countPackagesController =
                            new CountPackagesController(reader);
                    countPackagesController.countPackages();
                    break;
                default:
                    System.out.println("Incorrect answer");
                    logger.info("end application");
            }
        } catch (EmptyFileException | IncorrectAnswerException | SmallNumberBodiesException | FailedReadFileException e) {
            System.out.println(e.getMessage());
            logger.info("end application");
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            logger.info("end application");
        } catch (IOException e) {
            System.out.println("Unknown error");
            logger.info("end application");
        }*/
    }
}