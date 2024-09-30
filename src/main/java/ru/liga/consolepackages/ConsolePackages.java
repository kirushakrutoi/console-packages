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
        logger.info("Start app");
        SpringApplication.run(ConsolePackages.class);
    }
}