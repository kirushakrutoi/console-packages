package ru.liga.consolepackages.converters;

import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class CountingPackagesConverter {
    /**
     * Преобразует карту с типами посылок и их количеством в строку.
     *
     * @param packagesIntegerMap Карта, где ключ - символ посылки, а значение - количество посылок данного типа.
     * @return Строка, содержащая информацию о типах посылок и их количестве.
     */
    public String convertPackagesIngerMapToString(Map<Character, Integer> packagesIntegerMap) {
        StringBuilder result = new StringBuilder();
        for (Map.Entry<Character, Integer> entry : packagesIntegerMap.entrySet()) {
            result.append("Package of type ").append(entry.getKey()).append(" found ").append(entry.getValue()).append("\n");
        }
        return result.toString();
    }
}
