package com.example.entity.education.serviceImpl;

import com.example.entity.domain.Category;
import com.example.entity.domain.Word;
import com.example.entity.education.service.WordService;
import com.example.entity.repository.WordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class WordServiceImpl implements WordService {

    private final WordRepository wordRepository;

    @Override
    public List<Word> getWordsByCategory(Category category) {
        System.out.println("category = " + category);
        return wordRepository.findByCategory(category);
    }

    @Override
    public List<Word> findAllWords() {
        return wordRepository.findAll();
    }
}
