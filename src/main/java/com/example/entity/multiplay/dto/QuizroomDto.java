package com.example.entity.multiplay.dto;

import com.example.entity.multiplay.domain.QuizroomWord;
import com.example.entity.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

public class QuizroomDto {
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    static class Response {
        List<User> userList;
        List<QuizroomWord> quizroomWordList;
        boolean isPlaying;
        String inviteCode;
    }
}
