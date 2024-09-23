package ru.liga.consolepackages.exceptions;

public class FailedReadFileException extends RuntimeException {
    public FailedReadFileException(String message) {
        super(message);
    }
}
