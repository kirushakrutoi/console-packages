package ru.liga.services;

import org.junit.jupiter.api.Test;
import ru.liga.consolepackages.models.Body;
import ru.liga.consolepackages.models.Package;
import ru.liga.consolepackages.models.Place;
import ru.liga.consolepackages.services.placements.PlacementService;
import ru.liga.consolepackages.services.placements.UniformPlacementService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UniformPlacementServiceTest {
    private final PlacementService placementService = new UniformPlacementService();

    @Test
    void emptyListTest() {
        List<Package> packages = new ArrayList<>();

        List<Body> emptyBodies = new ArrayList<>();
        emptyBodies.add(new Body(6,6));

        List<Body> bodies = placementService.placementPackage(packages, emptyBodies);
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

        List<Body> emptyBodies = new ArrayList<>();
        emptyBodies.add(new Body(6,6));

        List<Body> bodies = placementService.placementPackage(packages, emptyBodies);

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
    void multiplePackagesTest() {
        List<Package> packages = new ArrayList<>();
        packages.add(new Package(new char[][]{{'4', '4', '4', '4'}}));
        packages.add(new Package(new char[][]{{'6', '6', '6'}, {'6', '6', '6'}}));
        packages.add(new Package(new char[][]{{'9', '9', '9'}, {'9', '9', '9'}, {'9', '9', '9'}}));

        List<Body> emptyBodies = new ArrayList<>();
        emptyBodies.add(new Body(6,6));
        emptyBodies.add(new Body(6,6));
        emptyBodies.add(new Body(6,6));

        List<Body> bodies = placementService.placementPackage(packages, emptyBodies);
        assertEquals(3, bodies.size());

        char[][][] testChars = {
                {
                        {' ', ' ', ' ', ' ', ' ', ' '},
                        {' ', ' ', ' ', ' ', ' ', ' '},
                        {' ', ' ', ' ', ' ', ' ', ' '},
                        {'9', '9', '9', ' ', ' ', ' '},
                        {'9', '9', '9', ' ', ' ', ' '},
                        {'9', '9', '9', ' ', ' ', ' '}
                },
                {
                        {' ', ' ', ' ', ' ', ' ', ' '},
                        {' ', ' ', ' ', ' ', ' ', ' '},
                        {' ', ' ', ' ', ' ', ' ', ' '},
                        {' ', ' ', ' ', ' ', ' ', ' '},
                        {'6', '6', '6', ' ', ' ', ' '},
                        {'6', '6', '6', ' ', ' ', ' '}
                },
                {
                        {' ', ' ', ' ', ' ', ' ', ' '},
                        {' ', ' ', ' ', ' ', ' ', ' '},
                        {' ', ' ', ' ', ' ', ' ', ' '},
                        {' ', ' ', ' ', ' ', ' ', ' '},
                        {' ', ' ', ' ', ' ', ' ', ' '},
                        {'4', '4', '4', '4', ' ', ' '}

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

    @Test
    void secondMultiplePackagesTest() {
        List<Package> packages = new ArrayList<>();
        packages.add(new Package(new char[][]{{'4', '4', '4', '4'}}));
        packages.add(new Package(new char[][]{{'6', '6', '6'}, {'6', '6', '6'}}));
        packages.add(new Package(new char[][]{{'9', '9', '9'}, {'9', '9', '9'}, {'9', '9', '9'}}));
        packages.add(new Package(new char[][]{{'2', '2'}}));

        List<Body> emptyBodies = new ArrayList<>();
        emptyBodies.add(new Body(6,6));
        emptyBodies.add(new Body(6,6));

        List<Body> bodies = placementService.placementPackage(packages, emptyBodies);
        assertEquals(2, bodies.size());

        char[][][] testChars = {
                {
                        {' ', ' ', ' ', ' ', ' ', ' '},
                        {' ', ' ', ' ', ' ', ' ', ' '},
                        {' ', ' ', ' ', ' ', ' ', ' '},
                        {'9', '9', '9', ' ', ' ', ' '},
                        {'9', '9', '9', ' ', ' ', ' '},
                        {'9', '9', '9', '2', '2', ' '}
                },
                {
                        {' ', ' ', ' ', ' ', ' ', ' '},
                        {' ', ' ', ' ', ' ', ' ', ' '},
                        {' ', ' ', ' ', ' ', ' ', ' '},
                        {'4', '4', '4', '4', ' ', ' '},
                        {'6', '6', '6', ' ', ' ', ' '},
                        {'6', '6', '6', ' ', ' ', ' '}
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
