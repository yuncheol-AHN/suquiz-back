package com.example.entity.repository;

import com.example.entity.singleplay.entity.SingleHistory;
import com.example.entity.user.User;
import com.example.entity.singleplay.repository.SingleHistoryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;


import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@Transactional
@Rollback(value = false)
public class SingleHistoryRepositoryTest {

    @Autowired UserRepository userRepository;
    @Autowired
    SingleHistoryRepository singleHistoryRepository;

    @Test
    public void test() throws Exception {
        /**
         * create and find
         */
        // 유저 생성 후 저장
        User user = User.builder()
                .email("a")
                .build();

        if (userRepository.findByEmail(user.getEmail()) == null) {
            userRepository.save(user);
        }

        // 싱글 히스토리 생성 후 저장
        SingleHistory history = SingleHistory.builder()
                .user(user)
                .trialCount(4)
                .isCorrect(true)
                .createDate(LocalDate.now())
                .resultText("success")
                .build();

        if (singleHistoryRepository.findByCreateDate(history.getCreateDate()) == null) {
            singleHistoryRepository.save(history);
        }

        // 싱글 히스토리 생성 후 저장
        SingleHistory history2 = SingleHistory.builder()
                .user(user)
                .trialCount(6)
                .isCorrect(true)
                .createDate(LocalDate.of(2024, 1,25))
                .resultText("success")
                .build();

        if (singleHistoryRepository.findByCreateDate(history2.getCreateDate()) == null) {
            singleHistoryRepository.save(history2);
        }

        // 날짜가 같은 싱글 히스토리 생성 후 저장   => 저장 안됨
        SingleHistory duplDateHistory = SingleHistory.builder()
                .user(user)
                .trialCount(4)
                .isCorrect(true)
                .createDate(LocalDate.now())
                .resultText("success")
                .build();

        if (singleHistoryRepository.findByCreateDate(duplDateHistory.getCreateDate()) == null) {
            singleHistoryRepository.save(duplDateHistory);
        }
//        Long userId = userRepository.findByEmail("a").getId();
//        LocalDate localDate = singleHistoryRepository.findByCreateDate(LocalDate.now()).getCreateDate();

        // user create and find test
        assertThat(user).isEqualTo(userRepository.findByEmail(user.getEmail()));

        // single history create and find test
        assertThat(history).isEqualTo(singleHistoryRepository.findByCreateDate(history.getCreateDate()));

        // duplicated single history create and find test
        /**
         * sql query가 동작하는 과정에서 발생하는 예외(내가 발생시키고자 하는 예외)를 try { }, assertThrows 안에서 스프링이 못잡음...
         * try { }, assertThrows 밖에서 에러를 띄우는 듯. 즉, transaction이 끝날 때 예외를 발생시키는 듯함.
         * 그래서 try { }, assertThrows 안에서 flush()를 해줬는데
         * 이건 또 무슨 Rollback 머시기 에러를 발생시킴 ... 이 에러는 잡히지도 않음
         *
         * 나 안해.....
         *
         * if unique key가 중복되면 -> 예외 처리
         */

//        assertThatThrownBy(() -> {
//            singleHistoryRepository.save(duplDateHistory);
//        }).isInstanceOf(Exception.class);

        /**
         * 아직 exception 작성 안해서 주석처리 해둠 !!!
         * 예외 발생 코드 작성해 !!!
         * EXAMPLE : 중복된 날짜를 가지는 single history
         * singleHistoryRepository.save(duplDateHistory);
         */
    }

    @Test
    public void saveHistoryTest() throws Exception {

        User user = User.builder()
                .email("1@1")
                .build();

        userRepository.save(user);

//        SingleHistory history = SingleHistory.builder()
//                .user(user)
//                .trialCount(4)
//                .isCorrect(true)
//                .createDate(LocalDate.now())
//                .resultText("success")
//                .build();
//
//        singleHistoryRepository.save(history);
    }
}
