package com.example.entity.education.service;

import com.example.entity.domain.Category;
import com.example.entity.domain.Subject;
import com.example.entity.domain.Word;
import com.example.entity.repository.SubjectRepository;
import com.example.entity.repository.WordRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class WordServiceImplTest {

    @Autowired SubjectService subjectService;
    @Autowired WordService wordService;
    @Autowired
    SubjectRepository subjectRepository;
    @Autowired
    WordRepository wordRepository;


    @Test
    public void 테스트2() throws Exception {
        // given
        List<Word> list = wordRepository.findAll();
        // when
        for (Word word : list) {
            System.out.println("word.getWordName() = " + word.getWordName());
        }
        // then
        
    }
    @Test
    public void 테스트() throws Exception {
        // given
        Subject fruit = Subject.builder()
                .subjectName("fruit")
                .build();


        Subject fr = Subject.builder()
                .subjectName("과일")
                .build();

        Word word = Word.builder()
                .videoUrl("1a")
                .wordName("사과")
                .subject(fruit)
                .category(Category.WORD)
                .build();

        // when
        subjectRepository.save(fruit);
        subjectRepository.save(fr);
        wordRepository.save(word);
//        try {
//            word.addToSubject();
//            List<Word> findCategory = wordService.getWordsByCategory(Category.WORD);
//            // then
//            System.out.println("findCategory.get(0).getWordName() = " + findCategory.get(0).getWordName());
//        } catch (Exception e) {
//            // 예외가 발생했을 경우 로그 출력 또는 다른 예외 처리 로직 수행
//            e.printStackTrace();
//        }
    }
}