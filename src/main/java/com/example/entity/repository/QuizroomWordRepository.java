package com.example.entity.repository;

import com.example.entity.domain.QuizroomWord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizroomWordRepository extends JpaRepository<QuizroomWord, Long> {

}
