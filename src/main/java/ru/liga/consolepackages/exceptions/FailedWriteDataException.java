package ru.liga.consolepackages.exceptions;

public class FailedWriteDataException extends RuntimeException {
    public FailedWriteDataException(String message) {
        super(message);
    }
}
