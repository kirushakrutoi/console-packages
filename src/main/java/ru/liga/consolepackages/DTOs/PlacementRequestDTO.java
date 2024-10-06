package ru.liga.consolepackages.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlacementRequestDTO {
    private String packagesNames;
    private String selectedAlgorithm;
    private String bodiesSize;
}
