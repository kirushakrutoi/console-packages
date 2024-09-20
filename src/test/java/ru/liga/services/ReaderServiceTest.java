package ru.liga.services;

import org.junit.jupiter.api.Test;
import ru.liga.consolepackages.models.Body;
import ru.liga.consolepackages.models.Package;
import ru.liga.consolepackages.models.Place;
import ru.liga.consolepackages.services.readers.ReaderService;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ReaderServiceTest {
    private final ReaderService readerService = new ReaderService();

    @Test
    public void emptyTxtFileTest() throws IOException {
        List<Package> packages =
                readerService.readPackagesFromTxt(
                        new File("src/test/resources/readerservicetestfiles/emptyFile.txt")
                );

        assertTrue(packages.isEmpty());
    }

    @Test
    public void onePackageTxtFileTest() throws IOException {
        List<Package> packages =
                readerService.readPackagesFromTxt(
                        new File("src/test/resources/readerservicetestfiles/onePackageFile.txt")
                );
        assertEquals(1, packages.size());

        List<Package> testPackages = new ArrayList<>();
        testPackages.add(new Package(new char[][]{{'7', '7', '7'}, {'7', '7', '7', '7'}}));
        assertEquals(testPackages.get(0), packages.get(0));
    }

    @Test
    public void manyPackageTxtFiles() throws IOException {
        List<Package> packages =
                readerService.readPackagesFromTxt(
                        new File("src/test/resources/readerservicetestfiles/manyPackageFile.txt")
                );

        assertEquals(11, packages.size());

        List<Package> testPackages = new ArrayList<>();
        testPackages.add(new Package(new char[][]{{'4', '4', '4', '4'}}));
        testPackages.add(new Package(new char[][]{{'6', '6', '6'}, {'6', '6', '6'}}));
        testPackages.add(new Package(new char[][]{{'9', '9', '9'}, {'9', '9', '9'}, {'9', '9', '9'}}));
        testPackages.add(new Package(new char[][]{{'1'}}));
        testPackages.add(new Package(new char[][]{{'2', '2'}}));
        testPackages.add(new Package(new char[][]{{'3', '3', '3'}}));
        testPackages.add(new Package(new char[][]{{'9', '9', '9'}, {'9', '9', '9'}, {'9', '9', '9'}}));
        testPackages.add(new Package(new char[][]{{'3', '3', '3'}}));
        testPackages.add(new Package(new char[][]{{'7', '7', '7'}, {'7', '7', '7', '7'}}));
        testPackages.add(new Package(new char[][]{{'3', '3', '3'}}));
        testPackages.add(new Package(new char[][]{{'1'}}));

        for (int i = 0; i < testPackages.size(); i++) {
            assertEquals(testPackages.get(i), packages.get(i));
        }
    }

    @Test
    public void emptyJsonFile() throws IOException {
        List<Body> bodies =
                readerService.readBodiesFromJson(
                        new File("src/test/resources/readerservicetestfiles/emptyFile.json")
                );

        assertEquals(1, bodies.size());
        Body body = bodies.get(0);

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                assertEquals(body.getElement(new Place(i, j)), ' ');
            }
        }
    }

    @Test
    public void onePackageJsonFileTest() throws IOException {
        List<Body> bodies =
                readerService.readBodiesFromJson(
                        new File("src/test/resources/readerservicetestfiles/onePackageFile.json")
                );

        assertEquals(1, bodies.size());
        Body body = bodies.get(0);
        char[][] testChars = new char[][] {
                {' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' '},
                {' ', ' ', ' ', ' ', ' ', ' '},
                {'7', '7', '7', ' ', ' ', ' '},
                {'7', '7', '7', '7', ' ', ' '}};

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                assertEquals(body.getElement(new Place(i, j)), testChars[i][j]);
            }
        }
    }

    @Test
    void multipleBodiesTest() throws IOException {
        List<Body> bodies =
                readerService.readBodiesFromJson(
                        new File("src/test/resources/readerservicetestfiles/manyBodiesFile.json")
                );

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
                {'1', '1', ' ', ' ', ' ', ' '},
                {'3', '3', '3', '3', '3', '3'},
                {'4', '4', '4', '4', '1', '1'}
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
