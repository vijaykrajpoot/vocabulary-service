package com.example.vocabulary.service.api;

import com.example.vocabulary.service.api.config.api.model.Vocabulary;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;

import javax.validation.Valid;
import java.util.Optional;

public interface VocabularyServiceApi extends VocabularyApi {
    @Override
    default ResponseEntity<Vocabulary> getVocabularybyType(@Valid Optional<String> type) {
        return null;
    }
}
