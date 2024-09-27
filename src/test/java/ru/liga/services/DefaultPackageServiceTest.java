package ru.liga.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import ru.liga.consolepackages.models.Body;
import ru.liga.consolepackages.models.Package;
import ru.liga.consolepackages.models.Place;
import ru.liga.consolepackages.repositories.FilePackageRepository;
import ru.liga.consolepackages.services.DefaultPackageService;
import ru.liga.consolepackages.validators.PackageValidator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DefaultPackageServiceTest {

    private final DefaultPackageService defaultPackageService = new DefaultPackageService(new FilePackageRepository(new ObjectMapper()), new PackageValidator());

    @Test
    public void getAllPackagesTest() {
        List<Package> testPackages = new ArrayList<>();
        testPackages.add(new Package("1", '1', new char[][] {{'*'}}));
        testPackages.add(new Package("2", '2', new char[][] {{'*', '*'}}));
        testPackages.add(new Package("3", '3', new char[][] {{'*', '*', '*'}}));
        testPackages.add(new Package("4", '4', new char[][] {{'*', '*', '*', '*'}}));
        testPackages.add(new Package("5", '5', new char[][] {{'*', '*', '*', '*', '*'}}));
        testPackages.add(new Package("6", '6', new char[][] {{'*', '*', '*'}, {'*', '*', '*'}}));
        testPackages.add(new Package("7", '7', new char[][] {{'*', '*', '*'}, {'*', '*', '*', '*'}}));
        testPackages.add(new Package("8", '8', new char[][] {{'*', '*', '*', '*'}, {'*', '*', '*', '*'}}));
        testPackages.add(new Package("9", '9', new char[][] {{'*', '*', '*'}, {'*', '*', '*'}, {'*', '*', '*'}}));
        assertEquals(testPackages, defaultPackageService.getAll());
    }

    @Test
    public void getPackageById() {
        Package testPackage = new Package("7", '7', new char[][] {{'*', '*', '*'}, {'*', '*', '*', '*'}});
        assertEquals(testPackage, defaultPackageService.getById("10"));
    }

    @Test
    public void changePackageById() throws JsonProcessingException {
        Package testPackage = new Package("7", '7', new char[][] {{'*', '*', '*'}, {'*', '*', '*', '*'}});
        defaultPackageService.change("9", "{\"id\":\"9\",\"symbol\":\"9\",\"pack\":[\"999\",\"999\",\"999\"]}");
    }
}
