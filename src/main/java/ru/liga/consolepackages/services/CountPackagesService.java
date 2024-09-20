package ru.liga.consolepackages.services;

import ru.liga.consolepackages.models.Body;
import ru.liga.consolepackages.models.Package;
import ru.liga.consolepackages.models.Place;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CountPackagesService {
    public Map<Package, Integer> countPackagesFromBodies(List<Body> bodies) {
        Map<Package, Integer> packageMap = new HashMap<>();

        for (Body body : bodies) {
            for (int i = 0; i < body.getWidth(); i++) {
                for (int j = 0; j < body.getLength(); j++) {
                    Place place = new Place(i, j);
                    char symbol = body.getElement(place);

                    if (symbol != ' ') {
                        putSymbol(packageMap, symbol);
                    }
                }
            }
        }

        countPackages(packageMap);

        return packageMap;
    }

    private void putSymbol(Map<Package, Integer> map, char symbol) {
        Package pack = new Package(new char[][]{{symbol}});

        if (!map.containsKey(pack)) {
            map.put(pack, 1);
        } else {
            map.put(pack, map.get(pack) + 1);
        }
    }

    private void countPackages(Map<Package, Integer> map) {
        for (Map.Entry<Package, Integer> entry : map.entrySet()) {
            entry.setValue(entry.getValue() / entry.getKey().getSquare());
        }
    }
}
