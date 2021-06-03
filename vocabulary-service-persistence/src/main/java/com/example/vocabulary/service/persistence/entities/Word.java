package com.example.vocabulary.service.persistence.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Word {

    private String id;
    String word;
    String meaning;
    String sentence;

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }

    public String getSentence() {
        return sentence;
    }

    public void setSentence(String sentence) {
        this.sentence = sentence;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Id
    public String getId() {
        return id;
    }
}
