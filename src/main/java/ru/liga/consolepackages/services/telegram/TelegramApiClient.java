package ru.liga.consolepackages.services.telegram;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;
import org.springframework.web.client.RestTemplate;
import org.telegram.telegrambots.meta.api.objects.ApiResponse;

import java.io.File;
import java.io.FileOutputStream;
import java.text.MessageFormat;
import java.util.Objects;

@Service
public class TelegramApiClient {
    private final String URL;
    private final String TOKEN;
    private final RestTemplate restTemplate;

    public TelegramApiClient(@Value("${bot.api-url}") String url, @Value("${bot.token}") String token) {
        URL = url;
        TOKEN = token;
        this.restTemplate = new RestTemplate();
    }

    /**
     * Получает файл из чата в телеграмм по его идентификатору.
     *
     * @param fileId Идентификатор файла.
     * @return Файл.
     */
    public File getDocumentFile(String fileId) {
        try {
            return restTemplate.execute(
                    Objects.requireNonNull(getDocumentTelegramFileUrl(fileId)),
                    HttpMethod.GET,
                    null,
                    clientHttpResponse -> {
                        File ret = File.createTempFile("download", "tmp");
                        StreamUtils.copy(clientHttpResponse.getBody(), new FileOutputStream(ret));
                        return ret;
                    });
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    private String getDocumentTelegramFileUrl(String fileId) {
        try {
            ResponseEntity<ApiResponse<org.telegram.telegrambots.meta.api.objects.File>> response = restTemplate.exchange(
                    MessageFormat.format("{0}bot{1}/getFile?file_id={2}", URL, TOKEN, fileId),
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<ApiResponse<org.telegram.telegrambots.meta.api.objects.File>>() {
                    }
            );
            return Objects.requireNonNull(response.getBody()).getResult().getFileUrl(this.TOKEN);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }
}
