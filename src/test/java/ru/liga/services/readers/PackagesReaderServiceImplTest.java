package ru.liga.services.readers;

import org.junit.jupiter.api.Test;
import ru.liga.consolepackages.models.Body;
import ru.liga.consolepackages.models.Package;
import ru.liga.consolepackages.models.Place;
import ru.liga.consolepackages.services.readers.PackagesReaderService;
import ru.liga.consolepackages.services.readers.PackagesReaderServiceImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PackagesReaderServiceImplTest {
    private final PackagesReaderService packagesReaderService = new PackagesReaderServiceImpl();

    @Test
    public void emptyTxtFileTest() throws IOException {
        List<Package> packages =
                packagesReaderService.readPackagesFromTxt(
                        "src/test/resources/readerservicetestfiles/emptyFile.txt"
                );

        assertTrue(packages.isEmpty());
    }

    @Test
    public void onePackageTxtFileTest() throws IOException {
        List<Package> packages =
                packagesReaderService.readPackagesFromTxt(
                        "src/test/resources/readerservicetestfiles/onePackageFile.txt"
                );
        assertEquals(1, packages.size());

        List<Package> testPackages = new ArrayList<>();
        testPackages.add(new Package(new char[][]{{'7', '7', '7'}, {'7', '7', '7', '7'}}));
        assertEquals(testPackages.get(0), packages.get(0));
    }

    @Test
    public void manyPackageTxtFiles() throws IOException {
        List<Package> packages =
                packagesReaderService.readPackagesFromTxt(
                        "src/test/resources/readerservicetestfiles/manyPackageFile.txt")
                ;

        assertEquals(11, packages.size());

        List<Package> testPackages = new ArrayList<>();
        testPackages.add(new Package(new char[][]{{'4', '4', '4', '4'}}));
        testPackages.add(new Package(new char[][]{{'6', '6', '6'}, {'6', '6', '6'}}));
        testPackages.add(new Package(new char[][]{{'9', '9', '9'}, {'9', '9', '9'}, {'9', '9', '9'}}));
        testPackages.add(new Package(new char[][]{{'1'}}));
        testPackages.add(new Package(new char[][]{{'2', '2'}}));
        testPackages.add(new Package(new char[][]{{'3', '3', '3'}}));
        testPackages.add(new Package(new char[][]{{'9', '9', '9'}, {'9', '9', '9'}, {'9', '9', '9'}}));
        testPackages.add(new Package(new char[][]{{'3', '3', '3'}}));
        testPackages.add(new Package(new char[][]{{'7', '7', '7'}, {'7', '7', '7', '7'}}));
        testPackages.add(new Package(new char[][]{{'3', '3', '3'}}));
        testPackages.add(new Package(new char[][]{{'1'}}));

        for (int i = 0; i < testPackages.size(); i++) {
            assertEquals(testPackages.get(i), packages.get(i));
        }
    }
}
