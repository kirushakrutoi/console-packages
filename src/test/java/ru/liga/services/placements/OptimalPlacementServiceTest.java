package ru.liga.services.placements;

import org.junit.jupiter.api.Test;
import ru.liga.consolepackages.exceptions.pacakgesexceptions.PlacementException;
import ru.liga.consolepackages.models.Body;
import ru.liga.consolepackages.models.Package;
import ru.liga.consolepackages.models.Place;
import ru.liga.consolepackages.services.placements.OptimalPlacementService;
import ru.liga.consolepackages.services.placements.PlacementService;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class OptimalPlacementServiceTest {
    @Test
    void emptyListTest() {
        PlacementService placementService = new OptimalPlacementService();
        List<Package> packages = new ArrayList<>();

        List<Body> emptyBodies = new ArrayList<>();
        emptyBodies.add(new Body(6, 6));
        emptyBodies.add(new Body(6, 6));
        emptyBodies.add(new Body(6, 6));
        emptyBodies.add(new Body(6, 6));
        emptyBodies.add(new Body(6, 6));

        List<Body> bodies = placementService.placementPackage(packages, emptyBodies);
        assertThat(bodies.size()).isEqualTo(5);

        for (Body body : bodies) {
            for (int i = 0; i < 6; i++) {
                for (int j = 0; j < 6; j++) {
                    assertThat(body.getElement(new Place(i, j))).isEqualTo(' ');
                }
            }
        }

    }

    @Test
    void singlePackageTest() {
        PlacementService placementService = new OptimalPlacementService();
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
    void multiplePackagesFitInOneBodyTest() {
        PlacementService placementService = new OptimalPlacementService();
        List<Package> packages = new ArrayList<>();
        packages.add(new Package("4", '#', new char[][]{{'4', '4', '4', '4'}}));
        packages.add(new Package(new char[][]{{'6', '6', '6'}, {'6', '6', '6'}}));
        packages.add(new Package(new char[][]{{'9', '9', '9'}, {'9', '9', '9'}, {'9', '9', '9'}}));
        packages.add(new Package(new char[][]{{'1'}}));
        packages.add(new Package(new char[][]{{'2', '2'}}));
        packages.add(new Package(new char[][]{{'3', '3', '3'}}));

        List<Body> emptyBodies = new ArrayList<>();
        emptyBodies.add(new Body(6, 6));
        emptyBodies.add(new Body(6, 6));

        List<Body> bodies = placementService.placementPackage(packages, emptyBodies);

        assertThat(bodies.size()).isEqualTo(2);
        Body body1 = bodies.get(0);
        char[][] testChars = new char[][]{
                {' ', ' ', ' ', ' ', ' ', ' '},
                {'1', ' ', ' ', ' ', ' ', ' '},
                {'#', '#', '#', '#', '2', '2'},
                {'9', '9', '9', '3', '3', '3'},
                {'9', '9', '9', '6', '6', '6'},
                {'9', '9', '9', '6', '6', '6'}};

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                assertThat(body1.getElement(new Place(i, j))).isEqualTo(testChars[i][j]);
            }
        }

        Body body2 = bodies.get(1);
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                assertThat(body2.getElement(new Place(i, j))).isEqualTo(' ');
            }
        }
    }

    @Test
    void multipleBodiesTest() {
        PlacementService placementService = new OptimalPlacementService();
        List<Package> packages = new ArrayList<>();
        packages.add(new Package(new char[][]{{'4', '4', '4', '4'}}));
        packages.add(new Package(new char[][]{{'6', '6', '6'}, {'6', '6', '6'}}));
        packages.add(new Package(new char[][]{{'9', '9', '9'}, {'9', '9', '9'}, {'9', '9', '9'}}));
        packages.add(new Package(new char[][]{{'1'}}));
        packages.add(new Package(new char[][]{{'2', '2'}}));
        packages.add(new Package(new char[][]{{'3', '3', '3'}}));
        packages.add(new Package(new char[][]{{'9', '9', '9'}, {'9', '9', '9'}, {'9', '9', '9'}}));
        packages.add(new Package(new char[][]{{'3', '3', '3'}}));
        packages.add(new Package(new char[][]{{'7', '7', '7', ' '}, {'7', '7', '7', '7'}}));

        List<Body> emptyBodies = new ArrayList<>();
        emptyBodies.add(new Body(6, 6));
        emptyBodies.add(new Body(6, 6));

        List<Body> bodies = placementService.placementPackage(packages, emptyBodies);

        assertThat(bodies.size()).isEqualTo(2);

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
                    assertThat(bodies.get(i).getElement(new Place(j, k))).isEqualTo(testChars[i][j][k]);
                }
            }
        }

        bodies.remove(1);

        assertThatThrownBy(() -> placementService.placementPackage(packages, emptyBodies))
                .isInstanceOf(PlacementException.class);

    }

    @Test
    void multiplePackagesWithNonStandardShapeFitInOneBodyTest() {
        PlacementService placementService = new OptimalPlacementService();
        List<Package> packages = new ArrayList<>();
        packages.add(new Package(new char[][]{{'4', '4', '4', '4'}}));
        packages.add(new Package(new char[][]{{'6', '6', '6'}, {'6', '6', '6'}}));
        packages.add(new Package(new char[][]{{'9', '9', '9'}, {'9', '9', '9'}, {'9', '9', '9'}}));
        //packages.add(new Package(new char[][]{{'7', '7', '7', ' '}, {'7', '7', '7', '7'}}));
        packages.add(new Package(new char[][]{{'1'}}));
        packages.add(new Package(new char[][]{{'1'}}));
        packages.add(new Package(new char[][]{{'2', '2'}}));
        packages.add(new Package(new char[][]{{'3', '3', '3'}}));
        packages.add(new Package(new char[][]{{'#', ' ', '#'}, {'#', '#', '#'}, {'#', ' ', '#'}}));

        List<Body> emptyBodies = new ArrayList<>();
        emptyBodies.add(new Body(6, 6));
        emptyBodies.add(new Body(6, 6));

        List<Body> bodies = placementService.placementPackage(packages, emptyBodies);

        assertThat(bodies.size()).isEqualTo(2);
        Body body1 = bodies.get(0);
        char[][] testChars = new char[][]{
                {'4', '4', '4', '4', ' ', ' '},
                {'6', '6', '6', '2', '2', ' '},
                {'6', '6', '6', '3', '3', '3'},
                {'9', '9', '9', '#', '1', '#'},
                {'9', '9', '9', '#', '#', '#'},
                {'9', '9', '9', '#', '1', '#'}};

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                assertThat(body1.getElement(new Place(i, j))).isEqualTo(testChars[i][j]);
            }
        }

        Body body2 = bodies.get(1);
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                assertThat(body2.getElement(new Place(i, j))).isEqualTo(' ');
            }
        }
    }

}
