package ru.liga.services.packages;

import org.junit.jupiter.api.Test;
import ru.liga.consolepackages.models.Body;
import ru.liga.consolepackages.models.Package;
import ru.liga.consolepackages.models.Place;
import ru.liga.consolepackages.services.packages.CountPackagesService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class CountPackagesServiceTest {
    @Test
    public void emptyBodyTest() {
        CountPackagesService countPackagesService = new CountPackagesService();
        List<Body> bodies = new ArrayList<>();
        bodies.add(new Body(6, 6));

        Map<Character, Integer> map = countPackagesService.countPackagesFromBodies(bodies);
        assertThat(map.size()).isEqualTo(0);
    }

    @Test
    public void onePackageTest() {
        CountPackagesService countPackagesService = new CountPackagesService();
        List<Body> bodies = new ArrayList<>();
        bodies.add(new Body(6, 6));
        bodies.get(0).insertPackage(new Package(new char[][]{{'7', '7', '7'}, {'7', '7', '7', '7'}}), new Place(5, 0));

        Map<Character, Integer> map = countPackagesService.countPackagesFromBodies(bodies);
        Map<Character, Integer> testMap = new HashMap<>();
        testMap.put('7', 1);
        assertThat(map).isEqualTo(testMap);
    }

    @Test
    public void manyBodiesTest() {
        CountPackagesService countPackagesService = new CountPackagesService();
        List<Body> bodies = new ArrayList<>();
        bodies.add(new Body(6, 6));
        bodies.get(0).insertPackage(new Package(new char[][]{{'7', '7', '7'}, {'7', '7', '7', '7'}}), new Place(5, 0));
        bodies.add(new Body(6, 6));
        bodies.get(1).insertPackage(new Package(new char[][]{{'6', '6', '6'}, {'6', '6', '6'}}), new Place(5, 0));
        bodies.get(1).insertPackage(new Package(new char[][]{{'6', '6', '6'}, {'6', '6', '6'}}), new Place(5, 3));

        Map<Character, Integer> map = countPackagesService.countPackagesFromBodies(bodies);
        Map<Character, Integer> testMap = new HashMap<>();
        testMap.put('7', 1);
        testMap.put('6', 2);
        assertThat(map).isEqualTo(testMap);
    }
}
