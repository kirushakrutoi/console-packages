package ru.liga.services.packages;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.liga.consolepackages.configurations.ObjectMapperConfiguration;
import ru.liga.consolepackages.configurations.PackageServiceTestConfig;
import ru.liga.consolepackages.mappers.PackageMapper;
import ru.liga.consolepackages.models.Package;
import ru.liga.consolepackages.repositories.JpaPackageRepository;
import ru.liga.consolepackages.services.packages.DbPackagesService;
import ru.liga.consolepackages.services.packages.PackageService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = {DbPackagesService.class, ObjectMapperConfiguration.class, JpaPackageRepository.class, PackageMapper.class})
public class DefaultPackageServiceTest {

    @MockBean
    private JpaPackageRepository jpaPackageRepository;

    @Autowired
    private PackageService packageService;

    @BeforeEach
    public void setUp() {
        List<Package> testPackages = new ArrayList<>();
        testPackages.add(new Package("1", '1', new char[][]{{'*'}}));
        testPackages.add(new Package("2", '2', new char[][]{{'*', '*'}}));
        testPackages.add(new Package("3", '3', new char[][]{{'*', '*', '*'}}));
        testPackages.add(new Package("4", '4', new char[][]{{'*', '*', '*', '*'}}));
        testPackages.add(new Package("5", '5', new char[][]{{'*', '*', '*', '*', '*'}}));
        testPackages.add(new Package("6", '6', new char[][]{{'*', '*', '*'}, {'*', '*', '*'}}));
        testPackages.add(new Package("7", '7', new char[][]{{'*', '*', '*'}, {'*', '*', '*', '*'}}));
        testPackages.add(new Package("8", '8', new char[][]{{'*', '*', '*', '*'}, {'*', '*', '*', '*'}}));
        testPackages.add(new Package("9", '9', new char[][]{{'*', '*', '*'}, {'*', '*', '*'}, {'*', '*', '*'}}));

        Mockito.when(jpaPackageRepository.findByName("7"))
                .thenReturn(Optional.of(testPackages.get(7)));
        Mockito.when(jpaPackageRepository.findAll())
                .thenReturn(testPackages);
    }

    @Test
    public void getAllPackagesTest() {
        List<Package> testPackages = new ArrayList<>();
        testPackages.add(new Package("1", '1', new char[][]{{'1'}}));
        testPackages.add(new Package("2", '2', new char[][]{{'2', '2'}}));
        testPackages.add(new Package("3", '3', new char[][]{{'3', '3', '3'}}));
        testPackages.add(new Package("4", '4', new char[][]{{'4', '4', '4', '4'}}));
        testPackages.add(new Package("5", '5', new char[][]{{'*', '*', '*', '*', '*'}}));
        testPackages.add(new Package("6", '6', new char[][]{{'*', '*', '*'}, {'*', '*', '*'}}));
        testPackages.add(new Package("7", '7', new char[][]{{'*', '*', '*'}, {'*', '*', '*', '*'}}));
        testPackages.add(new Package("8", '8', new char[][]{{'*', '*', '*', '*'}, {'*', '*', '*', '*'}}));
        testPackages.add(new Package("9", '9', new char[][]{{'*', '*', '*'}, {'*', '*', '*'}, {'*', '*', '*'}}));
        assertEquals(testPackages, packageService.getAll());
    }

    @Test
    public void getPackageById() {
        Package testPackage = new Package("7", '7', new char[][]{{'*', '*', '*'}, {'*', '*', '*', '*'}});
        assertEquals(testPackage, packageService.findByName("7"));
    }

}
