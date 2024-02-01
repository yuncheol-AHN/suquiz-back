package com.example.entity.repository;

import com.example.entity.domain.Word;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WordRepoitory extends JpaRepository<Word, Long> {
    Word findByWordName(String wordName);
}
