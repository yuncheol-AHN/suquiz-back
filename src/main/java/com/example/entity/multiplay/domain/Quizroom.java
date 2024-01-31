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

//    @OneToMany(mappedBy = "quizroom", cascade = CascadeType.ALL)
//    @Builder.Default
//    List<QuizroomWord> quizroomWordList = new ArrayList<>();

    @Builder.Default
    private boolean isPlaying = false;  // 게임 진행중 여부

    private String inviteCode;  // 초대 코드



    public void addUser(User user) {
        this.userList.add(user);
    }

    public void updateIsPlaying() {
        isPlaying = !isPlaying;
    }
}
