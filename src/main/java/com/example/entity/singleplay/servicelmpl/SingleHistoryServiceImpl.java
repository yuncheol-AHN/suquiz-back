package com.example.entity.singleplay.servicelmpl;

import com.example.entity.singleplay.dto.QuestDto;
import com.example.entity.user.domain.User;
import com.example.entity.singleplay.dto.SingleHistoryDto;
import com.example.entity.singleplay.domain.SingleHistory;
import com.example.entity.singleplay.service.SingleHistoryService;
import com.example.entity.singleplay.repository.SingleHistoryRepository;
import com.example.entity.user.repository.UserRepository;
import com.example.entity.global.service.EntityAndDtoConversionService;
import com.example.entity.word.domain.Word;
import com.example.entity.word.repository.WordRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.*;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SingleHistoryServiceImpl implements SingleHistoryService {

    private final SingleHistoryRepository singleHistoryRepository;
    private final UserRepository userRepository;
    private final WordRepository wordRepository;
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

            int recentCount = user.getSolveCount();
            int maxCount = user.getMaxCorrectCount();

            if (singleHistoryRepository.findByUserAndCreateDate(user, LocalDate.now()) == null) {
                userRepository.save(user.toBuilder()
                        .solveCount(recentCount+1)
                        .maxCorrectCount(Math.max(recentCount+1, maxCount))
                        .build());

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
    public QuestDto.DailyResponse dailyQuest() {
        return null;
    }

    @Override
    public QuestDto.AdditionalResponse additionalQuest() {

        List<Word> words = wordRepository.findAll();

        // 랜덤 인덱스 생성
        Random random = new Random();
        int randomIndex = random.nextInt(words.size());
        Word word = words.get(randomIndex);

        return QuestDto.AdditionalResponse.builder()
                .subject(word.getSubject().getSubjectName())
                .category(word.getCategory())
                .wordName(word.getWordName())
                .videoUrl(word.getVideoUrl())
                .build();
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
         *
         *         private int allTrialCount;                      // 전체 도전 횟수
         *         private List<LocalDate> localDate;              // 쉽게쉽게 가자
         *         private List<Integer> icCorrect;                // 쉽게쉽게 가자
         *         private int recentContCount;                    // 최근 연속 풀이 횟수
         *         private int recentAnsCount;                     // 최근 연속 정답
         *         private int continuousStreak;                   // 연속 스트릭
         *         private int[] trialSpread;                      // 도전 분포e
         */

        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            List<SingleHistory> histories = singleHistoryRepository.findAllByUser(user);

            int allTrialCount = histories.size();
            Map<LocalDate, Integer> streak = new TreeMap<>();
            int[] trialSpread = new int[5];

            // 현재 날짜
            LocalDate currentDate = LocalDate.now();
            // 1년 전 날짜 계산
            LocalDate oneYearAgo = currentDate.minusYears(1);
            // 1년 전부터 오늘까지 풀지 않은 값 저장
            for (LocalDate date = oneYearAgo; date.isBefore(currentDate.plusDays(1)); date = date.plusDays(1)) {
                streak.put(date, 0);
            }

            for (SingleHistory singleHistory: histories) {
                LocalDate getDate = singleHistory.getCreateDate();
                if (singleHistory.isCorrect()) {
                    streak.put(getDate, 1);
                } else {
                    streak.put(getDate, -1);
                }

                trialSpread[singleHistory.getTrialCount()] += 1;
            }

            return SingleHistoryDto.AllResultResponse.builder()
                    .allTrialCount(allTrialCount)
                    .streak(streak)
                    .solveCount(user.getSolveCount())
                    .correctCount(user.getCorrectCount())
                    .maxCorrectCount(user.getMaxCorrectCount())
                    .trialSpread(trialSpread)
                    .build();
        }
        return null;
    }
}
