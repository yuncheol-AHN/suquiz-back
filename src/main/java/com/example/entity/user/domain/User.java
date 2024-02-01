
package com.example.entity.user.domain;

import com.example.entity.multiplay.domain.Quizroom;
import com.example.entity.singleplay.domain.SingleHistory;


import com.example.entity.bookmark.domain.Bookmark;
import com.example.entity.singleplay.domain.SingleHistory;
import com.example.entity.multiplay.domain.Quizroom;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;


@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users") // 예약어 사용으로 인한 에러, => 테이블명 설정해줌
@Getter
public class User {

    @Id @GeneratedValue
    @Column(name = "user_id")
    private Long id;

    // unique key
    private String email;

//    @OneToMany(mappedBy = "user")
//    @Builder.Default
//    private List<Bookmark> bookmarkList = new ArrayList<>();

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @Builder.Default
    private List<SingleHistory> singleHistoryList = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "quizroom_id")
    private Quizroom quizroom;

    private String nickname;
    private int level;
    private int xp;

    @Enumerated(EnumType.STRING)
    private OAuthProvider oAuthProvider;

    // provider
    // refresh token

    @Builder.Default
    private boolean isPlaying = false;
    private int correctCount;   // 최근 연속 정답
    private int solveCount;     // 최근 연속 풀이
    private int maxCorrectCount;// 최다 연속 정답

    private String imageUrl;

//    @Builder(toBuilder = true)
//    public User(String email, OAuthProvider oAuthProvider) {
//        this.email = email;
//        this.oAuthProvider = oAuthProvider;
//    }



    public void changeNickname(String modifiedName) {
        this.nickname = modifiedName;
    }

    public void changeQuizroom(Quizroom quizroom) {
        this.quizroom = quizroom;
    }

    public void updateIsPlaying() {
        isPlaying = !isPlaying;
    }

    public void addExp(int score) {
        this.xp += score*5;
    }

    public void updateExp(int exp) {
        this.xp = exp;
    }

    public void levelUp() {
        this.level += 1;
    }
}
//