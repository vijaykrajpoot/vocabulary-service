package com.example.vocabulary.service.core;

import com.example.vocabulary.service.persistence.VocabularyServicePersistenceConfig;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.support.ConfigurableConversionService;
import org.springframework.core.convert.support.DefaultConversionService;

import java.util.Set;

@Configuration
@Import({
        VocabularyServicePersistenceConfig.class,
})
@ComponentScan
public class VocabularyServiceCoreConfig {

    @Bean
    @Qualifier("vocabulary")
    public ConversionService vocabularyConversionService(Set<Converter<?,?>> converters) {
        ConfigurableConversionService conversionService = new DefaultConversionService();
        converters.forEach(conversionService::addConverter);
        return conversionService;
    }
}