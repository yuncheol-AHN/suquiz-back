package com.example.entity.singleplay.controller;

import com.example.entity.singleplay.dto.QuestDto;
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

    // 오늘의 문제풀이 여부
    @GetMapping("/solved/{userId}")
    public ResponseEntity<CommonResponse<Boolean>> isSolved(@PathVariable(value = "userId") String email) {

        return new ResponseEntity<>(CommonResponse.<Boolean>builder()
                .status(HttpStatus.OK.value())
                .message("success")
                .data(singleHistoryService.dailyIsSolved(email))
                .build(), HttpStatus.OK);
    }

    // 데일리 문제
    @GetMapping("/daily")
    public ResponseEntity<CommonResponse<QuestDto.DailyResponse>> dailyQuest() {
        /**
         * input : null
         * output : WordDailyResponseDto
         *
         * word table에 접근해서 랜덤으로 하나 뽑아오기. 해시값으로 오늘 날짜 주기.
         */
        return new ResponseEntity<>(CommonResponse.<QuestDto.DailyResponse>builder()
                .status(HttpStatus.OK.value())
                .message("daily wordle")
                .data(singleHistoryService.dailyQuest())
                .build(), HttpStatus.OK);
    }

    // 데일리 추가 문제
    @GetMapping("/additional")
    public ResponseEntity<CommonResponse<QuestDto.AdditionalResponse>> additionalQuest() {

        return new ResponseEntity<>(CommonResponse.<QuestDto.AdditionalResponse>builder()
                .status(HttpStatus.OK.value())
                .message("share daily single history")
                .data(singleHistoryService.additionalQuest())
                .build(), HttpStatus.OK);
    }

    // 종료
    @PostMapping("/end")
    public ResponseEntity<CommonResponse<SingleHistoryDto.SaveResponse>> save(@RequestBody SingleHistoryDto.SaveRequest saveRequest) {
        System.out.println(saveRequest.isCorrect());
        SingleHistoryDto.SaveResponse save = singleHistoryService.end(saveRequest);
        return new ResponseEntity<>(CommonResponse.<SingleHistoryDto.SaveResponse>builder()
                .status(HttpStatus.OK.value())
                .message("save daily single history")
                .data(save)
                .build(), HttpStatus.OK);
    }

    // SNS 공유할 오늘의 결과 요청 ... 싱글플레이 종료 시 리턴해주는 DTO랑 다른게 뭐지...?
    @GetMapping("/share/{userId}")
    public ResponseEntity<CommonResponse<SingleHistoryDto.ShareResponse>> dailyShare(@PathVariable(value = "userId") String email) {

        return new ResponseEntity<>(CommonResponse.<SingleHistoryDto.ShareResponse>builder()
                .status(HttpStatus.OK.value())
                .message("share daily single history")
                .data(singleHistoryService.dailyShare(email))
                .build(), HttpStatus.OK);
    }

    // 싱글 플레이 결과 조회
    @GetMapping("/result/{userId}")
    public ResponseEntity<CommonResponse<SingleHistoryDto.AllResultResponse>> dailyResult(@PathVariable(value = "userId") String email) {
        /**
         * 전체 도전 횟수
         * 문제 스트릭
         * 연속 최대 풀이 횟수(연속 스트릭 일 수)
         * 최근 연속 정답
         * 최근 최다 정답
         * 도전 분포
         */

        return new ResponseEntity<>(CommonResponse.<SingleHistoryDto.AllResultResponse>builder()
                .status(HttpStatus.OK.value())
                .message("share daily single history")
                .data(singleHistoryService.singlePlayAllResult(email))
                .build(), HttpStatus.OK);
    }
}
