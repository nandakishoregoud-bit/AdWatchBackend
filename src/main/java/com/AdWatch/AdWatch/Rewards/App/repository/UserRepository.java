package com.AdWatch.AdWatch.Rewards.App.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.AdWatch.AdWatch.Rewards.App.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
    
    @Query("SELECT u FROM User u WHERE u.blocked = true")
    List<User> findAllBlockedUsers();

    
    @Query("SELECT u FROM User u WHERE u.blocked = false")
    List<User> findAllUnblockedUsers();

    List<User> findByBlockedTrue();

}