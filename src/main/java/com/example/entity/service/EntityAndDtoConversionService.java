package com.example.entity.service;

import com.example.entity.domain.User;
import com.example.entity.domain.singleplay.dto.SingleHistoryDto;
import com.example.entity.domain.singleplay.entity.SingleHistory;
import com.example.entity.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class EntityAndDtoConversionService {
    private final UserRepository userRepository;

    // dto -> entity
    public SingleHistory singleHistorySaveDtoToEntity(SingleHistoryDto.SaveRequest singleHistorySaveRequestDto) {

        Optional<User> user = userRepository.findByEmail(singleHistorySaveRequestDto.getEmail());

        if (user.isPresent()) {
            return SingleHistory.builder()
                    .user(user.get())
                    .trialCount(singleHistorySaveRequestDto.getTrialCount())
                    .isCorrect(singleHistorySaveRequestDto.isCorrect())
                    .resultText(singleHistorySaveRequestDto.getResultText())
                    .createDate(LocalDate.now())
                    .build();
        } else {
            return new SingleHistory();
        }
    }

    // entity -> dto
    public SingleHistoryDto.SaveResponse singleHistorySaveEntityToDto(SingleHistory singleHistory) {
        return SingleHistoryDto.SaveResponse.builder()
                .email(singleHistory.getUser().getEmail())
                .trialCount(singleHistory.getTrialCount())
                .correct(singleHistory.isCorrect())
                .resultText(singleHistory.getResultText())
                .build();
    }
}
