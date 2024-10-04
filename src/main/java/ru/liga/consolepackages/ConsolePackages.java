package ru.liga.consolepackages;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ConsolePackages {
    private static final Logger logger = LoggerFactory.getLogger(ConsolePackages.class);

    public static void main(String[] args) {
        logger.info("Start app");
        SpringApplication.run(ConsolePackages.class);
        logger.info("End app");
    }
}