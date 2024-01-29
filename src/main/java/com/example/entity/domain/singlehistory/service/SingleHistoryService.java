package com.example.entity.domain.singlehistory.service;

import com.example.entity.domain.singlehistory.dto.SingleHistoryDto;
import com.example.entity.domain.singlehistory.dto.SingleHistorySaveRequestDto;
import com.example.entity.domain.singlehistory.dto.SingleHistorySaveResponseDto;
import com.example.entity.global.dto.CommonResponse;

import java.util.List;

public interface SingleHistoryService {
    // 입력
    SingleHistoryDto.SaveResponse save(SingleHistoryDto.SaveRequest singleHistoryRequestDto);
    // 조회
    List<SingleHistorySaveResponseDto> findAll();
}
