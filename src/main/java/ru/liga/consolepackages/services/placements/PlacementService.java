package ru.liga.consolepackages.services.placements;

import ru.liga.consolepackages.models.Body;
import ru.liga.consolepackages.models.Package;

import java.util.List;

public abstract class PlacementService {
    public abstract List<Body> placementPackage(List<Package> packages);

    protected void sortPackage(List<Package> packages) {
        for (int i = 0; i < packages.size() - 1; i++) {
            for (int j = i + 1; j < packages.size(); j++) {
                if (packages.get(i).getSquare() < packages.get(j).getSquare()) {
                    Package temp = packages.get(i);
                    packages.set(i, packages.get(j));
                    packages.set(j, temp);
                }
            }
        }
    }
}
