package com.example.entity.education.service;

import com.example.entity.word.Category;
import com.example.entity.word.Word;

import java.util.List;

public interface WordService {

    List<Word> getWordsByCategory(Category category);

    List<Word> findAllWords();
}
