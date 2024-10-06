package ru.liga.consolepackages.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.liga.consolepackages.models.Body;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlacementResponseDTO {
    private List<Body> bodies;
}
