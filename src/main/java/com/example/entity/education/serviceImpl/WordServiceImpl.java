package com.example.entity.education.serviceImpl;

import com.example.entity.domain.Category;
import com.example.entity.domain.Word;
import com.example.entity.dto.word.WordDTO;
import com.example.entity.education.service.EntityAndDtoConversionService;
import com.example.entity.education.service.WordService;
import com.example.entity.repository.WordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class WordServiceImpl implements WordService {

    private final WordRepository wordRepository;
    private final EntityAndDtoConversionService conversionService;

    @Override
    public List<WordDTO.WordResponseDto> findAllWords() {
        List<Word> findList = wordRepository.findAll();
        List<WordDTO.WordResponseDto> responseDtos = new ArrayList<>();

        for (Word words : findList) {
            responseDtos.add(conversionService.WordEntityToDto(words));
        }
        return responseDtos;
    }

    @Override
    public List<WordDTO.WordResponseDto> findWordsByCategory(Category category) {
        List<Word> findWords = wordRepository.findByCategory(category);
        List<WordDTO.WordResponseDto> list = new ArrayList<>();
        for (Word word : findWords) {
            list.add(conversionService.WordEntityToDto(word));
        }
        return list;
    }
}

