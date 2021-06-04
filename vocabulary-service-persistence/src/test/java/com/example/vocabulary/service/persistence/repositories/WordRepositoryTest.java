package com.example.vocabulary.service.persistence.repositories;

import com.example.vocabulary.service.persistence.VocabularyServicePersistenceConfig;
import com.example.vocabulary.service.persistence.entities.WordEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {VocabularyServicePersistenceConfig.class})
@SpringBootTest(properties = {"db=h2"})
public class WordRepositoryTest {

    @Autowired
    private WorldRepository WorldRepository;

    @Test
    void test_create() {
        WordEntity entity = new WordEntity();
        WorldRepository.save(entity);
    }
}