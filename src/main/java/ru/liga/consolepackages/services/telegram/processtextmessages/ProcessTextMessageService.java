package ru.liga.consolepackages.services.telegram.processtextmessages;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;


public interface ProcessTextMessageService {

    /**
     * Обрабатывает текстовое сообщение от пользователя.
     *
     * @param message Текстовое сообщение.
     * @return Метод API Telegram для отправки ответа пользователю.
     */
    SendMessage processMessage(Message message);
}
