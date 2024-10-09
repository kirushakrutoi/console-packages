package ru.liga.consolepackages.services.telegram.processtextmessages.impls;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.liga.consolepackages.services.packages.PackageService;
import ru.liga.consolepackages.services.telegram.processtextmessages.ProcessTextMessageService;

@Service("Изменить посылку")
public class ChangePackage implements ProcessTextMessageService {
    private final String SUCCESS = "Посылка успешно изменена";
    private final PackageService packageService;

    public ChangePackage(PackageService packageService) {
        this.packageService = packageService;
    }

    @Override
    public SendMessage processMessage(Message message) {
        String packName = message.getText().split(" ")[2];
        String sPack = message.getText().split("json:")[1];
        packageService.change(packName, sPack);
        return createResponse(SUCCESS, message);
    }
}
