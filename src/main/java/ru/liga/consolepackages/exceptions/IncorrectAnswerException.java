package ru.liga.consolepackages.exceptions;

import java.io.IOException;

public class IncorrectAnswerException extends RuntimeException {
    public IncorrectAnswerException(String message) {
        super(message);
    }
}
