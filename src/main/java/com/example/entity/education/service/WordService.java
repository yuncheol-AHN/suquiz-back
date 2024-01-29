package com.example.entity.education.service;

import com.example.entity.domain.Category;
import com.example.entity.dto.word.WordDTO;

import java.util.List;

public interface WordService {

    List<WordDTO.WordResponseDto> findAllWords();

    List<WordDTO.WordResponseDto> findWordsByCategory(Category category);
}
