package com.example.entity.repository;

import com.example.entity.multiplay.domain.Quizroom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizroomRepository extends JpaRepository<Quizroom, Long> {
}
