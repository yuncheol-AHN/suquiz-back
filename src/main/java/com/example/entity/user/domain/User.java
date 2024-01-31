
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

    @OneToMany(mappedBy = "user")
    @Builder.Default
    private List<SingleHistory> singleHistoryList = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "quizroom_id")
    private Quizroom quizroom;

    private String nickname;
    private int level;
    private int exp;
    private OAuthProvider oAuthProvider;

    // provider
    // refresh token
    @Builder.Default
    private boolean isPlaying = false
            ;
    private int recentCorrectCount;
    private int maxCorrectCount;
    private String imageUrl;

    @Builder
    public User(String email, String nickname, OAuthProvider oAuthProvider) {
        this.email = email;
        this.nickname = nickname;
        this.oAuthProvider = oAuthProvider;
    }

    public void changeQuizroom(Quizroom quizroom) {
        this.quizroom = quizroom;
    }

    public void updateIsPlaying() {
        isPlaying = !isPlaying;
    }

    public void addExp(int score) {
        this.exp += score*5;
    }

    public void updateExp(int exp) {
        this.exp = exp;
    }

    public void levelUp() {
        this.level += 1;
    }
}
