package ru.liga.consolepackages.configurations;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import ru.liga.consolepackages.services.writers.WriterService;
import ru.liga.consolepackages.services.writers.WriterServiceImpl;

@Configuration
public class WriterConfiguration {

    @Value("{DIR.FOR.WRITE}")
    private String DIR_FOR_WRITE;

    @Bean
    public WriterService writerService() {
        return new WriterServiceImpl("batches");
    }
}
