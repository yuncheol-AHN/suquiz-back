package com.example.entity.domain.singleplay.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SingleHistorySaveResponseDto {
    protected String email;
    protected int trialCount;
    protected boolean isCorrect;
    protected String resultText;
}
