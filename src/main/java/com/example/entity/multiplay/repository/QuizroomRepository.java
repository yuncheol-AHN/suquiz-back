package com.example.entity.multiplay.repository;

import com.example.entity.multiplay.domain.Quizroom;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface QuizroomRepository extends JpaRepository<Quizroom, Long> {


    @EntityGraph(attributePaths = {"userList"})
    Optional<Quizroom> findById(long id);

    @EntityGraph(attributePaths = {"userList"})
    Optional<Quizroom> findByInviteCode(String inviteCode);
}
