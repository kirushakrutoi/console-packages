package ru.liga.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.liga.consolepackages.models.Body;
import ru.liga.consolepackages.models.Package;
import ru.liga.consolepackages.models.Place;
import ru.liga.consolepackages.services.CountPackagesService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CountPackagesServiceTest {

    private final CountPackagesService countPackagesService = new CountPackagesService();

    @Test
    public void emptyBodyTest() {
        List<Body> bodies = new ArrayList<>();
        bodies.add(new Body(6,6));

        Map<Character, Integer> map = countPackagesService.countPackagesFromBodies(bodies);
        assertTrue(map.isEmpty());
    }

    @Test
    public void onePackageTest() {
        List<Body> bodies = new ArrayList<>();
        bodies.add(new Body(6,6));
        bodies.get(0).insertPackage(new Package(new char[][]{{'7', '7', '7'}, {'7', '7', '7', '7'}}), new Place(5, 0));

        Map<Character, Integer> map = countPackagesService.countPackagesFromBodies(bodies);
        Map<Character, Integer> testMap = new HashMap<>();
        testMap.put('7', 1);
        assertEquals(map, testMap);
    }

    @Test
    public void manyBodiesTest() {
        List<Body> bodies = new ArrayList<>();
        bodies.add(new Body(6,6));
        bodies.get(0).insertPackage(new Package(new char[][]{{'7', '7', '7'}, {'7', '7', '7', '7'}}), new Place(5, 0));
        bodies.add(new Body(6,6));
        bodies.get(1).insertPackage(new Package(new char[][]{{'6', '6', '6'}, {'6', '6', '6'}}), new Place(5, 0));
        bodies.get(1).insertPackage(new Package(new char[][]{{'6', '6', '6'}, {'6', '6', '6'}}), new Place(5, 3));

        Map<Character, Integer> map = countPackagesService.countPackagesFromBodies(bodies);
        Map<Character, Integer> testMap = new HashMap<>();
        testMap.put('7', 1);
        testMap.put('6', 2);
        assertEquals(map, testMap);
    }
}
