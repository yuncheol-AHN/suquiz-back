package com.example.entity.multiplay.service;

import com.example.entity.education.dto.WordDTO;
import com.example.entity.multiplay.domain.Quizroom;
import com.example.entity.multiplay.dto.EndQuizDto;
import com.example.entity.multiplay.service.QuizroomService;
import com.example.entity.multiplay.repository.QuizroomRepository;
import com.example.entity.user.domain.Level;
import com.example.entity.user.domain.User;
import com.example.entity.user.repository.LevelRepository;
import com.example.entity.user.repository.UserRepository;
import com.example.entity.word.domain.Category;
import com.example.entity.word.domain.Subject;
import com.example.entity.word.domain.Word;
import com.example.entity.word.repository.SubjectRepository;
import com.example.entity.word.repository.WordRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.util.ArrayList;
import java.util.List;


@Transactional
@SpringBootTest
@Rollback(value = false)

public class QuizroomServiceImplTest {
    @Autowired
    private QuizroomService quizroomService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private QuizroomRepository quizroomRepository;

    @Autowired
    private WordRepository wordRepository;

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private LevelRepository levelRepository;

    @PersistenceContext
    EntityManager em;
    @Test
    public void makeQuizroomTest() {
                User user = User.builder()
                .email("yuncheol4")
                .nickname("cup")
                .level(10)
                .xp(300)
                .isPlaying(false)
                .maxCorrectCount(20)
                .imageUrl("image.com")
                .build();
                userRepository.save(user);
                em.flush();
        int result = quizroomService.makeQuizroom(user.getId());
        System.out.println("=======================");
        System.out.println(result);

    }

    @Test
    public void checkIsQuizroomFullTest() {
        User user = User.builder()
                .email("yuncheol4")
                .nickname("cup")
                .level(10)
                .xp(300)
                .isPlaying(false)
                .maxCorrectCount(20)
                .imageUrl("image.com")
                .build();
        User user2 = User.builder()
                .email("yuncheol4")
                .nickname("cup")
                .level(10)
                .xp(300)
                .isPlaying(false)
                .maxCorrectCount(20)
                .imageUrl("image.com")
                .build();
        User user3 = User.builder()
                .email("yuncheol4")
                .nickname("cup")
                .level(10)
                .xp(300)
                .isPlaying(false)
                .maxCorrectCount(20)
                .imageUrl("image.com")
                .build();
        User user4 = User.builder()
                .email("yuncheol4")
                .nickname("cup")
                .level(10)
                .xp(300)
                .isPlaying(false)
                .maxCorrectCount(20)
                .imageUrl("image.com")
                .build();
        userRepository.save(user);
        userRepository.save(user2);
        userRepository.save(user3);
        userRepository.save(user4);
        Quizroom quizroom = Quizroom.builder().inviteCode("12312315").build();
        quizroomRepository.save(quizroom);
        user.changeQuizroom(quizroom);
        quizroom.addUser(user);
        quizroom.addUser(user2);
        quizroom.addUser(user3);
        quizroom.addUser(user4);
        em.flush();
        System.out.println("----------------------");
        System.out.println(quizroomService.checkIsRoomJoinable("12312315"));


    }

