package ru.liga.consolepackages.services.packages;

import org.springframework.stereotype.Service;
import ru.liga.consolepackages.models.Body;
import ru.liga.consolepackages.models.Place;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CountPackagesService {

    /**
     * Метод для подсчета количества посылок каждого типа.
     *
     * @param bodies список кузовов грузовиков
     * @return карта, содержащая количество посылок каждого типа
     */
    public Map<Character, Integer> countPackagesFromBodies(List<Body> bodies) {
        Map<Character, Integer> packageMap = new HashMap<>();

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

    private void putSymbol(Map<Character, Integer> map, char symbol) {
        if (!map.containsKey(symbol)) {
            map.put(symbol, 1);
        } else {
            map.put(symbol, map.get(symbol) + 1);
        }
    }

    private void countPackages(Map<Character, Integer> map) {
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            entry.setValue(entry.getValue() / (entry.getKey() - '0'));
        }
    }
}
