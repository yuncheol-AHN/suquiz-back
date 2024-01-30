package com.example.entity.singleplay.service;

import com.example.entity.singleplay.dto.SingleHistoryDto;

public interface SingleHistoryService {
    // 입력
    SingleHistoryDto.SaveResponse save(SingleHistoryDto.SaveRequest singleHistoryRequestDto);
    // 데일리 조회
    boolean dailyIsSolved(String email);
    // SNS 오늘의 결과
    SingleHistoryDto.FindResponse dailyShare(String email);
    // 싱글 플레이 전체 결과 조회
    SingleHistoryDto.AllResultResponse singlePlayAllResult(String email);
}
