package com.example.entity.education.repository;

import com.example.entity.word.Category;
import com.example.entity.word.Subject;
import com.example.entity.word.Word;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WordRepository extends JpaRepository<Word, Long> {
    Word findByWordName(String wordName);
    @Query(" select w from Word w where w.category = :category")
    List<Word> findByCategory(@Param("category") Category category);

    List<Word> findBySubject(Subject subject);
}
