package ru.liga.consolepackages.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChangePackageDTO {
    private char[][] pack;
    private String name;
    private char symbol;
}
