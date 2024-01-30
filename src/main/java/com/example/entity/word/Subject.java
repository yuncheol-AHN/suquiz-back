package com.example.entity.word;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Builder(toBuilder = true) // 수정을 하고 싶을 때 toBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter
public class Subject {

    @Id @GeneratedValue
    @Column(name = "subject_id")
    private Long id;

    @OneToMany(mappedBy = "subject")
    @Builder.Default
    private List<Word> wordList = new ArrayList<>();

    private String subjectName;

//    @Builder
//    public Subject(String subjectName) {
//        this.subjectName = subjectName;
//    }



}
