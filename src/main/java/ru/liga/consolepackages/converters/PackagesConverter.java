package ru.liga.consolepackages.converters;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import ru.liga.consolepackages.models.Package;
import ru.liga.consolepackages.services.packages.PackageService;

import java.util.ArrayList;
import java.util.List;

@Component
public class PackagesConverter {
    private final PackageService packageService;

    public PackagesConverter(PackageService packageService) {
        this.packageService = packageService;
    }

    public List<Package> convertFromString(String sPackages) {
        List<Package> packages = new ArrayList<>();
        for (String sPack : sPackages.split(" ")) {
            packages.add(packageService.getById(sPack));
        }
        return packages;
    }
}
