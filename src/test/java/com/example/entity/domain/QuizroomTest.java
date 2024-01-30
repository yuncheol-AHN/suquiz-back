package com.example.entity.domain;

import com.example.entity.multiplay.domain.Quizroom;
import com.example.entity.multiplay.service.QuizroomService;
import com.example.entity.multiplay.repository.QuizroomRepository;
import com.example.entity.user.domain.User;
import com.example.entity.user.repository.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;


@Transactional
@SpringBootTest
@Rollback(value = false)

public class QuizroomTest {
    @Autowired
    private QuizroomService quizroomService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private QuizroomRepository quizroomRepository;

    @PersistenceContext
    EntityManager em;
    @Test
    public void makeQuizroomTest() {
                User user = User.builder()
                .email("yuncheol4")
                .nickname("cup")
                .level(10)
                .exp(300)
                .isPlaying(false)
                .recentCorrectCount(30)
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
                .exp(300)
                .isPlaying(false)
                .recentCorrectCount(30)
                .maxCorrectCount(20)
                .imageUrl("image.com")
                .build();
        User user2 = User.builder()
                .email("yuncheol4")
                .nickname("cup")
                .level(10)
                .exp(300)
                .isPlaying(false)
                .recentCorrectCount(30)
                .maxCorrectCount(20)
                .imageUrl("image.com")
                .build();
        User user3 = User.builder()
                .email("yuncheol4")
                .nickname("cup")
                .level(10)
                .exp(300)
                .isPlaying(false)
                .recentCorrectCount(30)
                .maxCorrectCount(20)
                .imageUrl("image.com")
                .build();
        User user4 = User.builder()
                .email("yuncheol4")
                .nickname("cup")
                .level(10)
                .exp(300)
                .isPlaying(false)
                .recentCorrectCount(30)
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
}
