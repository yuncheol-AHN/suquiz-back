package com.example.entity.repository;

import com.example.entity.multiplay.domain.Quizroom;
import com.example.entity.multiplay.repository.QuizroomRepository;
import com.example.entity.user.repository.UserRepository;
import com.example.entity.word.domain.Category;
import com.example.entity.word.domain.Subject;
import com.example.entity.word.domain.Word;
import com.example.entity.word.repository.SubjectRepository;
import com.example.entity.word.repository.WordRepository;
import com.example.entity.word.repository.WordRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@Transactional
@Rollback(value = false)
class QuizroomRepositoryTest {
    @Autowired
    UserRepository userRepository;
//    @Autowired
//    QuizroomWordRepository quizroomWordRepository;
    @Autowired
    QuizroomRepository quizroomRepository;
    @Autowired
    WordRepository wordRepository;
    @Autowired
    SubjectRepository subjectRepository;

    //
    @Test
    public void 퀴즈테스트() throws Exception {
//        // given
//        Quizroom quizRoom = Quizroom.builder()
//                .inviteCode("1234567")
//                .build();
//        /**
//         * quizRoom 생성
//         * -> word 테이블에서 word 랜덤으로 조회
//         * -> quizRoom과 word로 구성된 quizRoomWord생성
//         * -> quizRoom에 quizRoomWord삽입
//         * -> 나중에 quizRoom.quizRoomWord.word로 quizRoom에 있는 단어찾기
//         *
//         */
//
//        Subject fruit = Subject.builder()
//                .subjectName("fruit").build();
//
//        Word wordA = Word.builder()
//                .category(Category.WORD)
//                .subject(fruit)
//                .wordName("사과")
//                .videoUrl("1A")
//                .build();
//
//        Word wordB = Word.builder()
//                .category(Category.WORD)
//                .subject(fruit)
//                .wordName("바나나")
//                .videoUrl("1A")
//                .build();
//
//        QuizroomWord quizroomWord = QuizroomWord.builder()
//                .quizroom(quizRoom)
//                .word(wordA)
//                .build();
//
//        // when
//        subjectRepository.save(fruit);
//        quizroomRepository.save(quizRoom);
//        wordRepository.save(wordA);
//        wordRepository.save(wordB);
//        QuizroomWord saveQuizroom = quizroomWordRepository.save(quizroomWord);
//        List<QuizroomWord> quizroomWordList = saveQuizroom.getQuizroom().getQuizroomWordList();
//        System.out.println("quizroomWordList = " + quizroomWordList);
//        // then
//        for (QuizroomWord word : quizroomWordList) {
//            System.out.println("word = " + word);
//        }



    }

    /**
     * required : create, delete, ... play?! end? playing?
     * play : 질문리스트 줘야함
     * playing : 정답을 전달받아서 저장해야되나?
     * end : 최종 결과 줘야해?
     */
}