package ru.liga.consolepackages.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlacementRequestDto {
    private String packagesNames;
    private String selectedAlgorithm;
    private String bodiesSize;
}
