package ru.liga.consolepackages.services.telegram;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.liga.consolepackages.exceptions.IncorrectAnswerException;
import ru.liga.consolepackages.services.telegram.processdocumentmessage.ProcessDocumentMessageService;
import ru.liga.consolepackages.services.telegram.processtextmessages.ProcessTextMessageService;

import java.util.Map;

@Service
@Slf4j
public class MessageService {
    private final Map<String, ProcessTextMessageService> processMessageServiceMap;
    private final ProcessDocumentMessageService processDocumentMessageService;

    @Autowired
    public MessageService(Map<String, ProcessTextMessageService> processMessageServiceMap, ProcessDocumentMessageService processDocumentMessageService) {
        this.processDocumentMessageService = processDocumentMessageService;
        this.processMessageServiceMap = processMessageServiceMap;
    }

    /**
     * Обрабатывает входящие обновления.
     *
     * @param update Обновление от Telegram API.
     * @return Метод API Telegram для отправки ответа пользователю или {@code null}, если ответ не требуется.
     */
    public SendMessage messageReceiver(Update update) {
        if (update.getMessage().hasDocument()) {
            log.info("The message from the telegram with the document has been received");
            return processDocumentMessageService.processDocument(update.getMessage());
        }

        if (update.hasMessage() && update.getMessage().hasText()) {
            log.info("A message with the text was received, text - {}", update.getMessage().getText());
            Message message = update.getMessage();
            String command = update.getMessage().getText().split(" ")[0] +
                    " " +
                    update.getMessage().getText().split(" ")[1];

            if (!processMessageServiceMap.containsKey(command)) {
                throw new IncorrectAnswerException("Такого действия не предусмотренно");
            }

            log.debug("the put command is {}", command);
            ProcessTextMessageService service = processMessageServiceMap.get(command);

            return service.processMessage(message);
        }
        return null;
    }
}
