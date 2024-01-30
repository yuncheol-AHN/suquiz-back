package com.example.entity.repository;

import com.example.entity.bookmark.domain.Bookmark;
import com.example.entity.bookmark.repository.BookmarkRepository;
import com.example.entity.education.repository.SubjectRepository;
import com.example.entity.education.repository.WordRepository;
import com.example.entity.user.User;
import com.example.entity.word.Category;
import com.example.entity.word.Subject;
import com.example.entity.word.Word;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
@Rollback(false)
class BookmarkRepositoryTest {

    @Autowired UserRepository userRepository;
    @Autowired
    SubjectRepository subjectRepository;
    @Autowired
    WordRepository wordRepository;
    @Autowired
    BookmarkRepository bookmarkRepository;

    /**
     * required : create, find ...
     */

    @Test
    public void createBookmark() throws Exception {
        /**
         * 0. 단어를 북마크에 저장할까 유저에 저장할까 -> 단어를 북마크에 담아 유저가 가지고 있는 북마크 리스트에 저장하자.
         * 1. make word list
         * 2. add to bookmark(user id, word id)
         * 3. select * from bookmark where user id = 'A'
         */

        /**
         * 두번 째 방법
         * 1. create word, user : 단어, 유저를 만들고
         * 2. create bookmark : 이를 매개변수로 북마크를 만든다
         * 3. save word, user, bookmark : 그리고 단어, 유저, 북마크를 DB에 저장
         */

        /**
         * Problem.
         * 중복 저장되는 문제 ... 어디서 처리해줄까
         */
        // given
        User chulsu = User.builder()
                .email("chulsu")
                .build();

        User younghee = User.builder()
                .email("younghee")
                .build();

        userRepository.save(chulsu);
        userRepository.save(younghee);

        Subject fruit = Subject.builder()
                .subjectName("fruit")
                .build();

        Word apple = Word.builder()
                .category(Category.WORD)
                .subject(fruit)
                .wordName("apple")
                .build();

        Word banana = Word.builder()
                .category(Category.WORD)
                .subject(fruit)
                .wordName("banana")
                .build();

        subjectRepository.save(fruit);
        wordRepository.save(apple);
        wordRepository.save(banana);

        Bookmark bookmark_apple = Bookmark.builder()
                .user(chulsu)
                .word(apple)
                .build();

        Bookmark bookmark_banana = Bookmark.builder()
                .user(younghee)
                .word(banana)
                .build();

        Bookmark bookmark_chulsu_banana = Bookmark.builder()
                .user(chulsu)
                .word(banana)
                .build();

        bookmarkRepository.save(bookmark_apple);
        bookmarkRepository.save(bookmark_banana);
        bookmarkRepository.save(bookmark_chulsu_banana);

        // then
//        assertThat(bookmark_apple).isEqualTo(bookmarkRepository.findByUserAndWord(chulsu, apple));
//        assertThat(bookmark_banana).isEqualTo(bookmarkRepository.findByUserAndWord(younghee, banana));

    }

//    @Test
//    public void findBookmark() throws Exception {
//
//        /**
//         * 1. find user by social id
//         * 2. find all bookmarks
//         * 3. select * from bookmarks join user.id and bookmark.id
//         */
//
//        User chulsu = userRepository.findByEmail("chulsu");
//        List<Bookmark> bookmarks = bookmarkRepository.findAllByUser(chulsu);
//
//        System.out.println("--------------------절취선-------------------");
//        for (Bookmark bookmark: bookmarks) {
//            System.out.println(bookmark.getWord().getWordName());
//        }
//
//        // when
//
//        // then
//    }
}