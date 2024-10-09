package ru.liga.consolepackages.services.telegram.processtextmessages.impls;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import ru.liga.consolepackages.services.packages.PackageService;
import ru.liga.consolepackages.services.telegram.processtextmessages.ProcessTextMessageService;

import java.util.Objects;
import java.util.stream.Collectors;

@Service("Получить все")
public class FindAllPackages implements ProcessTextMessageService {

    private final PackageService packageService;

    public FindAllPackages(PackageService packageService) {
        this.packageService = packageService;
    }

    @Override
    public SendMessage processMessage(Message message) {
        String text = packageService.getAll().stream()
                .map(Objects::toString)
                .collect(Collectors.joining("\n"));

        return createResponse(text, message);
    }
}
