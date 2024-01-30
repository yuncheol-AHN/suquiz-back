package com.example.entity.multiplay.dto;

import com.example.entity.multiplay.domain.Quizroom;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


public class QuizroomWordDto {
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    static class request {
        Quizroom quizroom;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    static class response {
        QuizroomDto quizroom;

    }
}
