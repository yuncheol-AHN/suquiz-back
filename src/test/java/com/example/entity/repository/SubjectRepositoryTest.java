package com.example.entity.repository;

import com.example.entity.word.domain.Category;
import com.example.entity.word.domain.Subject;
import com.example.entity.word.domain.Word;
import com.example.entity.word.repository.SubjectRepository;
import com.example.entity.word.repository.WordRepoitory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
@Rollback(value = false)
class SubjectRepositoryTest {

    @Autowired
    SubjectRepository subjectRepository;
    @Autowired
    WordRepoitory wordRepoitory;

    @Test
    public void saveTest() throws Exception {
        
        // given
        Subject fruit = Subject.builder()
                .subjectName("fruit")
                .build();

        if (!subjectRepository.existsBySubjectName("fruit")) {
            subjectRepository.save(fruit);
        }

//        Word carrot = Word.builder()
//                .wordName("carrot")
//                .category(Category.WORD)
//                .subject(fruit)    // 요상함...   // entitiy
//                .videoUrl("url").build();
//
//        Word selectWord = wordRepoitory.save(carrot);

        // create & read
        Subject findSubject = subjectRepository.findBySubjectName("fruit");
        assertThat(fruit.getSubjectName()).isEqualTo((findSubject.getSubjectName()));

        // update
        /* 일단 없어도 될듯 */
    }

    @Test
    public void 테스트() throws Exception {
        // given
        Subject nameA = Subject.builder()
                .subjectName("달력")
                .wordList(new ArrayList<>())
                .build();

        Word word = Word.builder()
                .wordName("1월")
                .subject(nameA)
                .videoUrl("1a")
                .build();

        Word word2 = Word.builder()
                .category(Category.WORD)
                .wordName("1월")
                .subject(nameA)
//                .videoUrl("1a")
                .build();

//        if (word.getSubject() == null) {
//            word.toBuilder()
//                    .subject()
//        }
        System.out.println("1:::" + word);

//        Word testWord = testWord.toBuilder()
//                        .subject(nameA)
//                        .build();
        // when
        System.out.println("nameA.getWordList() = " + nameA.getWordList().get(0).getWordName());
        System.out.println("word.getSubject() = " + word.getSubject().getSubjectName());
        // then

//        Assertions.assertThat(word.getSubject().getSubjectName()).isEqualTo("달력");
    }
}