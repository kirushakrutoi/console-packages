package ru.liga.consolepackages.services.telegram.processtextmessages.impls;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.liga.consolepackages.services.packages.PackageService;
import ru.liga.consolepackages.services.telegram.SendMessageCreator;
import ru.liga.consolepackages.services.telegram.processtextmessages.ProcessTextMessageService;

@Service("Удалить посылку")
public class DeletePackage implements ProcessTextMessageService {
    private final String SUCCESS = "Посылка успешно удалена";
    private final PackageService packageService;
    private final SendMessageCreator messageCreator;

    public DeletePackage(PackageService packageService, SendMessageCreator messageCreator) {
        this.packageService = packageService;
        this.messageCreator = messageCreator;
    }

    @Override
    public SendMessage processMessage(Message message) {
        String packNAme = message.getText().split(" ")[2];
        packageService.delete(packNAme);
        return messageCreator.createResponse(SUCCESS, message);
    }
}
