package ru.liga.consolepackages.services.telegram.processtextmessages.impls;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.liga.consolepackages.services.packages.PackageService;
import ru.liga.consolepackages.services.telegram.processtextmessages.ProcessTextMessageService;

@Service("Создать посылку")
public class CreatePackage implements ProcessTextMessageService {
    private final String SUCCESS = "Посылка успешно создана";
    private final PackageService packageService;

    public CreatePackage(PackageService packageService) {
        this.packageService = packageService;
    }

    @Override
    public SendMessage processMessage(Message message) {
        String sPack = message.getText().split("json:")[1];
        packageService.create(sPack);
        return createResponse(SUCCESS, message);
    }
}
