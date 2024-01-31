package com.example.entity.singleplay.servicelmpl;

import com.example.entity.singleplay.dto.QuestDto;
import com.example.entity.user.domain.User;
import com.example.entity.singleplay.dto.SingleHistoryDto;
import com.example.entity.singleplay.domain.SingleHistory;
import com.example.entity.singleplay.service.SingleHistoryService;
import com.example.entity.singleplay.repository.SingleHistoryRepository;
import com.example.entity.user.repository.UserRepository;
import com.example.entity.global.service.EntityAndDtoConversionService;
import com.example.entity.word.domain.DailyWord;
import com.example.entity.word.domain.Word;
import com.example.entity.word.repository.WordRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
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

    private static QuestDto.DailyResponse dailyResponse;
    @Override
    @Scheduled(cron = "*/10 * * * * *")
    public void createDaily() {
        System.out.println("create daily quest");
        List<Word> words = wordRepository.findAll();

        // 랜덤으로 하나 선택
        if (!words.isEmpty()) {
            Random random = new Random();
            int index = random.nextInt(words.size());
            Word word = words.get(index);

            dailyResponse = QuestDto.DailyResponse.builder()
                    .category(word.getCategory())
                    .subject(word.getSubject().getSubjectName())
                    .wordName(word.getWordName())
                    .videoUrl(word.getVideoUrl())
                    .build();
        }
    }


    @Override
    public boolean dailyIsSolved(String email) {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            return singleHistoryRepository.findByUserAndCreateDate(user, LocalDate.now()) != null;
        }
        return false;
    }

    @Override
    public QuestDto.DailyResponse dailyQuest() {
        return dailyResponse;
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

    @Transactional
    @Override
    public SingleHistoryDto.SaveResponse end(SingleHistoryDto.SaveRequest singleHistorySaveRequestDto) {
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

            int solveCount = user.getSolveCount();
            int correctCount = user.getCorrectCount();
            int maxCount = user.getMaxCorrectCount();
            boolean isCorrect = singleHistorySaveRequestDto.isCorrect();
            /**
             *         “solveCount” : int,
             *         “correctCount” : int,
             *         “maxCorrectCount” : int
             */
            if (singleHistoryRepository.findByUserAndCreateDate(user, LocalDate.now()) == null) {
                userRepository.save(user.toBuilder()
                        .solveCount(solveCount + 1)
                        .correctCount(isCorrect ? correctCount + 1 : 0)
                        .maxCorrectCount(Math.max(correctCount, maxCount))
                        .build());

                // save single history
                SingleHistory singleHistory = entityAndDtoConversionService.singleHistorySaveDtoToEntity(singleHistorySaveRequestDto);
                return entityAndDtoConversionService.singleHistorySaveEntityToDto(singleHistoryRepository.save(singleHistory));
//                singleHistoryRepository.save(SingleHistory.builder()
//                        .createDate(LocalDate.now())
//                        .user(user)
//                        .trialCount(singleHistorySaveRequestDto.getTrialCount())
//                        .resultText(singleHistorySaveRequestDto.getResultText())
//                        .build());
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
    public SingleHistoryDto.ShareResponse dailyShare(String email) {

        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            SingleHistory singleHistory = singleHistoryRepository.findByUserAndCreateDate(user, LocalDate.now());

            if (singleHistory != null) {
                return SingleHistoryDto.ShareResponse.builder()
                        .correct(singleHistory.isCorrect())
                        .trialCount(singleHistory.getTrialCount())
                        .correctCount(user.getCorrectCount())
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
            float okCount = 0;
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
                    okCount += 1;
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
                    .correctRate((int) (okCount/allTrialCount*100))
                    .build();
        }
        return null;
    }
}
