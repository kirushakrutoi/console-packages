package ru.liga.consolepackages.services.telegram.processtextmessages.impls;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.liga.consolepackages.services.packages.PackageService;
import ru.liga.consolepackages.services.telegram.processtextmessages.ProcessTextMessageService;

@Service("Получить посылку")
public class FindPackage implements ProcessTextMessageService {

    private final PackageService packageService;

    public FindPackage(PackageService packageService) {
        this.packageService = packageService;
    }

    @Override
    public SendMessage processMessage(Message message) {
        String packageName = message.getText().split(" ")[2];
        String text = packageService.findByName(packageName).toString();
        return createResponse(text, message);
    }
}
