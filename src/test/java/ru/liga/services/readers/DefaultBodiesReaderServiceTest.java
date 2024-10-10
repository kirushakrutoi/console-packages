package ru.liga.services.readers;

import org.junit.jupiter.api.Test;
import ru.liga.consolepackages.models.Body;
import ru.liga.consolepackages.models.Place;
import ru.liga.consolepackages.services.readers.BodiesReaderService;
import ru.liga.consolepackages.services.readers.DefaultBodiesReaderService;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class DefaultBodiesReaderServiceTest {
    BodiesReaderService bodiesReaderService;

    @Test
    public void emptyJsonFile() {
        BodiesReaderService bodiesReaderService = new DefaultBodiesReaderService();
        List<Body> bodies =
                bodiesReaderService.readBodiesFromJson(
                        "src/test/resources/readerservicetestfiles/empty_file.json"
                );

        assertThat(bodies.size()).isEqualTo(1);
        Body body = bodies.get(0);

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 6; j++) {
                assertThat(body.getElement(new Place(i, j))).isEqualTo(' ');
            }
        }
    }

    @Test
    public void onePackageJsonFileTest() {
        List<Body> bodies =
                bodiesReaderService.readBodiesFromJson(
                        "src/test/resources/readerservicetestfiles/one_package_file.json"
                );

        assertThat(bodies.size()).isEqualTo(1);
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
                assertThat(body.getElement(new Place(i, j))).isEqualTo(testChars[i][j]);
            }
        }
    }

    @Test
    void multipleBodiesTest() {
        List<Body> bodies =
                bodiesReaderService.readBodiesFromJson(
                        "src/test/resources/readerservicetestfiles/many_bodies_file.json"
                );

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
                        {'1', '1', ' ', ' ', ' ', ' '},
                        {'3', '3', '3', '3', '3', '3'},
                        {'4', '4', '4', '4', '1', '1'}
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
