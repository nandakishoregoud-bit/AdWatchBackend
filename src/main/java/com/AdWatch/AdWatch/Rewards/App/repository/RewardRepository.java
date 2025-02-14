package com.AdWatch.AdWatch.Rewards.App.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.AdWatch.AdWatch.Rewards.App.entity.Reward;

@Repository
public interface RewardRepository extends JpaRepository<Reward, Long> {

	// Custom query to find rewards by userId
    List<Reward> findAllByUserId(Long userId);
	
}
