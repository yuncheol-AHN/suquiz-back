package com.example.entity.bookmark.serviceImpl;

import com.example.entity.bookmark.domain.Bookmark;
import com.example.entity.bookmark.dto.BookmarkDTO;
import com.example.entity.bookmark.repository.BookmarkRepository;
import com.example.entity.bookmark.service.BookmarkService;
import com.example.entity.education.repository.WordRepository;
import com.example.entity.global.service.EntityAndDtoConversionService;
import com.example.entity.repository.UserRepository;
import com.example.entity.user.User;
import com.example.entity.word.Word;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BookmarkServiceImpl implements BookmarkService {
    private final WordRepository wordRepository;
    private final UserRepository userRepository;
    private final BookmarkRepository bookmarkRepository;
    private final EntityAndDtoConversionService conversionService;
    @Override
    public BookmarkDTO.checkResponse findAllByUser(String userEmail) {
        Optional<User> findUser = userRepository.findByEmail(userEmail);
        if(findUser.isPresent()) {
            List<Bookmark> list = bookmarkRepository.findAllByUser(findUser.get());
            BookmarkDTO.checkResponse response = conversionService.checkBookmarkEntityToDto(list);
            return response;
        } else {
            // User가 없는 경우에 대한 처리
            return BookmarkDTO.checkResponse.builder()
                    .wordList(Collections.emptyList())  // 빈 리스트 또는 null로 설정
                    .build();
        }

    }

    @Override
    public void addWordsByUser(String email, String wordName) {
        Optional<User> findEmail = userRepository.findByEmail(email);
        Word findWord = wordRepository.findByWordName(wordName);
        Bookmark bookmark = Bookmark.builder()
                .word(findWord)
                .user(findEmail.get())
                .build();
        bookmarkRepository.save(bookmark);
    }

    @Override
    public void deleteWordsByUser(String email, String wordName) {
        Optional<User> findEmail = userRepository.findByEmail(email);
        Word findWord = wordRepository.findByWordName(wordName);
        Bookmark allByUserAndWord = bookmarkRepository.findAllByUserAndWord(findEmail.get(), findWord);

        bookmarkRepository.delete(allByUserAndWord);
    }
}
