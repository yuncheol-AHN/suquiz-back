package com.example.entity.singleplay.repository;

import com.example.entity.singleplay.domain.SingleHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
@Repository
public interface SingleHistoryRepository extends JpaRepository<SingleHistory, Long> {
    SingleHistory findByCreateDate(LocalDate localDate);
}
