package ru.liga.consolepackages.services.telegram.processdocumentmessage.impls;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.liga.consolepackages.coordinators.CountPackageCoordinator;
import ru.liga.consolepackages.services.telegram.SendMessageCreator;
import ru.liga.consolepackages.services.telegram.TelegramApiClient;
import ru.liga.consolepackages.services.telegram.processdocumentmessage.ProcessDocumentMessageService;

@Service
public class DefaultProcessDocumentService implements ProcessDocumentMessageService {
    private final TelegramApiClient client;
    private final CountPackageCoordinator coordinator;
    private final SendMessageCreator messageCreator;

    public DefaultProcessDocumentService(TelegramApiClient client, CountPackageCoordinator coordinator, SendMessageCreator messageCreator) {
        this.client = client;
        this.coordinator = coordinator;
        this.messageCreator = messageCreator;
    }

    @Override
    public SendMessage processDocument(Message message) {
        String fileID = message.getDocument().getFileId();
        String response = coordinator.countPackage(client.getDocumentFile(fileID));
        return messageCreator.createResponse(response, message);
    }
}
