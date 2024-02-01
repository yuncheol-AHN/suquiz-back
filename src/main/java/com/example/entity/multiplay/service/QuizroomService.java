package com.example.entity.multiplay.service;

import com.example.entity.education.dto.WordDTO;
import com.example.entity.multiplay.dto.EndQuizDto;
import org.springframework.stereotype.Service;

import java.util.List;


public interface QuizroomService {
    int makeQuizroom(Long userId);

    boolean checkIsRoomJoinable(String inviteCode);

    boolean checkIsRoomPlaying(Long quizroomId);


    List<WordDTO.WordResponseDto> startQuizroom(Long quizroomId);

    List<EndQuizDto.Response> endQuizgame(Long quizroomId, List<EndQuizDto.Request> requests);
}
