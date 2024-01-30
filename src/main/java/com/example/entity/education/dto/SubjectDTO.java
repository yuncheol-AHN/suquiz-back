package com.example.entity.education.dto;

import com.example.entity.education.dto.WordDTO;
import lombok.*;

import java.util.List;

public class SubjectDTO {

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Request {
        private String subjectName;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AllSubject {
        private String subjectName;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Response {
        private String subjectName;
        private List<WordDTO.WordResponseDto> wordList;

//        public static SubjectResponseDto entityToDto(Subject subject) {
//            return SubjectResponseDto.builder()
//                    .subjectName(subject.getSubjectName())
//                    .wordList(mapWordEntitiesToDto(subject.getWordList()))
//                    .build();
//        }
//
//        private static List<WordResponseDto> mapWordEntitiesToDto(List<Word> wordList) {
//            return wordList.stream()
//                    .map(word -> WordResponseDto.builder().wordName(word.getWordName()).build())
//                    .collect(Collectors.toList());
//        }
    }
}
