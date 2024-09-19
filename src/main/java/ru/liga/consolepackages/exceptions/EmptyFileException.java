package ru.liga.consolepackages.exceptions;

import java.io.IOException;

public class EmptyFileException extends RuntimeException {
    public EmptyFileException(String message) {
        super(message);
    }
}
