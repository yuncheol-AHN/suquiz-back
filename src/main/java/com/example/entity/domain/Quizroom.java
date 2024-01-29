package com.example.entity.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
public class Quizroom {

    @Id @GeneratedValue
    @Column(name = "quizroom_id")
    private Long id;

    @OneToMany(mappedBy = "quizroom")
    private List<User> userList = new ArrayList<>();

    @OneToMany(mappedBy = "quizroom", cascade = CascadeType.ALL)
    List<QuizroomWord> quizroomWordList = new ArrayList<>();

    private boolean isPlaying = false;  // 게임 진행중 여부

//  private int participantsCount;  // 참가자 수
//  유저 리스트에서 참가인원 수를 체크하는 방식으로 사용하면 될듯 하여, deprecated 함

    private String inviteCode;  // 초대 코드

    @Builder
    public Quizroom(String inviteCode) {
    this.inviteCode = inviteCode;
    }

}
