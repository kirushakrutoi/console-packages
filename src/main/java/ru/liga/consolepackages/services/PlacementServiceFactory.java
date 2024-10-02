package ru.liga.consolepackages.services;

import ru.liga.consolepackages.exceptions.IncorrectAnswerException;
import ru.liga.consolepackages.services.placements.OptimalPlacementService;
import ru.liga.consolepackages.services.placements.PlacementService;
import ru.liga.consolepackages.services.placements.UniformPlacementService;

import java.util.HashMap;
import java.util.Map;

public class PlacementServiceFactory {
    private static final Map<String, PlacementService> placementServicesMap = new HashMap<>();

    public PlacementServiceFactory() {
        placementServicesMap.put("o", new OptimalPlacementService());
        placementServicesMap.put("u", new UniformPlacementService());
    }

    /**
     * Метод для получения сервиса размещения посылок для указанного типа алгоритма.
     *
     * @param algorithmType тип алгоритма
     * @return сервис размещения посылок
     */
    public PlacementService getPlacementService(String algorithmType) {
        if (!placementServicesMap.containsKey(algorithmType)) {
            throw new IncorrectAnswerException("Incorrect algorithm type");
        }

        return placementServicesMap.get(algorithmType);
    }
}