    @Test
    public void requestWordListTest() {
        Quizroom quizroom = Quizroom.builder().build();
        quizroomRepository.save(quizroom);
        User user = User.builder()
                .email("yuncheol4")
                .nickname("cup")
                .level(10)
                .xp(300)
                .isPlaying(false)
                .maxCorrectCount(20)
                .imageUrl("image.com")
                .build();
        User user2 = User.builder()
                .email("yuncheol4")
                .nickname("cup")
                .level(10)
                .xp(300)
                .isPlaying(false)
                .maxCorrectCount(20)
                .imageUrl("image.com")
                .build();
        User user3 = User.builder()
                .email("yuncheol4")
                .nickname("cup")
                .level(10)
                .xp(300)
                .isPlaying(false)
                .maxCorrectCount(20)
                .imageUrl("image.com")
                .build();
        User user4 = User.builder()
                .email("yuncheol4")
                .nickname("cup")
                .level(10)
                .xp(300)
                .isPlaying(false)
                .maxCorrectCount(20)
                .imageUrl("image.com")
                .build();
        userRepository.save(user);
        userRepository.save(user2);
        userRepository.save(user3);
        userRepository.save(user4);
        quizroom.addUser(user);
        quizroom.addUser(user2);
        quizroom.addUser(user3);
        quizroom.addUser((user4));
        user.changeQuizroom(quizroom);
        user2.changeQuizroom(quizroom);
        user3.changeQuizroom(quizroom);
        user4.changeQuizroom(quizroom);
        Subject fr = Subject.builder()
                .subjectName("과일")
                .build();
        subjectRepository.save(fr);
        Word word = Word.builder()
                .videoUrl("1a")
                .wordName("사과")
                .subject(fr)
                .category(Category.WORD)
                .build();
        Word word2 = Word.builder()
                .videoUrl("1a")
                .wordName("바나나")
                .subject(fr)
                .category(Category.WORD)
                .build();
        Word word3 = Word.builder()
                .videoUrl("1a")
                .wordName("키위")
                .subject(fr)
                .category(Category.WORD)
                .build();
        Word word4 = Word.builder()
                .videoUrl("1a")
                .wordName("토마토")
                .subject(fr)
                .category(Category.WORD)
                .build();
        wordRepository.save(word);
        wordRepository.save(word2);
        wordRepository.save(word3);
        wordRepository.save(word4);
        em.flush();
        List<WordDTO.WordResponseDto> list = quizroomService.startQuizroom(quizroom.getId());
        for(WordDTO.WordResponseDto wordResponseDto : list) {
            System.out.println(wordResponseDto.getWordName());
        }

    }

    @Test
    public void endQuizroomTest() {
        Quizroom quizroom = Quizroom.builder().build();
        quizroomRepository.save(quizroom);
        User user = User.builder()
                .email("yuncheol1")
                .nickname("a")
                .level(1)
                .xp(0)
                .isPlaying(false)
                .maxCorrectCount(20)
                .imageUrl("image.com")
                .build();
        User user2 = User.builder()
                .email("yuncheol2")
                .nickname("b")
                .level(1)
                .xp(0)
                .isPlaying(false)
                .maxCorrectCount(20)
                .imageUrl("image.com")
                .build();
        User user3 = User.builder()
                .email("yuncheol3")
                .nickname("c")
                .level(1)
                .xp(0)
                .isPlaying(false)
                .maxCorrectCount(20)
                .imageUrl("image.com")
                .build();
        User user4 = User.builder()
                .email("yuncheol4")
                .nickname("d")
                .level(1)
                .xp(0)
                .isPlaying(false)
                .maxCorrectCount(20)
                .imageUrl("image.com")
                .build();
        userRepository.save(user);
        userRepository.save(user2);
        userRepository.save(user3);
        userRepository.save(user4);
        int exp = 0;
        for(int i=1; i<=10; i++) {
            Level level = Level.builder().level(i).xp(exp).build();
            exp+=50;
            levelRepository.save(level);
        }

        quizroom.addUser(user);
        quizroom.addUser(user2);
        quizroom.addUser(user3);
        quizroom.addUser((user4));
        user.changeQuizroom(quizroom);
        user2.changeQuizroom(quizroom);
        user3.changeQuizroom(quizroom);
        user4.changeQuizroom(quizroom);
        List<EndQuizDto.Request> requests = new ArrayList<>();
        requests.add(EndQuizDto.Request.builder().userId(1).score(1).build());
        requests.add(EndQuizDto.Request.builder().userId(2).score(2).build());
        requests.add(EndQuizDto.Request.builder().userId(3).score(0).build());
        requests.add(EndQuizDto.Request.builder().userId(4).score(10).build());
        List<EndQuizDto.Response> resultList = quizroomService.endQuizgame(quizroom.getId(), requests);
        em.flush();
        for(EndQuizDto.Response res: resultList) {
            System.out.println(" 유저 : " + res.getUserId() + "경험치 : " + res.getExp()+ " 레벨 : " + res.getLevel());
        }
    }
}
