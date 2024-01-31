package com.example.entity.singleplay.service;

import com.example.entity.singleplay.dto.QuestDto;
import com.example.entity.singleplay.dto.SingleHistoryDto;
import org.springframework.scheduling.annotation.Scheduled;

public interface SingleHistoryService {
    // 데일리 문제 생성
    @Scheduled
    void createDaily();
  // 데일리 조회
    boolean dailyIsSolved(String email);
    // 데일리 문제
    QuestDto.DailyResponse dailyQuest();
    // 데일리 추가 문제
    QuestDto.AdditionalResponse additionalQuest();
    // 입력
    SingleHistoryDto.SaveResponse end(SingleHistoryDto.SaveRequest singleHistoryRequestDto);
    // SNS 오늘의 결과
    SingleHistoryDto.ShareResponse dailyShare(String email);
    // 싱글 플레이 전체 결과 조회
    SingleHistoryDto.AllResultResponse singlePlayAllResult(String email);
}
