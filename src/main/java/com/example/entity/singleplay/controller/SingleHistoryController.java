package com.example.entity.singleplay.controller;

import com.example.entity.singleplay.dto.SingleHistoryDto;
import com.example.entity.singleplay.service.SingleHistoryService;
import com.example.entity.global.dto.CommonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/wordle")
@RequiredArgsConstructor
public class SingleHistoryController {

    private final SingleHistoryService singleHistoryService;
//    private final Word

    @GetMapping("/isSolved/{userId}")
    public ResponseEntity<CommonResponse> isSolved(@PathVariable(value = "userId") String email) {

        System.out.println(email);
        return new ResponseEntity<>(CommonResponse.builder()
                .status(HttpStatus.OK.value())
                .message("isSolved daily wordle")
                .data(singleHistoryService.dailyIsSolved(email))
                .build(), HttpStatus.OK);
    }

    @GetMapping("/dailyQuest")
    public ResponseEntity<CommonResponse> dailyQuest() {
        /**
         * input : null
         * output : WordDailyResponseDto
         *
         * word table에 접근해서 랜덤으로 하나 뽑아오기. 해시값으로 오늘 날짜 주기.
         */
        return new ResponseEntity<>(CommonResponse.builder()
                .status(HttpStatus.OK.value())
                .message("daily wordle")
//                .data(singleHistoryService.dailyQuest())
                .build(), HttpStatus.OK);
    }

    @GetMapping("/additional")
    public ResponseEntity<CommonResponse> additionalQuest() {

        return new ResponseEntity<>(CommonResponse.builder()
                .status(HttpStatus.OK.value())
                .message("share daily single history")
                .data(singleHistoryService.additionalQuest())
                .build(), HttpStatus.OK);
    }

    @PostMapping("/end")
    public ResponseEntity<CommonResponse> save(@RequestBody SingleHistoryDto.SaveRequest saveRequest) {
        System.out.println(saveRequest.isCorrect());
        SingleHistoryDto.SaveResponse save = singleHistoryService.save(saveRequest);
        return new ResponseEntity<>(CommonResponse.builder()
                .status(HttpStatus.OK.value())
                .message("save daily single history")
                .data(save)
                .build(), HttpStatus.OK);
    }

    // SNS 공유할 오늘의 결과 요청 ... 싱글플레이 종료 시 리턴해주는 DTO랑 다른게 뭐지...?
    @GetMapping("/share")
    public ResponseEntity<CommonResponse> dailyShare(@RequestParam(value = "userId") String email) {

        return new ResponseEntity<>(CommonResponse.builder()
                .status(HttpStatus.OK.value())
                .message("share daily single history")
                .data(singleHistoryService.dailyShare(email))
                .build(), HttpStatus.OK);
    }

    // 싱글 플레이 결과 조회
    @GetMapping("/result")
    public ResponseEntity<CommonResponse> dailyResult(@RequestParam(value = "userId") String email) {
        /**
         * 전체 도전 횟수
         * 문제 스트릭
         * 연속 최대 풀이 횟수(연속 스트릭 일 수)
         * 최근 연속 정답
         * 최근 최다 정답
         * 도전 분포
         */

        return new ResponseEntity<>(CommonResponse.builder()
                .status(HttpStatus.OK.value())
                .message("share daily single history")
                .data(singleHistoryService.singlePlayAllResult(email))
                .build(), HttpStatus.OK);
    }
}
