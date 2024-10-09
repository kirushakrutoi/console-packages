package ru.liga.consolepackages.telegram;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updates.SetWebhook;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.starter.SpringWebhookBot;
import ru.liga.consolepackages.configurations.properties.TelegramBotProperties;
import ru.liga.consolepackages.services.telegram.MessageService;

@Slf4j
@Component
public class Bot extends SpringWebhookBot {

    private final TelegramBotProperties properties;
    private final MessageService service;

    public Bot(SetWebhook setWebhook, TelegramBotProperties properties, MessageService service) {
        super(setWebhook);
        this.properties = properties;
        this.service = service;
    }

    @Override
    public String getBotUsername() {
        return properties.name();
    }

    @Override
    public String getBotToken() {
        return properties.token();
    }

    /**
     * Метод для обработки сообщений из Telegram.
     *
     * @param update Обновление от Telegram API.
     * @return Метод API Telegram для отправки ответа пользователю или {@code null}, если ответ не требуется.
     */
    @Override
    public BotApiMethod<?> onWebhookUpdateReceived(Update update) {
        try {
            return service.messageReceiver(update);
        } catch (RuntimeException e) {
            log.warn(e.getMessage());
            SendMessage sendMessage = new SendMessage();
            sendMessage.setText(e.getMessage());
            sendMessage.setChatId(update.getMessage().getChatId());
            return sendMessage;
        }
    }

    @Override
    public String getBotPath() {
        return properties.path();
    }
}
