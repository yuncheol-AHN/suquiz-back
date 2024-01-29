//package com.example.entity.dto.subject;
//
//import com.example.entity.domain.Subject;
//import com.example.entity.domain.Word;
//import com.example.entity.dto.word.WordResponseDto;
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Data
//@Builder
//@NoArgsConstructor
//@AllArgsConstructor
//public class SubjectResponseDto {
//    private String subjectName;
//    private List<WordResponseDto> wordList;
//
//    public static SubjectResponseDto entityToDto(Subject subject) {
//        return SubjectResponseDto.builder()
//                .subjectName(subject.getSubjectName())
//                .wordList(mapWordEntitiesToDto(subject.getWordList()))
//                .build();
//    }
//
//    private static List<WordResponseDto> mapWordEntitiesToDto(List<Word> wordList) {
//        return wordList.stream()
//                .map(word -> WordResponseDto.builder().wordName(word.getWordName()).build())
//                .collect(Collectors.toList());
//    }
//}
