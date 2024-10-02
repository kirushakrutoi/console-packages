package ru.liga.consolepackages.converters;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import ru.liga.consolepackages.models.Body;

import java.util.ArrayList;
import java.util.List;

@Component
public class BodiesConverter {
    public List<Body> fromStringToBodies(String bodiesSize) {
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
}
