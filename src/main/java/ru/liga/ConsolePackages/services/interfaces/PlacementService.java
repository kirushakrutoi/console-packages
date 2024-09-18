package ru.liga.ConsolePackages.services.interfaces;

import ru.liga.ConsolePackages.models.Body;
import ru.liga.ConsolePackages.models.Package;

import java.util.List;

public interface PlacementService {
    List<Body> placementPackage(List<Package> packages);
}
