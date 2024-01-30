package com.example.entity.global.service;

import com.example.entity.bookmark.domain.Bookmark;
import com.example.entity.bookmark.dto.BookmarkDTO;
import com.example.entity.repository.UserRepository;
import com.example.entity.user.User;
import com.example.entity.word.Subject;
import com.example.entity.word.Word;
import com.example.entity.education.dto.SubjectDTO;
import com.example.entity.education.dto.WordDTO;
import com.example.entity.education.repository.SubjectRepository;
import com.example.entity.education.repository.WordRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class EntityAndDtoConversionService {

    private final SubjectRepository subjectRepository;
    private final WordRepository wordRepository;
    private final UserRepository userRepository;

    // Subject Conversion
    public SubjectDTO.Response findSubjectEntityToDto(Subject subject) {
        return SubjectDTO.Response.builder()
                .subjectName(subject.getSubjectName())
                .wordList(mapWordEntitiesToDto(subject.getWordList()))
                .build();
    }
    private List<WordDTO.WordResponseDto> mapWordEntitiesToDto(List<Word> wordList) {
        return wordList.stream()
                .map(word -> WordDTO.WordResponseDto.builder().wordName(word.getWordName()).build())
                .collect(Collectors.toList());

    }

    // Word Conversion
    public WordDTO.WordResponseDto WordEntityToDto(Word entity) {
        return WordDTO.WordResponseDto.builder()
                .wordName(entity.getWordName())
                .category(entity.getCategory().name())
                .subjectName(entity.getSubject().getSubjectName())
                .videoUrl(entity.getVideoUrl())
                .build();
    }

    public Word WordDtoToEntity(WordDTO.WordRequestDto requestDto) {
        Subject subject = new Subject(requestDto.getSubjectName());

        return Word.builder()
                .category(requestDto.getCategory())
                .wordName(requestDto.getWordName())
                .subject(subject)
                .build();
    }

    // Bookmark Conversion
    public Bookmark addBookmarkDtoToEntity(BookmarkDTO.addRequest addRequest) {

        Optional<User> findUser = userRepository.findByEmail(addRequest.getUserEmail());
        Word findWord = wordRepository.findByWordName(addRequest.getWordName());

        return findUser.map(user -> Bookmark.builder()
                .user(user)
                .word(findWord)
                .build()).orElse(null);
    }

    public BookmarkDTO.checkResponse checkBookmarkEntityToDto(List<Bookmark> bookmarks) {

        List<WordDTO.WordResponseDto> wordDtoList = converWordListToDtoList(bookmarks);
        return BookmarkDTO.checkResponse.builder()
                .wordList(wordDtoList)
                .build();
    }

    private List<WordDTO.WordResponseDto> converWordListToDtoList(List<Bookmark> bookmarks) {
        return bookmarks.stream()
                .map(bookmark -> WordDTO.WordResponseDto.builder()
                        .wordName(bookmark.getWord().getWordName())
                        .subjectName(bookmark.getWord().getSubject().getSubjectName())
                        .category(bookmark.getWord().getCategory().name())
                        .videoUrl(bookmark.getWord().getVideoUrl())
                        .build())
                .collect(Collectors.toList());
    }

}
