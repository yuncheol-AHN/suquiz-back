package com.example.entity.singleplay;

import com.example.entity.singleplay.domain.SingleHistory;
import com.example.entity.singleplay.repository.SingleHistoryRepository;
import com.example.entity.user.domain.User;
import com.example.entity.user.repository.UserRepository;
import com.example.entity.word.domain.Category;
import com.example.entity.word.domain.Subject;
import com.example.entity.word.domain.Word;
import com.example.entity.word.repository.SubjectRepository;
import com.example.entity.word.repository.WordRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;

@SpringBootTest
@Transactional
@Rollback(value = false)
public class SingleplayTest {

    @Autowired UserRepository userRepository;
    @Autowired WordRepository wordRepository;
    @Autowired SubjectRepository subjectRepository;
    @Autowired SingleHistoryRepository singleHistoryRepository;

    @Test
    public void test() {

        Subject fruit = Subject.builder().subjectName("fruit").build();
        subjectRepository.save(fruit);

        Subject animal = Subject.builder().subjectName("animal").build();
        subjectRepository.save(animal);

        Word apple = Word.builder()
                .wordName("apple")
                .category(Category.WORD)
                .subject(fruit)
                .build();

        Word kiwi = Word.builder()
                .wordName("kiwi")
                .category(Category.WORD)
                .subject(fruit)
                .build();

        Word lion = Word.builder()
                .wordName("lion")
                .category(Category.WORD)
                .subject(animal)
                .build();

        wordRepository.save(apple);
        wordRepository.save(kiwi);
        wordRepository.save(lion);
    }

    @Test
    public void singleHistoryTest() {

        User user = User.builder()
                .email("1@1")
                .maxCorrectCount(3)
                .build();

        userRepository.save(user);

        SingleHistory build = SingleHistory.builder()
                .user(user)
                .createDate(LocalDate.now().minusDays(1))
                .trialCount(1)
                .isCorrect(true)
                .build();

        SingleHistory build1 = SingleHistory.builder()
                .user(user)
                .createDate(LocalDate.now().minusDays(3))
                .trialCount(2)
                .isCorrect(false)
                .build();

        SingleHistory build2 = SingleHistory.builder()
                .user(user)
                .createDate(LocalDate.now().minusDays(4))
                .trialCount(4)
                .isCorrect(true)
                .build();

        SingleHistory build3 = SingleHistory.builder()
                .user(user)
                .createDate(LocalDate.now().minusDays(5))
                .trialCount(2)
                .isCorrect(true)
                .build();

        SingleHistory build4 = SingleHistory.builder()
                .user(user)
                .createDate(LocalDate.now().minusDays(7))
                .trialCount(4)
                .isCorrect(false)
                .build();

        singleHistoryRepository.save(build);
        singleHistoryRepository.save(build1);
        singleHistoryRepository.save(build2);
        singleHistoryRepository.save(build3);
        singleHistoryRepository.save(build4);

    }
}
