package ru.liga.converters;

import org.junit.jupiter.api.Test;
import ru.liga.consolepackages.converters.BodyConverter;
import ru.liga.consolepackages.models.Body;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BodyConverterTest {
    private final BodyConverter bodyConverter = new BodyConverter();

    @Test
    public void fromStringToBodiesTest() {
        String testString = "6x6 5x8 4x2 3x9 2x2 1x1";
        List<Body> testBodies = new ArrayList<>();
        testBodies.add(new Body(6, 6));
        testBodies.add(new Body(5, 8));
        testBodies.add(new Body(4, 2));
        testBodies.add(new Body(3, 9));
        testBodies.add(new Body(2, 2));
        testBodies.add(new Body(1, 1));
        List<Body> bodies = bodyConverter.converter(testString);
        assertEquals(testBodies.size(), bodies.size());
        for (int i = 0; i < testBodies.size(); i++) {
            assertEquals(testBodies.get(i).getWidth(), bodies.get(i).getWidth());
            assertEquals(testBodies.get(i).getLength(), bodies.get(i).getLength());
        }
    }

    @Test
    public void fromBodiesToStringTest() {
        String testString = """
                + +
                + +
                +++
                
                +    +
                +    +
                +    +
                +    +
                ++++++
                
                """;
        List<Body> testBodies = new ArrayList<>();
        testBodies.add(new Body(2, 1));
        testBodies.add(new Body(4, 4));
        String string = bodyConverter.converter(testBodies);
        assertTrue(testString.equals(string));
    }
}
