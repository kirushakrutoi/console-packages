package ru.liga.consolepackages.exceptions.pacakgesexceptions;

public class PackageNotFoundException extends RuntimeException {
    public PackageNotFoundException(String message) {
        super(message);
    }
}
