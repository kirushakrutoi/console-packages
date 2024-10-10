package ru.liga.consolepackages.mappers;

import org.springframework.stereotype.Component;
import ru.liga.consolepackages.dtos.ChangePackageDto;
import ru.liga.consolepackages.dtos.NewPackageDto;
import ru.liga.consolepackages.models.Package;

@Component
public class PackageMapper {
    /**
     * Преобразует данные нового объекта DTO в объект сущности посылки.
     *
     * @param newPackageDTO Данные новой посылки.
     * @return Объект сущности посылки.
     */
    public Package fromNewDtoToPackage(NewPackageDto newPackageDTO) {
        Package pack = new Package();

        pack.setName(newPackageDTO.getName());
        pack.setSymbol(newPackageDTO.getSymbol());
        pack.setPack(newPackageDTO.getPack());

        return pack;
    }

    /**
     * Преобразует данные объекта DTO изменения в объект сущности посылки.
     *
     * @param changePackageDTO Измененные данные посылки.
     * @return Объект сущности посылки.
     */
    public Package fromChangeDtoToPackage(ChangePackageDto changePackageDTO) {
        Package newPack = new Package();
        if (changePackageDTO.getName() != null) {
            newPack.setName(changePackageDTO.getName());
        }
        if (changePackageDTO.getPack() != null) {
            newPack.setPack(changePackageDTO.getPack());
        }
        if (newPack.getSymbol() != '\u0000') {
            newPack.setSymbol(changePackageDTO.getSymbol());
        }
        return newPack;
    }

    /**
     * Обновляет поля объекта сущности посылки на основе данных объекта DTO изменения.
     *
     * @param oldPack Текущий объект сущности посылки.
     * @param newPack Измененные данные посылки.
     */
    public void updatePackageFields(Package oldPack, Package newPack) {
        if (newPack.getName() != null) {
            oldPack.setName(newPack.getName());
        }
        if (newPack.getPack() != null) {
            oldPack.setPack(newPack.getPack());
        }
        if (newPack.getSymbol() != '\u0000') {
            oldPack.setSymbol(newPack.getSymbol());
        }
    }
}
