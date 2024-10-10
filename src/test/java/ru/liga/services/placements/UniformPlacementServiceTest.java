package ru.liga.services.placements;

import org.junit.jupiter.api.Test;
import ru.liga.consolepackages.models.Body;
import ru.liga.consolepackages.models.Package;
import ru.liga.consolepackages.models.Place;
import ru.liga.consolepackages.services.placements.PlacementService;
import ru.liga.consolepackages.services.placements.UniformPlacementService;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class UniformPlacementServiceTest {
    PlacementService placementService = new UniformPlacementService();

    @Test
    void emptyListTest() {
        PlacementService placementService = new UniformPlacementService();
        List<Package> packages = new ArrayList<>();

        List<Body> emptyBodies = new ArrayList<>();
        emptyBodies.add(new Body(6, 6));

        List<Body> bodies = placementService.placementPackage(packages, emptyBodies);
        assertThat(bodies.size()).isEqualTo(1);
        Body body = bodies.get(0);

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                assertThat(body.getElement(new Place(i, j))).isEqualTo(' ');
            }
        }

    }

    @Test
    void singlePackageTest() {
        PlacementService placementService = new UniformPlacementService();
        List<Package> packages = new ArrayList<>();
        packages.add(new Package(new char[][]{{'4', '4', '4', '4'}}));

        List<Body> emptyBodies = new ArrayList<>();
        emptyBodies.add(new Body(6, 6));

        List<Body> bodies = placementService.placementPackage(packages, emptyBodies);

        assertThat(bodies.size()).isEqualTo(1);
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
                assertThat(body.getElement(new Place(i, j))).isEqualTo(testChars[i][j]);
            }
        }
    }

    @Test
    void multiplePackagesTest() {
        PlacementService placementService = new UniformPlacementService();
        List<Package> packages = new ArrayList<>();
        packages.add(new Package(new char[][]{{'4', '4', '4', '4'}}));
        packages.add(new Package(new char[][]{{'6', '6', '6'}, {'6', '6', '6'}}));
        packages.add(new Package(new char[][]{{'9', '9', '9'}, {'9', '9', '9'}, {'9', '9', '9'}}));

        List<Body> emptyBodies = new ArrayList<>();
        emptyBodies.add(new Body(6, 6));
        emptyBodies.add(new Body(6, 6));
        emptyBodies.add(new Body(6, 6));

        List<Body> bodies = placementService.placementPackage(packages, emptyBodies);
        assertThat(bodies.size()).isEqualTo(1);

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
                    assertThat(bodies.get(i).getElement(new Place(j, k))).isEqualTo(testChars[i][j][k]);
                }
            }
        }
    }

    @Test
    void secondMultiplePackagesTest() {
        PlacementService placementService = new UniformPlacementService();
        List<Package> packages = new ArrayList<>();
        packages.add(new Package(new char[][]{{'4', '4', '4', '4'}}));
        packages.add(new Package(new char[][]{{'6', '6', '6'}, {'6', '6', '6'}}));
        packages.add(new Package(new char[][]{{'9', '9', '9'}, {'9', '9', '9'}, {'9', '9', '9'}}));
        packages.add(new Package(new char[][]{{'2', '2'}}));

        List<Body> emptyBodies = new ArrayList<>();
        emptyBodies.add(new Body(6, 6));
        emptyBodies.add(new Body(6, 6));

        List<Body> bodies = placementService.placementPackage(packages, emptyBodies);
        assertThat(bodies.size()).isEqualTo(2);

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
                    assertThat(bodies.get(i).getElement(new Place(j, k))).isEqualTo(testChars[i][j][k]);
                }
            }
        }
    }
}
