package com.example.entity.dto.singlehistory;

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
public class SingleHistoryRequestDto {

    private User user;
    private LocalDate createDate;
    private int trialCount;
    private boolean isCorrect;
    private String resultText;
}
