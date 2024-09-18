package ru.liga.services;

import ru.liga.models.Body;
import ru.liga.models.Package;

import java.util.List;

public interface PlacementService {
    List<Body> placementPackage(List<Package> packages);
}
