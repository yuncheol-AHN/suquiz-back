package com.example.entity.domain.singlehistory.servicelmpl;

import com.example.entity.domain.singlehistory.dto.SingleHistoryDto;
import com.example.entity.domain.singlehistory.dto.SingleHistorySaveResponseDto;
import com.example.entity.domain.singlehistory.entity.SingleHistory;
import com.example.entity.domain.singlehistory.service.SingleHistoryService;
import com.example.entity.repository.SingleHistoryRepository;
import com.example.entity.repository.UserRepository;
import com.example.entity.service.EntityAndDtoConversionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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

        SingleHistory singleHistory = entityAndDtoConversionService.singleHistorySaveDtoToEntity(singleHistorySaveRequestDto);
        return entityAndDtoConversionService.singleHistorySaveEntityToDto(singleHistoryRepository.save(singleHistory));
    }

    @Override
    public List<SingleHistorySaveResponseDto> findAll() {
        return null;

    }
}
