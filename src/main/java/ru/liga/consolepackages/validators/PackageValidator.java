package ru.liga.consolepackages.validators;

import ru.liga.consolepackages.exceptions.pacakgesexceptions.InvalidPackageDataException;
import ru.liga.consolepackages.models.Package;

public class PackageValidator {
    public void validation(Package pack) {
        if(pack.getId() == null || pack.getId().isEmpty()) {
            throw new InvalidPackageDataException("Empty id package");
        }

        if(pack.getSymbol() == '\u0000') {
            throw new InvalidPackageDataException("Empty symbol package");
        }

        if(pack.getSquare() == 0) {
            throw new InvalidPackageDataException("Empty form package");
        }
    }
}
