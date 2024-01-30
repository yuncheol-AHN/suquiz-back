package com.example.entity.multiplay.controller;

import com.example.entity.global.dto.CommonResponse;
import com.example.entity.multiplay.service.QuizroomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/quizrooms")
public class QuizroomController {
    private final QuizroomService quizroomService;

    // 퀴즈 방 생성
    @PostMapping("/{userId}")
    public ResponseEntity<CommonResponse> makeQuizroom (@PathVariable Long userId) {

        int result = quizroomService.makeQuizroom(userId);
        if(result==1) {
            return new ResponseEntity<>(CommonResponse.builder()
                    .status(HttpStatus.OK.value())
                    .message("퀴즈룸 생성 완료")
                    .data("")
                    .build(), HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(CommonResponse.builder()
                    .status(HttpStatus.BAD_REQUEST.value())
                    .message("퀴즈룸 생성 실패")
                    .data("")
                    .build(), HttpStatus.BAD_REQUEST);
        }
    }

    // 퀴즈 룸 입장 가능 여부 조회
    @GetMapping("/isFull/{inviteCode}")
    public ResponseEntity<CommonResponse> checkIsRoomJoinable(@PathVariable String inviteCode) {
        boolean isJoinable = quizroomService.checkIsRoomJoinable(inviteCode);

        if(isJoinable) {
            return new ResponseEntity<>(CommonResponse.builder()
                    .status(HttpStatus.OK.value())
                    .message("퀴즈룸 입장 가능")
                    .data("true")
                    .build(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(CommonResponse.builder()
                    .status(HttpStatus.OK.value())
                    .message("퀴즈룸 입장 불가")
                    .data("false")
                    .build(), HttpStatus.OK);
        }

    }


    // 퀴즈 룸 입장(퀵)
    @PostMapping("/enter/quick/{userId}")
    public ResponseEntity<CommonResponse> joinQuickQuizroom(@PathVariable Long userId) {

        return new ResponseEntity<>(CommonResponse.builder()
                .status(HttpStatus.OK.value())
                .message("퀴즈룸 퀵매칭 입장 완료")
                .data("")
                .build(), HttpStatus.OK);
    }


    // 퀴즈 룸 입장(초대코드)
    @PostMapping("/enter/{userId}")
    public ResponseEntity<CommonResponse> joinInviteQuizroom(@PathVariable Long userId, @RequestParam String inviteCode) {

        return new ResponseEntity<>(CommonResponse.builder()
                .status(HttpStatus.OK.value())
                .message("퀴즈룸 입장 완료")
                .data("")
                .build(), HttpStatus.OK);
    }

    // 퀴즈 룸 퇴장
    @DeleteMapping("/exit/{userId}")
    public ResponseEntity<CommonResponse> exitQuizroom(@PathVariable Long userId) {

        return new ResponseEntity<>(CommonResponse.builder()
                .status(HttpStatus.OK.value())
                .message("퀴즈룸 퇴장 완료")
                .data("")
                .build(), HttpStatus.OK);
    }

    // 퀴즈 룸 게임 진행 여부 조회
    @GetMapping("/isPlaying/{roomId}")
    public ResponseEntity<CommonResponse> checkQuizroomIsPlaying(@PathVariable Long roomId) {

        return new ResponseEntity<>(CommonResponse.builder()
                .status(HttpStatus.OK.value())
                .message("퀴즈룸 진행중")
                .data("")
                .build(), HttpStatus.OK);
    }

    // 퀴즈 룸 단어 리스트 요청
    @PostMapping("/start/{roomId}")
    public ResponseEntity<CommonResponse> quizroomRequestWordList(@PathVariable Long roomId) {

        return new ResponseEntity<>(CommonResponse.builder()
                .status(HttpStatus.OK.value())
                .message("퀴즈룸 단어 리스트 응답 완료")
                .data("")
                .build(), HttpStatus.OK);
    }

    // 퀴즈 룸 종료
    @DeleteMapping("/end/{roomId}")
    public ResponseEntity<CommonResponse> terminateQuizroom(@PathVariable Long roomId) {

        return new ResponseEntity<>(CommonResponse.builder()
                .status(HttpStatus.OK.value())
                .message("퀴즈룸 종료 성공")
                .data("")
                .build(), HttpStatus.OK);
    }




}
