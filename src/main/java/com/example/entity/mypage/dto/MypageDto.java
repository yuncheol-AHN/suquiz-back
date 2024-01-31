package com.example.entity.mypage.dto;

import lombok.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Map;

public class MypageDto {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UserResponse {
        /**
         * nickname, profile image, level, exp
         */
        String nickname;
        String profileImage;
        int level;
        int exp;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class NicknameModifyRequest {
        private String email;
        private String modifiedName;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class NicknameModifyResoponse {
        private String modifiedName;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class UserWordleResponse {

        private int allTrialCount;             // 전체 도전 횟수
        private Map<LocalDate, Integer> streak;     // 스트릭
        private int solveCount;                // 최근 연속 풀이 횟수
        private int correctCount;              // 최근 연속 정답
        private int maxCorrectCount;           // 연속 스트릭
        private int[] trialSpread;             // 도전 분포
        private int correctRate;               // 정답률
    }
}
