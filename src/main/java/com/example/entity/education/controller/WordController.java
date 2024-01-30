package com.example.entity.education.controller;

import com.example.entity.word.Category;
import com.example.entity.word.Word;
import com.example.entity.education.service.WordService;
import com.example.entity.global.dto.CommonResponse;
import com.example.entity.education.repository.WordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/word")
@RequiredArgsConstructor
public class WordController {
    private final WordRepository wordRepository;
    private final WordService wordService;

    @GetMapping("/categories")
    public ResponseEntity<List<Word>> allCategories(@RequestParam Category category) {
        System.out.println("category = " + category.getClass());
        List<Word> wordsByCategory = wordService.getWordsByCategory(category);

        if (wordsByCategory.isEmpty()) {
            // 해당 카테고리에 대한 단어가 없는 경우 NOT_FOUND 상태 코드 반환
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(wordsByCategory);
        }

        System.out.println("wordsByCategory = " + wordsByCategory.get(0).getWordName());
        System.out.println("--------------절취선-------------");
        for (Word w : wordsByCategory) {
            System.out.println(w.getWordName());
        }

        return ResponseEntity.ok(wordsByCategory);
    }

    @GetMapping("/wholeWords")
    public ResponseEntity<CommonResponse> allWords() {
        List<Word> allWords = wordRepository.findAll();
        return new ResponseEntity<>(CommonResponse.builder()
                .status(HttpStatus.OK.value())
                .message("단어 조회 성공")
                .list(allWords)
                .build(), HttpStatus.OK);
    }

}
