package ru.liga.consolepackages.utils;

import ru.liga.consolepackages.exceptions.IncorrectAnswerException;
import ru.liga.consolepackages.services.placements.OptimalPlacementService;
import ru.liga.consolepackages.services.placements.PlacementService;
import ru.liga.consolepackages.services.placements.UniformPlacementService;

import java.util.HashMap;
import java.util.Map;

public class PlacementServiceFactory {
    private static final Map<String, PlacementService> placementServicesMap = new HashMap<>();

    public PlacementServiceFactory(int width, int length) {
        placementServicesMap.put("o", new OptimalPlacementService(width, length));
        placementServicesMap.put("u", new UniformPlacementService(width, length));
    }

    /**
     * Метод для получения сервиса размещения посылок для указанного типа алгоритма.
     *
     * @param algorithmType тип алгоритма
     * @return сервис размещения посылок
     * @throws IncorrectAnswerException если указан некорректный тип алгоритма
     */
    public PlacementService getPlacementService(String algorithmType) {
        if (!placementServicesMap.containsKey(algorithmType)) {
            throw new IncorrectAnswerException("Incorrect algorithm type");
        }

        return placementServicesMap.get(algorithmType);
    }
}
