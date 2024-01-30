package com.example.entity.multiplay.domain;

import com.example.entity.user.domain.User;
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
@Builder(toBuilder = true)
public class Quizroom {

    @Id @GeneratedValue
    @Column(name = "quizroom_id")
    private Long id;

    @OneToMany(mappedBy = "quizroom")
    @Builder.Default
    private List<User> userList = new ArrayList<>();

    @OneToMany(mappedBy = "quizroom", cascade = CascadeType.ALL)
    @Builder.Default
    List<QuizroomWord> quizroomWordList = new ArrayList<>();

    @Builder.Default
    private boolean isPlaying = false;  // 게임 진행중 여부

//  private int participantsCount;  // 참가자 수
//  유저 리스트에서 참가인원 수를 체크하는 방식으로 사용하면 될듯 하여, deprecated 함
    private String inviteCode;  // 초대 코드



    public void addUser(User user) {
        this.userList.add(user);
    }


}
