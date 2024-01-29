package com.example.entity.domain.singlehistory.controller;

import com.example.entity.domain.singlehistory.dto.SingleHistoryDto;
import com.example.entity.domain.singlehistory.service.SingleHistoryService;
import com.example.entity.global.dto.CommonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/singlehistory")
@RequiredArgsConstructor
public class SingleHistoryController {
    private final SingleHistoryService singleHistoryService;

    @GetMapping("/save")
    public ResponseEntity<CommonResponse> save(@RequestBody SingleHistoryDto.SaveRequest saveRequest) {

        SingleHistoryDto.SaveResponse save = singleHistoryService.save(saveRequest);
        System.out.println(save);
        return new ResponseEntity<>(CommonResponse.builder()
                .status(HttpStatus.OK.value())
                .message("save daily single history")
                .list(save)
                .build(), HttpStatus.OK);
    }
}
