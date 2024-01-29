package com.example.entity.repository;

import com.example.entity.domain.Category;
import com.example.entity.domain.Word;
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
}
