package com.example.entity.repository;

import com.example.entity.domain.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@Rollback(false)
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    /**
     * 생성
     * 조회(1, ALL)
     * 수정
     */

    @Test
    public void createUser() throws Exception {

        // given
        // create user
//        User user = User.builder()
//                .email("yuncheol4")
//                .nickname("cup")
//                .level(10)
//                .exp(300)
//                .isPlaying(false)
//                .recentCorrectCount(30)
//                .maxCorrectCount(20)
//                .imageUrl("image.com")
//                .build();
//
//        // when
//        // save user
//        if (!userRepository.existsByEmail(user.getEmail())) {
//            userRepository.save(user);
//        }
//
//        // find user
//        User findUser = userRepository.findByEmail("yuncheol4");
//
//        // then
//        assertThat(user.getEmail()).isEqualTo(findUser.getEmail());
    }

//    @Test
//    public void findUser() throws Exception {
//        // findById -> .get() 필요, findAll -> .get() 필요없음
//        // given
//        User user = userRepository.findByEmail("yuncheol1");
//        List<User> users = userRepository.findAll();
//
//        // do
//
//
//        // then
//        assertThat(user.getId()).isEqualTo(1);
//        assertThat(users.size()).isEqualTo(4);
//    }
//
//    @Test
//    public void updateUser() throws Exception {
//
//        // given
//        User user = userRepository.findByEmail("yuncheol1");
//
//        System.out.println("user : " + user.getId());
//        User updateUser = user.toBuilder()    // 설정 필요 !!!
//                .exp(999)
//                .build();
//
//        // do
//        userRepository.save(updateUser);   // id값을 기준으로 덮어쓰나?
//
//
//        // then
//        User findUser = userRepository.findByEmail("yuncheol1");
//        assertThat(updateUser.getExp()).isEqualTo(findUser.getExp());
//    }
}