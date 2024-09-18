package ru.liga.exceptions;

import java.io.IOException;

public class IncorrectAnswerException extends IOException {
    public IncorrectAnswerException(String message) {
        super(message);
    }
}
