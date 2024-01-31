package com.example.entity.user.repository;

import com.example.entity.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);

    @Query("select u from User u where u.nickname like %:nickname%")
    List<User> findAllByNickname(@Param("nickname") String nickname);

    User findByNickname(String Nickname);


}
