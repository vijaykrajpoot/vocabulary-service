package com.example.vocabulary.service.server.controllers;

import com.example.vocabulary.service.api.VocabularyServiceApi;
import com.example.vocabulary.service.api.config.api.model.Word;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class VocabularyController implements VocabularyServiceApi {

    @Override
    public ResponseEntity<List<Word>> getVocabularyByType( Optional<String> type) {
        return null;
    }
}
