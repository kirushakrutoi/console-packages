package ru.liga.ConsolePackages.exceptions;

import java.io.IOException;

public class EmptyFileException extends IOException {
    public EmptyFileException(String message) {
        super(message);
    }
}
