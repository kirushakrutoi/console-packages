package ru.liga.consolepackages.controllers.telegram;

import org.checkerframework.checker.units.qual.A;
import org.checkerframework.checker.units.qual.C;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.liga.consolepackages.telegram.Bot;

@RestController
public class TelegramController {
    private final Bot bot;

    @Autowired
    public TelegramController(Bot bot) {
        this.bot = bot;
    }

    /**
     * Обрабатывает запросы от Telegram API.
     *
     * @param update Обновление от Telegram API.
     * @return Метод API Telegram для отправки ответа пользователю.
     */
    @PostMapping("/")
    public BotApiMethod<?> onUpdateReceived(@RequestBody Update update) {
        return bot.onWebhookUpdateReceived(update);
    }
}
