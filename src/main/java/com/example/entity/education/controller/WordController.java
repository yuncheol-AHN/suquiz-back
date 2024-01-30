package com.example.entity.education.controller;

import com.example.entity.word.Category;
import com.example.entity.education.dto.WordDTO;
import com.example.entity.education.service.WordService;
import com.example.entity.global.dto.CommonResponse;
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
    private final WordService wordService;

    @GetMapping("/all")
    public ResponseEntity<CommonResponse> allWords() {
        List<WordDTO.WordResponseDto> allWords = wordService.findAllWords();
        return new ResponseEntity<>(CommonResponse.builder()
                .status(HttpStatus.OK.value())
                .message("단어 조회 성공")
                .list(allWords)
                .build(), HttpStatus.OK);
    }

    @GetMapping("/categoryWords")
    public ResponseEntity<CommonResponse> wordsfromCategory(@RequestParam("category") String categoryParam) {
        Category category = Category.valueOf(categoryParam.toUpperCase()); // 대소문자 구분 없이 처리
        List<WordDTO.WordResponseDto> wordsInCategory = wordService.findWordsByCategory(category);
        return new ResponseEntity<>(CommonResponse.builder()
                .status(HttpStatus.OK.value())
                .message("카테고리별 단어 조회 성공")
                .list(wordsInCategory)
                .build(), HttpStatus.OK);
    }

}
