package com.example.entity.repository;

import com.example.entity.domain.Bookmark;
import com.example.entity.domain.User;
import com.example.entity.domain.Word;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {
//    Bookmark findByUserAndWord(User user, Word word);

    @EntityGraph(attributePaths = {"user"})
    List<Bookmark> findAllByUser(User user);


}
