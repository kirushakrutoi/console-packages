package ru.liga.consolepackages.validators;

import org.springframework.stereotype.Component;
import ru.liga.consolepackages.exceptions.pacakgesexceptions.InvalidPackageDataException;
import ru.liga.consolepackages.models.Package;

@Component
public class PackageValidator {

    /**
     * Валидирует параметры посылки.
     *
     * @param pack посылка для валидации
     * @throws InvalidPackageDataException если параметры посылки некорректны
     */
    public void validation(Package pack) {
        if (pack.getId() == null || pack.getId().isEmpty()) {
            throw new InvalidPackageDataException("Empty id package");
        }

        if (pack.getSymbol() == '\u0000') {
            throw new InvalidPackageDataException("Empty symbol package");
        }

        if (pack.getSquare() == 0) {
            throw new InvalidPackageDataException("Empty form package");
        }
    }
}
