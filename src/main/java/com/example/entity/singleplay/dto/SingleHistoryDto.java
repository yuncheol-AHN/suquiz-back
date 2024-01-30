package com.example.entity.singleplay.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
    public static class FindResponse {
        protected int trialCount;
        protected boolean correct;
        protected String resultText;
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
         * 최근 최다 정답 -> 연속 스트릭 아님?
         * 도전 분포 -> 뭐임?
         */
        protected int allTrialCount;
        protected String streak;
        protected int maxStreakCount;
        protected int recentCount;
    }
}
