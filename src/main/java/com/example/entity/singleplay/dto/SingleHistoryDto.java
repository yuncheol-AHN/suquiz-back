package com.example.entity.singleplay.dto;

import com.example.entity.user.domain.User;
import com.fasterxml.jackson.core.JsonFactory;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class SingleHistoryDto {

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SaveRequest {
        protected String email;
        protected int trialCount;
        protected boolean correct;
        protected String resultText;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SaveResponse {
        protected String email;
        protected int trialCount;
        protected boolean correct;
        protected String resultText;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ShareResponse {
        private boolean correct;
        private int trialCount;
        private int correctCount;
        private String resultText;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AllResultResponse {
        /**
         * 전체 도전 횟수
         * 문제 스트릭
         * 연속 최대 풀이 횟수(연속 스트릭 일 수)
         * 최근 연속 정답
         * 연속 스트릭
         * 도전 분포 -> 뭐임?
         */
        private int allTrialCount;             // 전체 도전 횟수
        private Map<LocalDate, Integer> streak;     // 스트릭
        private int solveCount;                // 최근 연속 풀이 횟수
        private int correctCount;              // 최근 연속 정답
        private int maxCorrectCount;           // 연속 스트릭
        private int[] trialSpread;             // 도전 분포e
        private int correctRate;               // 정답률
    }
}
