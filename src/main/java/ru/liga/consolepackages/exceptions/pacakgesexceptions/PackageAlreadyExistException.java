package ru.liga.consolepackages.exceptions.pacakgesexceptions;

public class PackageAlreadyExistException extends RuntimeException{
    public PackageAlreadyExistException(String message) {
        super(message);
    }
}
