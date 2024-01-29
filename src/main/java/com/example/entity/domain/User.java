package com.example.entity.domain;

import com.example.entity.domain.bookmark.ecntity.Bookmark;
import com.example.entity.domain.singlehistory.entity.SingleHistory;
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

    @OneToMany(mappedBy = "user")
    @Builder.Default
    private List<Bookmark> bookmarkList = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    @Builder.Default
    private List<SingleHistory> singleHistoryList = new ArrayList<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "quizroom_id")
    private Quizroom quizroom;
    //
    private String nickname;
    private int level;
    private int exp;
    private OAuthProvider oAuthProvider;

    // provider
    // refresh token

    private boolean isPlaying;
    private int recentCorrectCount;
    private int maxCorrectCount;
    private String imageUrl;

    @Builder
    public User(String email, String nickname, OAuthProvider oAuthProvider) {
        this.email = email;
        this.nickname = nickname;
        this.oAuthProvider = oAuthProvider;
    }

}
