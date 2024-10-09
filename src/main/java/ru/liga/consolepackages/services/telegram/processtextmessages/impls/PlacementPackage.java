package ru.liga.consolepackages.services.telegram.processtextmessages.impls;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.liga.consolepackages.coordinators.PlacePackagesCoordinator;
import ru.liga.consolepackages.services.telegram.processtextmessages.ProcessTextMessageService;

@Service("Разместить посылки")
public class PlacementPackage implements ProcessTextMessageService {

    private final PlacePackagesCoordinator coordinator;

    public PlacementPackage(PlacePackagesCoordinator coordinator) {
        this.coordinator = coordinator;
    }

    @Override
    public SendMessage processMessage(Message message) {
        String bodiesSize = message.getText().split("\"")[1];
        String sPackages = message.getText().split("\"")[3];
        String selectedAlgorithm = message.getText().split("\"")[4].trim();
        String text = coordinator.getFilledBodiesFromString(bodiesSize, sPackages, selectedAlgorithm);
        return createResponse(text, message);
    }
}
