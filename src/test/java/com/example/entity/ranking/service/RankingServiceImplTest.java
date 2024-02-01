package com.example.entity.ranking.service;

import com.example.entity.multiplay.domain.Quizroom;
import com.example.entity.multiplay.dto.EndQuizDto;
import com.example.entity.multiplay.repository.QuizroomRepository;
import com.example.entity.multiplay.service.QuizroomService;
import com.example.entity.multiplay.service.QuizroomServiceImplTest;
import com.example.entity.ranking.dto.RankingDto;
import com.example.entity.user.domain.Level;
import com.example.entity.user.domain.User;
import com.example.entity.user.repository.LevelRepository;
import com.example.entity.user.repository.UserRepository;
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

@SpringBootTest
@Rollback(value = false)
public class RankingServiceImplTest {


        @Autowired
        private UserRepository userRepository;

        @Autowired
        private RankingService rankingService;


        @PersistenceContext
        EntityManager em;

        @Transactional
        @Test
        public void getRankingTest() {

            User user = User.builder()
                    .email("yuncheol1")
                    .nickname("a")
                    .level(1)
                    .xp(1)
                    .isPlaying(false)
                    .maxCorrectCount(20)
                    .imageUrl("image.com")
                    .build();
            User user2 = User.builder()
                    .email("yuncheol2")
                    .nickname("b")
                    .level(5)
                    .xp(3)
                    .isPlaying(false)
                    .maxCorrectCount(20)
                    .imageUrl("image.com")
                    .build();
            User user3 = User.builder()
                    .email("yuncheol3")
                    .nickname("c")
                    .level(10)
                    .xp(50)
                    .isPlaying(false)
                    .maxCorrectCount(20)
                    .imageUrl("image.com")
                    .build();
            User user4 = User.builder()
                    .email("yuncheol4")
                    .nickname("d")
                    .level(10)
                    .xp(100)
                    .isPlaying(false)
                    .maxCorrectCount(20)
                    .imageUrl("image.com")
                    .build();
            userRepository.save(user);
            userRepository.save(user2);
            userRepository.save(user3);
            userRepository.save(user4);
            em.flush();

            RankingDto.Response ranking = rankingService.getRanking(2L);

            System.out.println("내 순위 : " + ranking.getMyRank());
            for(RankingDto.RankDto rank : ranking.getRanking()) {
                System.out.println("닉네임 : "+ rank.getNickname() + ", 레벨 : "+  rank.getLevel()+ ", 경험치 : " + rank.getExp());
            }


        }


}
