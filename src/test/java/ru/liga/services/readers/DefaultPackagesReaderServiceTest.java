package ru.liga.services.readers;

import org.junit.jupiter.api.Test;
import ru.liga.consolepackages.models.Package;
import ru.liga.consolepackages.services.readers.DefaultPackagesReaderService;
import ru.liga.consolepackages.services.readers.PackagesReaderService;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class DefaultPackagesReaderServiceTest {
    private final PackagesReaderService packagesReaderService = new DefaultPackagesReaderService();

    @Test
    public void emptyTxtFileTest() {
        List<Package> packages =
                packagesReaderService.readPackagesFromTxt(
                        "src/test/resources/readerservicetestfiles/empty_file.txt"
                );

        assertThat(packages).isEmpty();
    }

    @Test
    public void onePackageTxtFileTest() {
        List<Package> packages =
                packagesReaderService.readPackagesFromTxt(
                        "src/test/resources/readerservicetestfiles/one_package_file.txt"
                );
        assertThat(packages.size()).isEqualTo(1);

        List<Package> testPackages = new ArrayList<>();
        testPackages.add(new Package(new char[][]{{'7', '7', '7'}, {'7', '7', '7', '7'}}));
        assertThat(testPackages.get(0)).isEqualTo(packages.get(0));
    }

    @Test
    public void manyPackageTxtFiles() {
        List<Package> packages =
                packagesReaderService.readPackagesFromTxt(
                        "src/test/resources/readerservicetestfiles/many_package_file.txt");

        assertThat(packages.size()).isEqualTo(11);

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
            assertThat(testPackages.get(i)).isEqualTo(packages.get(i));
        }
    }
}
