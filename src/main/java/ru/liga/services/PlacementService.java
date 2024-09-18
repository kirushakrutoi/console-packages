package ru.liga.services;

import ru.liga.models.Body;

import java.util.List;

public interface PlacementService {
    List<Body> placementPackage(List<char[][]> packages);
}
