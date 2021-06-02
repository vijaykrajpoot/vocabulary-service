package com.example.vocabulary.service.server;

import com.example.vocabulary.service.core.VocabularyServiceCoreConfig;
import com.example.vocabulary.service.api.config.VocabularyServiceSwaggerConfig;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import({
        VocabularyServiceSwaggerConfig.class,
        VocabularyServiceCoreConfig.class,
})
public class VocabularyServiceServerConfig {

}
