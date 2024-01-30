package com.example.entity.singleplay.servicelmpl;

import com.example.entity.user.User;
import com.example.entity.singleplay.dto.SingleHistoryDto;
import com.example.entity.singleplay.entity.SingleHistory;
import com.example.entity.singleplay.service.SingleHistoryService;
import com.example.entity.singleplay.repository.SingleHistoryRepository;
import com.example.entity.repository.UserRepository;
import com.example.entity.global.service.EntityAndDtoConversionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SingleHistoryServiceImpl implements SingleHistoryService {

    private final SingleHistoryRepository singleHistoryRepository;
    private final UserRepository userRepository;
    private final EntityAndDtoConversionService entityAndDtoConversionService;

    @Transactional
    @Override
    public SingleHistoryDto.SaveResponse save(SingleHistoryDto.SaveRequest singleHistorySaveRequestDto) {
        /**
         * single history request dto -> signle history entity
         * single history entity -> single history response dto
         *
         * required data when saving single history
         *  user_email, trial_count, is_correct, result_text
         *
         * return data that user_email, trial_count, is_correct, result_text
         */

        // update user info(recentCorrectCount; maxCorrectCount;)
        Optional<User> optionalUser = userRepository.findByEmail(singleHistorySaveRequestDto.getEmail());

        if (optionalUser.isPresent()) {
            User user = optionalUser.get();

            int recentCount = user.getRecentCorrectCount();
            int maxCount = user.getMaxCorrectCount();

            userRepository.save(user.toBuilder()
                    .recentCorrectCount(recentCount+1)
                    .maxCorrectCount(Math.max(recentCount+1, maxCount))
                    .build());

            if (singleHistoryRepository.findByUserAndCreateDate(user, LocalDate.now()) == null) {
                // save single history
                SingleHistory singleHistory = entityAndDtoConversionService.singleHistorySaveDtoToEntity(singleHistorySaveRequestDto);

                return entityAndDtoConversionService.singleHistorySaveEntityToDto(singleHistoryRepository.save(singleHistory));
            }
        }
        return null;
//        if (singleHistoryRepository.findByUserAndCreateDate(, LocalDate.now()) == null) {
//
//            // update user info(recentCorrectCount; maxCorrectCount;)
//            Optional<User> optionalUser = userRepository.findByEmail(singleHistorySaveRequestDto.getEmail());
//            if (optionalUser.isPresent()) {
//                User user = optionalUser.get();
//
//            }
//
//        } else {
//
//        }
    }

    @Override
    public boolean dailyIsSolved(String email) {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        return optionalUser.isPresent();
    }

    @Override
    public SingleHistoryDto.FindResponse dailyShare(String email) {

        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            SingleHistory singleHistory = singleHistoryRepository.findByUserAndCreateDate(user, LocalDate.now());

            if (singleHistory != null) {
                return SingleHistoryDto.FindResponse.builder()
                        .trialCount(singleHistory.getTrialCount())
                        .correct(singleHistory.isCorrect()      )
                        .resultText(singleHistory.getResultText())
                        .build();
            }
        }
        return null;
    }


    @Override
    public SingleHistoryDto.AllResultResponse singlePlayAllResult(String email) {
        /**
         * 전체 도전 횟수
         * 문제 스트릭
         * 연속 최대 풀이 횟수(연속 스트릭 일 수)
         * 최근 연속 정답
         * 최근 최다 정답 -> 연속 스트릭 아님?
         * 도전 분포 -> 뭐임?
         */

        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();

            return SingleHistoryDto.AllResultResponse.builder()
//                    .allTrialCount()
//                    .streak()
                    .maxStreakCount(user.getMaxCorrectCount())
                    .recentCount(user.getRecentCorrectCount())
                    .build();
        }
        return null;
    }
}
