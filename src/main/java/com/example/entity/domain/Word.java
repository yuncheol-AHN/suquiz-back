package com.example.entity.domain;

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
@Getter
public class Word {

    @Id @GeneratedValue
    @Column(name = "word_id")
    private Long id;

    /** 유저 북마크, 퀴즈룸 질문리스트**/
//    @OneToMany(mappedBy = "word")
//    private List<Bookmark> bookmarkList = new ArrayList<>();

//    @OneToMany(mappedBy = "word")
//    @Builder.Default
//    private List<QuizroomWord> quizroomWordList = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "subject_id")
    private Subject subject;

    @Column(name = "word_name")
    private String wordName;

    @Column(name = "video_url")
    private String videoUrl;

    /**
     * 1. 생성 메서드
     * 2. 연관관계 메서드
     */
    // 주제가 있는 단어 생성
    @Builder
    public Word(Category category, Subject subject, String wordName, String videoUrl) {
        this.category = category;
        this.subject = subject;
        this.wordName = wordName;
        this.videoUrl = videoUrl;
        subject.getWordList().add(this);
    }


    public void changeSubject(Subject newSubject) {
        // 현재 단어의 주제가 있으면, 지워주고
        if(this.subject != null) {
            this.subject.getWordList().remove(this);
        }
        // 새로 들어오는 주제가 빈값이 아니면
        if(newSubject != null) {
            newSubject.getWordList().add(this);
        }
        // 현재 단어의 주제를 바꿔준다
        this.subject = newSubject;
    }


}
