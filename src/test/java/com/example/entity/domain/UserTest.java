package com.example.entity.domain;

import com.example.entity.repository.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback(value = false)
class UserTest {

    @Autowired
    UserRepository userRepository;
    @PersistenceContext
    EntityManager em;

    @Test
    public void user() throws Exception {
        // given
//        User user = User.builder()
//                .email("yuncheol")
//                .nickname("kiki")
//                .level(10)
//                .exp(1000)
//                .isPlaying(false)
//                .recentCorrectCount(30)
//                .maxCorrectCount(20)
//                .imageUrl("blblbl.com")
//                .build();

//        // do
//        em.persist(user);
//
//        em.flush();
//        em.clear();

        // then
    }
//    @Test
//    public void 유저테스트() throws Exception {
//        // given
//        User user = User.builder()
//                .image_url("test")
//                .max_correct_count(10)
//                .level(20)
//                .exp(200)
//                .isPlaying(false)
//                .recent_correct_count(15)
//                .nickname("안윤철")
//                .social_id("naver")
//                .build();
//        // do
//        userRepository.save(user);
//        // then
//        Assertions.assertThat(user.getLevel()).isEqualTo(20);
//    }
}