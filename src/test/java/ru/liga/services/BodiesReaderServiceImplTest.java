package ru.liga.services;

import org.junit.jupiter.api.Test;
import ru.liga.consolepackages.models.Body;
import ru.liga.consolepackages.models.Place;
import ru.liga.consolepackages.services.readers.BodiesReaderService;
import ru.liga.consolepackages.services.readers.BodiesReaderServiceImpl;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BodiesReaderServiceImplTest {
    private final BodiesReaderService bodiesReaderService = new BodiesReaderServiceImpl();

    @Test
    public void emptyJsonFile() throws IOException {
        List<Body> bodies =
                bodiesReaderService.readBodiesFromJson(
                        "src/test/resources/readerservicetestfiles/emptyFile.json"
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
                bodiesReaderService.readBodiesFromJson(
                        "src/test/resources/readerservicetestfiles/onePackageFile.json"
                );

        assertEquals(1, bodies.size());
        Body body = bodies.get(0);
        char[][] testChars = new char[][]{
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
                bodiesReaderService.readBodiesFromJson(
                        "src/test/resources/readerservicetestfiles/manyBodiesFile.json"
                );

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
