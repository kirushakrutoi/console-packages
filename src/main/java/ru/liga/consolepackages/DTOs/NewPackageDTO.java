package ru.liga.consolepackages.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewPackageDTO {
    @NotNull
    private char[][] pack;
    @NotBlank
    private String name;
    @NotBlank
    private char symbol;
}
