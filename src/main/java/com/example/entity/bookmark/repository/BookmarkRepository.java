package com.example.entity.bookmark.repository;

import com.example.entity.bookmark.domain.Bookmark;
import com.example.entity.user.User;
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
