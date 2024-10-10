package ru.liga.consolepackages.services.telegram.processdocumentmessage;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;

public interface ProcessDocumentMessageService {
    /**
     * Обрабатывает входящий документ от пользователя.
     *
     * @param message Входящее сообщение от пользователя с документом.
     * @return Метод API Telegram для отправки ответа пользователю.
     */
    SendMessage processDocument(Message message);
}
