package com.example.vocabulary.service.server.controllers;

import com.example.vocabulary.service.api.VocabularyServiceApi;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VocabularyController implements VocabularyServiceApi {

    @RequestMapping("/")
    public String root() {
        return "Vocabulary Service";
    }
}
