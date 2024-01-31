package com.example.entity.mypage.controller;

import com.example.entity.global.dto.CommonResponse;
import com.example.entity.mypage.dto.MypageDto;
import com.example.entity.mypage.service.MypageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

import java.util.HashMap;

@RestController
@RequestMapping("/mypage")
@RequiredArgsConstructor
public class MypageController {

    private final MypageService mypageService;

    /**e
     * request param : query parameter
     * request body : request body
     * GET : query parameter
     * POST, PUT, DELETE : request body
     */
    @GetMapping("/find/{userId}")
    public ResponseEntity<CommonResponse<MypageDto.UserResponse>> find(@PathVariable(value = "userId") String email) {
        /**
         * nickname, profile image, level, exp
         */
        MypageDto.UserResponse response = mypageService.find(email);
        return new ResponseEntity<>(CommonResponse.<MypageDto.UserResponse>builder()
                .status(HttpStatus.OK.value())
                .message("success : find user nickname")
                .data(response)
                .build(), HttpStatus.OK);
    }

    @PutMapping("/modify")
    public ResponseEntity<CommonResponse<MypageDto.NicknameModifyResoponse>> modify(@RequestBody MypageDto.NicknameModifyRequest request) {
        /**
         * modified nickname
         */
//        String response = mypageService.modify(request);

        return new ResponseEntity<>(CommonResponse.<MypageDto.NicknameModifyResoponse>builder()
                .status(HttpStatus.OK.value())
                .message("success : modify user nickname")
                .data(mypageService.modify(request))
                .build(), HttpStatus.OK);
    }

    @GetMapping("/wordle/{userId}")
    public ResponseEntity<CommonResponse<MypageDto.UserResponse>> wordle(@PathVariable(value = "userId") String email) {

        MypageDto.UserWordleResponse response = mypageService.userWordleResponse(email);
        return new ResponseEntity<>(CommonResponse.<MypageDto.UserResponse>builder()
                .status(HttpStatus.OK.value())
                .message("success : user wordle list")
                .data(response)
                .build(), HttpStatus.OK);
    }
}
