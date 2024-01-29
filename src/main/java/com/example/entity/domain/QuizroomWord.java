package com.example.entity.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="quizroom_word")
@Getter
public class QuizroomWord {

    @Id @GeneratedValue
    @Column(name = "quizroom_word_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "quizroom_id")
    private Quizroom quizroom;

    @ManyToOne(fetch = FetchType.LAZY)
    private Word word;

    @Builder
    public QuizroomWord(Quizroom quizroom, Word word) {
        this.quizroom = quizroom;
        this.word = word;
        quizroom.quizroomWordList.add(this);
    }
}
