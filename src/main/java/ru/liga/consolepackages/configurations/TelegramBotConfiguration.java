package ru.liga.consolepackages.configurations;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.meta.api.methods.updates.SetWebhook;
import ru.liga.consolepackages.configurations.properties.TelegramBotProperties;
import ru.liga.consolepackages.services.telegram.MessageService;
import ru.liga.consolepackages.telegram.Bot;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class TelegramBotConfiguration {
    private final TelegramBotProperties properties;

    @Bean
    public SetWebhook setWebhookInstance() {
        return SetWebhook.builder().url(properties.path()).build();
    }

    @Bean
    public Bot springWebhookBot(SetWebhook setWebhook,
                                MessageService messageService,
                                TelegramBotProperties telegramBotProperties) {

        return new Bot(setWebhook, telegramBotProperties, messageService);
    }
}
