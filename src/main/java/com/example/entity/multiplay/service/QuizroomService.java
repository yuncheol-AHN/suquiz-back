package com.example.entity.multiplay.service;

import org.springframework.stereotype.Service;


public interface QuizroomService {
    int makeQuizroom(Long userId);

    boolean checkIsRoomJoinable(String inviteCode);
}
