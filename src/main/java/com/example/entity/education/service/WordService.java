package com.example.entity.education.service;

import com.example.entity.word.Category;
import com.example.entity.education.dto.WordDTO;

import java.util.List;

public interface WordService {

    List<WordDTO.WordResponseDto> findAllWords();

    List<WordDTO.WordResponseDto> findWordsByCategory(Category category);
}
