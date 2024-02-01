package com.example.entity.word.domain;

import jakarta.persistence.*;
import lombok.*;

@Builder(toBuilder = true)
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Getter
public class DailyWord {

    @Id @GeneratedValue
    @Column(name = "daily_word_id")
    private String id;

    private Category category;

    @ManyToOne()
    private Subject subject;
    private String wordName;
    private String wordUrl;
}
