package ru.liga.consolepackages.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChangePackageDTO {
    private char[][] pack;
    private String name;
    private char symbol;
}
