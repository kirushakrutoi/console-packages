package ru.liga.mappers;

import org.junit.jupiter.api.Test;
import ru.liga.consolepackages.dtos.NewPackageDto;
import ru.liga.consolepackages.mappers.PackageMapper;
import ru.liga.consolepackages.models.Package;

import static org.assertj.core.api.Assertions.assertThat;


public class PackageMapperTest {
    @Test
    public void fromNewDtoToPackageTest() {
        PackageMapper packageMapper = new PackageMapper();
        NewPackageDto newPackageDTO = new NewPackageDto(new char[][]{{'*', '*'}}, "star", '*');
        Package testPack = new Package(1, "star", '*', new char[][]{{'1', '1'}});
        assertThat(testPack).isEqualTo(packageMapper.fromNewDtoToPackage(newPackageDTO));
    }

    @Test
    public void updatePackageAllFieldsTest() {
        PackageMapper packageMapper = new PackageMapper();
        Package newPackage = new Package("star", '*', new char[][]{{'*', '*', '*'}});
        Package oldPack = new Package(1, "asdf", '?', new char[][]{{'1', '1'}});
        packageMapper.updatePackageFields(oldPack, newPackage);
        Package changedPackage = new Package(1, "star", '*', new char[][]{{'1', '1', '1'}});
        assertThat(changedPackage).isEqualTo(oldPack);
    }

    @Test
    public void updatePackageOneFieldTest() {
        PackageMapper packageMapper = new PackageMapper();
        Package newPackage = new Package(null, '\u0000', new char[][]{{'*', '*', '*'}});
        Package oldPack = new Package(1, "asdf", '?', new char[][]{{'1', '1'}});
        packageMapper.updatePackageFields(oldPack, newPackage);
        Package changedPackage = new Package(1, "asdf", '?', new char[][]{{'1', '1', '1'}});
        assertThat(changedPackage).isEqualTo(oldPack);
    }
}
