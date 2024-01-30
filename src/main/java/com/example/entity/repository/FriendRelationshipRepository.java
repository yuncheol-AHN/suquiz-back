package com.example.entity.repository;

import com.example.entity.friend.domain.FriendRelationship;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FriendRelationshipRepository extends JpaRepository<FriendRelationship, Long> {

}
