package com.example.entity.repository;

import com.example.entity.word.domain.Category;
import com.example.entity.word.domain.Subject;
import com.example.entity.word.domain.Word;
import com.example.entity.word.repository.SubjectRepository;
import com.example.entity.word.repository.WordRepoitory;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback(value = false)
class WordRepoitoryTest {

    @Autowired
    WordRepoitory wordRepoitory;
    @Autowired
    SubjectRepository subjectRepository;

    @Test
    public void createWord() throws Exception {

        /**
         * case 1 subject가 존재 O
         * case 2 subject가 존재 X
         */
        // subject 존재 X
        Subject subject = Subject.builder()
                .subjectName("fruit")
                .build();

        // subject 존재 O
//        subject

        // given
        Word apple = Word.builder()
                .wordName("banana")
                .category(Category.WORD)
                .subject(subject)    // 요상함...   // entitiy
                .videoUrl("url").build();

        // when
        wordRepoitory.save(apple);

        // then
        Word findWord = wordRepoitory.findByWordName("apple");
        assertThat(apple.getWordName()).isEqualTo(findWord.getWordName());

    }
    @Test
    public void 단어테스트() throws Exception {
        // given
        Subject fruit = Subject.builder()
                .subjectName("fruit")
                .build();

        Subject fr = Subject.builder()
                .subjectName("과일")
                .build();

        Word word = Word.builder()
                .subject(fruit)
                .wordName("사과")
                .videoUrl("1a")
                .category(Category.WORD)
                .build();
        
        // when
        word.changeSubject(fr);
        // then
        System.out.println("word.getSubject().getSubjectName() = " + word.getSubject().getSubjectName());
        System.out.println("fr.getWordList().get(0).getWordName() = " + fr.getWordList().get(0).getWordName());
        Assertions.assertThat(fruit.getWordList().size()).isEqualTo(0);

    }
}