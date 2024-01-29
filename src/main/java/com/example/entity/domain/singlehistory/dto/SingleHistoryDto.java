package com.example.entity.domain.singlehistory.dto;

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
        protected boolean isCorrect;
        protected String resultText;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SaveResponse {
        protected String email;
        protected int trialCount;
        protected boolean isCorrect;
        protected String resultText;
    }

    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class FindResponse {
        protected int trialCount;
        protected boolean isCorrect;
        protected String resultText;
    }
}
