package ru.liga.services;

import org.junit.jupiter.api.Test;
import ru.liga.consolepackages.exceptions.SmallNumberBodiesException;
import ru.liga.consolepackages.models.Body;
import ru.liga.consolepackages.models.Package;
import ru.liga.consolepackages.models.Place;
import ru.liga.consolepackages.services.placements.OptimalPlacementService;
import ru.liga.consolepackages.services.placements.PlacementService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class OptimalPlacementServiceTest {

    private final PlacementService placementService = new OptimalPlacementService(6, 6);

    @Test
    void emptyListTest() {
        List<Package> packages = new ArrayList<>();

        List<Body> bodies = placementService.placementPackage(packages, 5);
        assertEquals(5, bodies.size());

        for (Body body : bodies) {
            for (int i = 0; i < 6; i++) {
                for (int j = 0; j < 6; j++) {
                    assertEquals(body.getElement(new Place(i, j)), ' ');
                }
            }
        }

    }

    @Test
    void singlePackageTest() {
        List<Package> packages = new ArrayList<>();
        packages.add(new Package(new char[][]{{'4', '4', '4', '4'}}));

        List<Body> bodies = placementService.placementPackage(packages, 1);

        assertEquals(1, bodies.size());
        Body body = bodies.get(0);
        char[][] testChars = new char[][]{
                {' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' '},
                {'4', '4', '4', '4', ' ', ' '}};

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                assertEquals(body.getElement(new Place(i, j)), testChars[i][j]);
            }
        }
    }

    @Test
    void multiplePackagesFitInOneBodyTest() {
        List<Package> packages = new ArrayList<>();
        packages.add(new Package(new char[][]{{'4', '4', '4', '4'}}));
        packages.add(new Package(new char[][]{{'6', '6', '6'}, {'6', '6', '6'}}));
        packages.add(new Package(new char[][]{{'9', '9', '9'}, {'9', '9', '9'}, {'9', '9', '9'}}));
        packages.add(new Package(new char[][]{{'1'}}));
        packages.add(new Package(new char[][]{{'2', '2'}}));
        packages.add(new Package(new char[][]{{'3', '3', '3'}}));

        List<Body> bodies = placementService.placementPackage(packages, 2);

        assertEquals(2, bodies.size());
        Body body1 = bodies.get(0);
        char[][] testChars = new char[][]{
                {' ', ' ', ' ', ' ', ' ', ' '},
                {'1', ' ', ' ', ' ', ' ', ' '},
                {'4', '4', '4', '4', '2', '2'},
                {'9', '9', '9', '3', '3', '3'},
                {'9', '9', '9', '6', '6', '6'},
                {'9', '9', '9', '6', '6', '6'}};

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                assertEquals(body1.getElement(new Place(i, j)), testChars[i][j]);
            }
        }

        Body body2 = bodies.get(1);
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                assertEquals(body2.getElement(new Place(i, j)), ' ');
            }
        }
    }

    @Test
    void multipleBodiesTest() {
        List<Package> packages = new ArrayList<>();
        packages.add(new Package(new char[][]{{'4', '4', '4', '4'}}));
        packages.add(new Package(new char[][]{{'6', '6', '6'}, {'6', '6', '6'}}));
        packages.add(new Package(new char[][]{{'9', '9', '9'}, {'9', '9', '9'}, {'9', '9', '9'}}));
        packages.add(new Package(new char[][]{{'1'}}));
        packages.add(new Package(new char[][]{{'2', '2'}}));
        packages.add(new Package(new char[][]{{'3', '3', '3'}}));
        packages.add(new Package(new char[][]{{'9', '9', '9'}, {'9', '9', '9'}, {'9', '9', '9'}}));
        packages.add(new Package(new char[][]{{'3', '3', '3'}}));
        packages.add(new Package(new char[][]{{'7', '7', '7'}, {'7', '7', '7', '7'}}));

        List<Body> bodies = placementService.placementPackage(packages, 2);

        assertEquals(2, bodies.size());

        char[][][] testChars = {
                {
                        {'3', '3', '3', '6', '6', '6'},
                        {'7', '7', '7', '6', '6', '6'},
                        {'7', '7', '7', '7', '2', '2'},
                        {'9', '9', '9', '9', '9', '9'},
                        {'9', '9', '9', '9', '9', '9'},
                        {'9', '9', '9', '9', '9', '9'}
                },
                {
                        {' ', ' ', ' ', ' ', ' ', ' '},
                        {' ', ' ', ' ', ' ', ' ', ' '},
                        {' ', ' ', ' ', ' ', ' ', ' '},
                        {' ', ' ', ' ', ' ', ' ', ' '},
                        {'3', '3', '3', ' ', ' ', ' '},
                        {'4', '4', '4', '4', '1', ' '}
                }
        };

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 6; j++) {
                for (int k = 0; k < 6; k++) {
                    assertEquals(bodies.get(i).getElement(new Place(j, k)), testChars[i][j][k]);
                }
            }
        }


        assertThrows(SmallNumberBodiesException.class, () -> {
            placementService.placementPackage(packages, 1);
        });
    }

}
