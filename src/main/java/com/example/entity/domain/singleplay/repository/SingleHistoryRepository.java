package com.example.entity.domain.singleplay.repository;

import com.example.entity.domain.User;
import com.example.entity.domain.singleplay.entity.SingleHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
@Repository
public interface SingleHistoryRepository extends JpaRepository<SingleHistory, Long> {
    SingleHistory findByCreateDate(LocalDate localDate);
    SingleHistory findByUserAndCreateDate(User user, LocalDate localDate);
}
