package com.example.entity.domain.singlehistory.dto;

import com.example.entity.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

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
