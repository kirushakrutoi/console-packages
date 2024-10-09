package ru.liga.mappers;

import org.junit.jupiter.api.Test;
import ru.liga.consolepackages.DTOs.ChangePackageDTO;
import ru.liga.consolepackages.DTOs.NewPackageDTO;
import ru.liga.consolepackages.mappers.PackageMapper;
import ru.liga.consolepackages.models.Package;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class PackageMapperTest {
    private final PackageMapper packageMapper = new PackageMapper();

    @Test
    public void fromNewDtoToPackageTest() {
        NewPackageDTO newPackageDTO = new NewPackageDTO(new char[][]{{'*', '*'}}, "star", '*');
        Package testPack = new Package(1, "star", '*', new char[][]{{'1', '1'}});
        assertEquals(testPack, packageMapper.fromNewDtoToPackage(newPackageDTO));
    }

    @Test
    public void updatePackageAllFieldsTest() {
        ChangePackageDTO newPackageDTO = new ChangePackageDTO(new char[][]{{'*', '*', '*'}}, "star", '*');
        Package oldPack = new Package(1, "asdf", '?', new char[][]{{'1', '1'}});
        packageMapper.updatePackageFields(oldPack, newPackageDTO);
        Package changedPackage = new Package(1, "star", '*', new char[][]{{'1', '1', '1'}});
        assertEquals(changedPackage, oldPack);
    }

    @Test
    public void updatePackageOneFieldTest() {
        ChangePackageDTO newPackageDTO = new ChangePackageDTO(new char[][]{{'*', '*', '*'}}, null, '\u0000');
        Package oldPack = new Package(1, "asdf", '?', new char[][]{{'1', '1'}});
        packageMapper.updatePackageFields(oldPack, newPackageDTO);
        Package changedPackage = new Package(1, "asdf", '?', new char[][]{{'1', '1', '1'}});
        assertEquals(changedPackage, oldPack);
    }
}
