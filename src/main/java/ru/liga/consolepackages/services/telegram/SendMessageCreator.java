package ru.liga.consolepackages.services.telegram;

import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

@Service
public class SendMessageCreator {
    public SendMessage createResponse(String text, Message message) {
        SendMessage response = new SendMessage();
        response.setText(text);
        response.setChatId(message.getChatId());
        return response;
    }
}
