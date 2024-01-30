package com.example.entity.word.repository;

import com.example.entity.word.domain.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface SubjectRepository extends JpaRepository<Subject, Long> {
    Subject findBySubjectName(String subjectName);
    boolean existsBySubjectName(String subjectName);
}
