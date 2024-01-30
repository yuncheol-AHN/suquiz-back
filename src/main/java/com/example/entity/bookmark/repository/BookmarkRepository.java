package com.example.entity.bookmark.repository;

import com.example.entity.bookmark.domain.Bookmark;
import com.example.entity.user.domain.User;
import com.example.entity.word.domain.Word;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {
//    Bookmark findByUserAndWord(User user, Word word);

    // select b from Bookmark b left outer join user on b.user_id = :user_id
    @EntityGraph(attributePaths = {"user","word.subject", "word.category"})
    List<Bookmark> findAllByUser(User user);

    // user와 word로 존재하는지 있으면 true, 없으면 false
    Bookmark findAllByUserAndWord(User user, Word word);


}
