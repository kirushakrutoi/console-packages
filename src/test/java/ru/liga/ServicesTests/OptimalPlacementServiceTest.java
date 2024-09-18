package ru.liga.ServicesTests;

import org.junit.jupiter.api.Test;
import ru.liga.ConsolePackages.models.Body;
import ru.liga.ConsolePackages.models.Package;
import ru.liga.ConsolePackages.models.Place;
import ru.liga.ConsolePackages.services.interfaces.PlacementService;
import ru.liga.ConsolePackages.services.OptimalPlacementService;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class OptimalPlacementServiceTest {

    private final PlacementService placementService = new OptimalPlacementService(6,6);

    @Test
    void emptyListTest() {
        List<Package> packages = new ArrayList<>();

        List<Body> bodies = placementService.placementPackage(packages);
        assertEquals(1, bodies.size());
        Body body = bodies.get(0);

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                assertEquals(body.getElement(new Place(i, j)), ' ');
            }
        }

    }

    @Test
    void singlePackageTest() {
        List<Package> packages = new ArrayList<>();
        packages.add(new Package(new char[][]{{'4', '4', '4', '4'}}));

        List<Body> bodies = placementService.placementPackage(packages);

        assertEquals(1, bodies.size());
        Body body = bodies.get(0);
        char[][] testChars = new char[][] {
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

        List<Body> bodies = placementService.placementPackage(packages);

        assertEquals(1, bodies.size());
        Body body = bodies.get(0);
        char[][] testChars = new char[][] {
                {' ', ' ', ' ', ' ', ' ', ' '},
                {'1', ' ', ' ', ' ', ' ', ' '},
                {'4', '4', '4', '4', '2', '2'},
                {'9', '9', '9', '3', '3', '3'},
                {'9', '9', '9', '6', '6', '6'},
                {'9', '9', '9', '6', '6', '6'}};
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                assertEquals(body.getElement(new Place(i, j)), testChars[i][j]);
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

        List<Body> bodies = placementService.placementPackage(packages);

        assertEquals(2, bodies.size());

        char[][][] testChars =  {
                {
                        {'3', '3', '3', '6', '6', '6'},
                        {'7', '7', '7', '6', '6', '6'},
                        {'7', '7', '7', '7', '2', '2'},
                        {'9', '9', '9', '9', '9', '9'},
                        {'9', '9', '9', '9', '9', '9'},
                        {'9', '9', '9', '9', '9', '9'}
                }, {
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
    }

}
