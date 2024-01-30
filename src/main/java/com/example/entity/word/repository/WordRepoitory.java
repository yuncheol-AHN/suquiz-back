package com.example.entity.word.repository;

import com.example.entity.word.domain.Word;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WordRepoitory extends JpaRepository<Word, Long> {
    Word findByWordName(String wordName);
}
