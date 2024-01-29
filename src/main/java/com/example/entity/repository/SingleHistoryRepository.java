package com.example.entity.repository;

import com.example.entity.domain.singlehistory.entity.SingleHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
@Repository
public interface SingleHistoryRepository extends JpaRepository<SingleHistory, Long> {
    SingleHistory findByCreateDate(LocalDate localDate);
}
