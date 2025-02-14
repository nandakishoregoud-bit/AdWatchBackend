package com.AdWatch.AdWatch.Rewards.App.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.AdWatch.AdWatch.Rewards.App.entity.BlockedUser;
import com.AdWatch.AdWatch.Rewards.App.entity.User;

@Transactional
public interface BlockedUserRepository extends JpaRepository<BlockedUser, Long> { // Use Long for the ID type

    boolean existsByOwnerAndBlockedUser(User owner, User user);

    void deleteByOwnerAndBlockedUser(User owner, User user); // Add a method for unblocking

}
