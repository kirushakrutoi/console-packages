package ru.liga.consolepackages.converters;

import org.springframework.stereotype.Component;
import ru.liga.consolepackages.models.Body;

import java.util.ArrayList;
import java.util.List;

@Component
public class BodyConverter {

    /**
     * Преобразует строку с размерами посылок в список объектов `Body`.
     *
     * @param bodiesSize Строка с размерами посылок в формате "ширинаxвысота".
     * @return Список объектов `Body`.
     */
    public List<Body> converter(String bodiesSize) {
        String[] sizes = bodiesSize.split(" ");
        List<Body> bodies = new ArrayList<>();
        for (String s : sizes) {
            String[] size = s.split("x");
            int width = Integer.parseInt(size[0]);
            int height = Integer.parseInt(size[1]);
            bodies.add(new Body(width, height));

        }
        return bodies;
    }

    /**
     * Преобразует список объектов `Body` в строку.
     *
     * @param bodies Список объектов `Body`.
     * @return Строка представляющая собой заполненные кузова машин.
     */
    public String converter(List<Body> bodies) {
        StringBuilder result = new StringBuilder();
        for (Body body : bodies) {
            result.append(body).append("\n");
        }
        return result.toString();
    }
}
