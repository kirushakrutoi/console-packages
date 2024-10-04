package ru.liga.consolepackages.converters;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import ru.liga.consolepackages.models.Package;
import ru.liga.consolepackages.services.packages.PackageService;

import java.util.ArrayList;
import java.util.List;

@Component
public class PackagesConverter {
    private static final Logger logger = LoggerFactory.getLogger(PackagesConverter.class);
    private final PackageService packageService;

    public PackagesConverter(PackageService packageService) {
        this.packageService = packageService;
    }

    public List<Package> convertFromString(String sPackages) {
        logger.debug("Start converting packages {}", sPackages);
        List<Package> packages = new ArrayList<>();
        for (String sPack : sPackages.split(" ")) {
            packages.add(packageService.getById(sPack));
        }
        logger.debug("End converting packages {}", sPackages);
        return packages;
    }
}
