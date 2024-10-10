package ru.liga.consolepackages.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.liga.consolepackages.models.Body;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlacementResponseDto {
    private List<Body> bodies;
}
