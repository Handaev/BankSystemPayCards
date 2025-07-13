package com.example.BankCardManagementSystems.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        // Опционально: настройки (примеры ниже)
        modelMapper.getConfiguration()
                .setMatchingStrategy(MatchingStrategies.STRICT) // строгое соответствие полей
                .setSkipNullEnabled(true); // игнорировать null при маппинге

        return modelMapper;
    }
}
