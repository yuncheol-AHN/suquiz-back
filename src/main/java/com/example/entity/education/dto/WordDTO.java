package com.example.entity.education.dto;

import com.example.entity.word.domain.Category;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

public class WordDTO {
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class WordRequestDto {
        private String wordName;
        private String SubjectName;
        private Category category;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class WordResponseDto {
        private String subjectName;
        private String category;
        private String videoUrl;
        private String wordName;
    }
}
