package ru.liga.consolepackages.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.liga.consolepackages.models.Body;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CountPackageDto {
    private Map<Character, Integer> packages;
    private List<Body> bodies;
}
