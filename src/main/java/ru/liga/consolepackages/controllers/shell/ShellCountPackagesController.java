package ru.liga.consolepackages.controllers.shell;

import lombok.extern.slf4j.Slf4j;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import ru.liga.consolepackages.coordinators.CountPackageCoordinator;

@Slf4j
@ShellComponent
public class ShellCountPackagesController {
    private final CountPackageCoordinator coordinator;

    public ShellCountPackagesController(CountPackageCoordinator coordinator) {
        this.coordinator = coordinator;
    }

    /**
     * Метод для подсчета посылок.
     *
     * @param filePath Путь к файлу с данными о кузовах машин.
     * @return Результат подсчета посылок или сообщение об ошибке.
     */
    @ShellMethod("Метод для подсчета посылок")
    public String countPackages(String filePath) {
        try {
            log.info("Count packages");
            return coordinator.countPackage(filePath);
        } catch (RuntimeException e) {
            log.warn(e.getMessage());
            return e.getMessage();
        }
    }

}
