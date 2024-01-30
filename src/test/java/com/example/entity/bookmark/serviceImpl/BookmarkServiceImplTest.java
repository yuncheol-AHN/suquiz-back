package com.example.entity.bookmark.serviceImpl;

import com.example.entity.bookmark.domain.Bookmark;
import com.example.entity.bookmark.dto.BookmarkDTO;
import com.example.entity.bookmark.repository.BookmarkRepository;
import com.example.entity.bookmark.service.BookmarkService;
import com.example.entity.education.dto.WordDTO;
import com.example.entity.education.repository.SubjectRepository;
import com.example.entity.education.repository.WordRepository;
import com.example.entity.repository.UserRepository;
import com.example.entity.user.OAuthProvider;
import com.example.entity.user.User;
import com.example.entity.word.Category;
import com.example.entity.word.Subject;
import com.example.entity.word.Word;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Rollback(value = false)
class BookmarkServiceImplTest {
    @Autowired
    UserRepository userRepository;
    @Autowired
    WordRepository wordRepository;
    @Autowired
    SubjectRepository subjectRepository;
    @Autowired
    BookmarkRepository bookmarkRepository;
    @Autowired
    BookmarkService bookmarkService;
    @PersistenceContext
    EntityManager em;

    @Transactional
    @Test
    public void 유저별단어조회() throws Exception {
        // given
        User userA = User.builder()
                .maxCorrectCount(0)
                .recentCorrectCount(0)
                .isPlaying(false)
                .oAuthProvider(OAuthProvider.KAKAO)
                .nickname("테스트1")
                .imageUrl("1a")
                .email("denny10002@naver.com")
                .level(10)
                .exp(100)
                .build();

        Subject subjectA = Subject.builder()
                .subjectName("과일")
                .build();

        Word wordA = Word.builder()
                .category(Category.WORD)
                .videoUrl("AAAA")
                .wordName("오렌지")
                .subject(subjectA)
                .build();

        Word wordB = Word.builder()
                .category(Category.WORD)
                .videoUrl("BBB")
                .wordName("딸기")
                .subject(subjectA)
                .build();

        Bookmark bookmarkA = Bookmark.builder()
                .word(wordA)
                .user(userA)
                .build();

        Bookmark bookmarkB = Bookmark.builder()
                .word(wordB)
                .user(userA)
                .build();
        // when
        userRepository.save(userA);
        subjectRepository.save(subjectA);
        wordRepository.save(wordA);
        wordRepository.save(wordB);
        bookmarkRepository.save(bookmarkA);
        bookmarkRepository.save(bookmarkB);

        em.flush();
        em.clear();
        bookmarkRepository.delete(bookmarkA);
        bookmarkRepository.delete(bookmarkB);
        // then

//        Optional<User> findUser = userRepository.findByEmail(userA.getEmail());
//        System.out.println("findUser = " + findUser.get().getEmail());
//        BookmarkDTO.checkResponse allByUser = bookmarkService.findAllByUser(userA.getEmail());
//        for (WordDTO.WordResponseDto wordResponseDto : allByUser.getWordList()) {
//            System.out.println("wordResponseDto = " + wordResponseDto);
        }

    }
